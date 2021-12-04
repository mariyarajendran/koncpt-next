package app.technotech.koncpt.ui.fragments.liveclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.DBHelper;
import app.technotech.koncpt.data.network.model.DownloadModel;
import app.technotech.koncpt.data.network.model.NotesModel;
import app.technotech.koncpt.data.network.model.RatingModel;
import app.technotech.koncpt.data.network.model.VideoCompleteModel;
import app.technotech.koncpt.databinding.FragmentClassesBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.ClassAdapter;
import app.technotech.koncpt.ui.viewmodels.CompleteVideoViewModel;
import app.technotech.koncpt.ui.viewmodels.LiveNotesViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.DownloadAsync;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class ClassFragment extends Fragment implements ClassAdapter.OnVideoItemSelectedListener {


    private LiveNotesViewModel model;
    private CompleteVideoViewModel viewModel;
    private GeneralUtils generalUtils;
    FragmentClassesBinding binding;
    private AlertDialog progressDialog;
    private Context mContext;


    //Player
    private PlayerView playerView;
    private SimpleExoPlayer player;
    AppCompatImageView fullscreenButton;
    boolean fullscreen = false;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private PlaybackStateListener playbackStateListener;


    //Variables
    private String video_id, class_video, title, description, videoId, type, video_type;

    private DBHelper db;
    private ArrayList<DownloadModel> downloadModelArrayList;
    private ClassAdapter completedAdapter;

    private TabLayout tabLayout;
    private MainFragment fragment;
    private Toolbar toolbar;
    private BottomNavigationView navigationView;
    PopupWindow popupWindow;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static ClassFragment getInstance(String params) {
        Bundle bundle = new Bundle();
        ClassFragment classFragment = new ClassFragment();
        bundle.putString("video_id", params);
        classFragment.setArguments(bundle);
        return classFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            video_id = getArguments().getString("video_id");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_classes, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(LiveNotesViewModel.class);
        binding.setClassViewModel(model);
        return binding.getRoot();
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        fragment = MainFragment.getInstance();
        tabLayout = fragment.getTabLayoutView();
        navigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        toolbar = ((MainActivity) requireActivity()).getToolbar();
        db = new DBHelper(getActivity());


        if (navigationView.getVisibility() == View.VISIBLE) {
            navigationView.setVisibility(View.GONE);
        }

        createPlayer(view);
        setHasOptionsMenu(true);
        sendPost();
        binding.imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (class_video != null) {

                    // File downloadFile = new File(directory, video_id + ".klc");

                    Activity activity = getActivity();

                    if (GeneralUtils.hasPermissionToDownload(activity)){
                        new DownloadAsync(getActivity(), 0, videoId, title, description, class_video, db).execute();
                    }

                }

            }

        });
        binding.btnViewPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
            }
        });


        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    try {

                        long lastPlayedPosition = (binding.videoView.getPlayer().getCurrentPosition() + 1000) / 1000;
                        String lastPosition = DateUtils.formatElapsedTime(lastPlayedPosition);
                        Log.i("TAG", "onClick: "+lastPosition);
                        Log.d("completedVideo", lastPosition + "");
                        type = "1";
                        progressDialog.show();
                        completePost(type, lastPosition, v);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


    }


    private void completePost(String type, String lastPosition, View v) {

        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("paushed_time", lastPosition);
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("video_id", video_id);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<VideoCompleteModel>() {
            @Override
            public void onChanged(VideoCompleteModel notesModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (notesModel != null) {

                            if (notesModel.getStatus() == 1) {
                                String jsonData = new Gson().toJson(notesModel);
                                showPopupWindow(v);
//                                    binding.videoView.pause();
                            }
                        }
                    }
                }, 500);
            }
        });


    }


    public void showPopupWindow(final View view) {
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.completed_popup, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


//        Button close=popupView.findViewById(R.id.img_close_image_dialog);
        RatingBar ratingBar = popupView.findViewById(R.id.videoRating);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
        Button btnSubmit = popupView.findViewById(R.id.btnSubmit);
        String rating = "";
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(ratingBar.getRating());
                Log.d("rating", rating + "");
                if (rating != null && !rating.equals("0.0") && !rating.equals("")) {
                    updateRating(rating);
                } else {
                    Toast.makeText(getContext(), "Oops!You have not rated the module yet", Toast.LENGTH_SHORT).show();

                }


            }
        });

        TextView txtCancel = popupView.findViewById(R.id.txtCancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        //Handler for clicking on the inactive zone of the window

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }


//
//        binding.videoView.setControlDispatcher(new DefaultControlDispatcher()){
//            @Override
//            public boolean dispatchSetPlayWhenReady(Player player, boolean playWhenReady) {
//                // You can modify this code to call through to the player or not.
//                player.setPlayWhenReady(playWhenReady);
//                // Return true if you called through to the player, and false otherwise.
//                return true;
//            }
//        };


    private void createPlayer(View view) {

        try {

            player = null;
            player = ExoPlayerFactory.newSimpleInstance(getActivity());
            playerView = view.findViewById(R.id.video_view);
            fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_icon);

            fullscreenButton.setOnClickListener(view1 -> {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_open));

                    toolbar.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);

                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * requireActivity().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);

                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_close));


                    toolbar.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);

                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);

                    fullscreen = true;
                }
            });

            player.setVideoScalingMode(Renderer.VIDEO_SCALING_MODE_DEFAULT);
            playerView.setPlayer(player);
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

            playbackStateListener = new PlaybackStateListener();


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser) {
            DebugLog.e("isVisibleToUser" + " Shows");
            if (player != null) {
                player.setPlayWhenReady(isVisibleToUser);
            }
        } else {
            if (player != null) {
                player.setPlayWhenReady(isVisibleToUser);
            }
            DebugLog.e("isVisibleToUser" + " Not Shows");
        }

        super.setUserVisibleHint(isVisibleToUser);


    }

    private void initializePlayer(String link) {

        // With this
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(link)
                .setMimeType(MimeTypes.VIDEO_MP4)
                .build();
        player.addMediaItem(mediaItem);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.addListener(playbackStateListener);
        player.prepare();


    }


    private void updateRating(String rating) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("video_id", video_id);
        params.put("ratting", rating);
        DebugLog.e("paramsRating==> " + params.toString()); //this values are not displayed..check

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getRatingData(params).observe(getActivity(), new Observer<RatingModel>() {
            @Override
            public void onChanged(RatingModel notesModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (notesModel != null) {
                            binding.activityMain.setVisibility(View.VISIBLE);
                            if (notesModel.getStatus() == 1) {
                                String jsonData = new Gson().toJson(notesModel);
                                DebugLog.e("ResponseClass =>>" + jsonData);
                                Toast.makeText(getContext(), "Thank You for your Ratting", Toast.LENGTH_SHORT).show();
                                binding.btnComplete.setVisibility(View.INVISIBLE);

//                                popupWindow.dismiss();
                            } else {
                                String jsonData = new Gson().toJson(notesModel);
                                DebugLog.e("ResponseClass =>>" + jsonData);
                                Toast.makeText(getContext(), "Oops..Something went wrong..please try again", Toast.LENGTH_SHORT).show();
                                //                              popupWindow.dismiss();
                            }
                        }
                    }
                }, 500);
            }
        });


    }


//    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        public void onAudioFocusChange(int focusChange) {
//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
//                // Pause playback
//                pause();
//            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                // Resume playback
//                //resume();
//            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
//                // audioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
//                audioManager.abandonAudioFocus(afChangeListener);
//                // Stop playback
//                // stop();
//
//            }
//        }
//    };

    public void pause() {

        try {


//            if (binding.videoView == null) {
//                return;
//            } else if (binding.videoView.getPlayer().getPlayWhenReady()) {
//                long lastPlayedPosition = binding.videoView.getPlayer().getCurrentPosition();
//                String lastPosition = String.valueOf(lastPlayedPosition);
//                Log.d("lastPosition", lastPosition + "");
//                binding.videoView.getPlayer().setPlayWhenReady(false);
//                pausedFromPlayer = false;
//            } else {
//                pausedFromPlayer = true;
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void sendPost() {

        Map<String, String> params = new HashMap<>();
        params.put("video_id", video_id);

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getNoteData(params).observe(getActivity(), new Observer<NotesModel>() {
            @Override
            public void onChanged(NotesModel notesModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (notesModel != null) {
                            binding.activityMain.setVisibility(View.VISIBLE);
                            if (notesModel.getStatus() == 1) {
                                String jsonData = new Gson().toJson(notesModel);
                                DebugLog.e("ResponseClass =>>" + jsonData);
                                loadData(notesModel);
                            } else {
                                Toasty.error(mContext, notesModel.getMessage()).show();
                            }
                        }
                    }
                }, 500);
            }
        });

    }

    private void loadData(NotesModel notesModel) {

        binding.txtTitle.setText(notesModel.getData().get(0).getSubjectTitle());
        binding.txtName.setText(notesModel.getData().get(0).getFacultyName());
        binding.txtLabel.setText(notesModel.getData().get(0).getClassTitle());
        binding.txtDescription.setText(notesModel.getData().get(0).getDescription());
        class_video = notesModel.getData().get(0).getClassVideo();

        if (class_video != null) {
            String[] separated = class_video.split("video_class/");
            String one = separated[0].trim();
            Log.d("one", one + "");
            String two = separated[1].trim();
            Log.d("two", two + "");
            String three[] = two.split("-");
            videoId = three[0].trim();
            Log.d("videoId", videoId + "");


            if (notesModel.getData().get(0).getData().size() > 0) {
                binding.recyclerViewSlicing.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recyclerViewSlicing.setItemAnimator(new DefaultItemAnimator());
                completedAdapter = new ClassAdapter(getActivity(), notesModel.getData().get(0).getData(), this::onVideoItemClick);
                binding.recyclerViewSlicing.setAdapter(completedAdapter);

            }


            title = notesModel.getData().get(0).getSubjectTitle();
            description = notesModel.getData().get(0).getDescription();
            video_type = notesModel.getData().get(0).getType();


            initializePlayer(class_video);


            if (video_type != null) {
                if (video_type.equals("1")) {
                    binding.btnComplete.setVisibility(View.INVISIBLE);
                } else {
                    binding.btnComplete.setVisibility(View.VISIBLE);

                }
            }


        }

    }



    @Override
    public void onPause() {
        super.onPause();

        try {

            if (DateUtils.formatElapsedTime(binding.videoView.getPlayer().getCurrentPosition()) != null) {

                String valuePause ;
                long millis = binding.videoView.getPlayer().getCurrentPosition();

                valuePause = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                Log.i("TAG", "onPause: "+valuePause);
                if (video_type.equals("")) {
                    pauseVideo(valuePause);
                } else if (video_type.equals("1")) {

                } else {
                    pauseVideo(valuePause);
                }
            }

            releasePlayer();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void pauseVideo(String valuePause) {

        Map<String, String> params = new HashMap<>();
        params.put("type", "2");
        params.put("paushed_time", valuePause);
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("video_id", video_id);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<VideoCompleteModel>() {
            @Override
            public void onChanged(VideoCompleteModel notesModel) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (notesModel != null) {
                            binding.lnrPlans.setVisibility(View.GONE);
                            binding.activityMain.setVisibility(View.VISIBLE);


                            if (notesModel.getStatus() == 1) {
                                String jsonData = new Gson().toJson(notesModel);
                                DebugLog.e("Response =>>" + jsonData);

                            }
                        }
                    }
                }, 500);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();

        try {

            if (Build.VERSION.SDK_INT > 23) {
                releasePlayer();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        try {
            releasePlayer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public void onVideoItemClick(NotesModel.VideoCutTimeTopic data, int position) {


        try {

            try {

                String timeString = data.getVideoTime();
                int multiplier[] = {3600000, 60000, 100};
                String splits[] = timeString.split(":");
                long time = 0;
                for (int x = 0; x < splits.length; x++) {
                    time += (Integer.parseInt(splits[x]) * multiplier[x]);
                }
                System.out.println(time);
                if (timeString != null) {
                    player.seekTo(time);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private class PlaybackStateListener implements Player.EventListener {

        @Override
        public void onPlaybackStateChanged(int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    DebugLog.e("STATE => " + stateString);
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    DebugLog.e("STATE => " + stateString);
                    break;
                case ExoPlayer.STATE_READY:
                    binding.progressBar.setVisibility(View.GONE);
                    stateString = "ExoPlayer.STATE_READY     -";
                    DebugLog.e("STATE => " + stateString);
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    DebugLog.e("STATE => " + stateString);
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    DebugLog.e("STATE => " + stateString);
                    break;
            }
            DebugLog.e("changed state to " + stateString);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            //initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // hideSystemUi();
        if ((Util.SDK_INT < 24 || player == null)) {
            DebugLog.e("Video ID => " + class_video);

            createPlayer(binding.getRoot());
            initializePlayer(class_video);
        }


        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT < 24) {
//            releasePlayer();
//        }
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Util.SDK_INT >= 24) {
//            releasePlayer();
//        }
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }


    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.removeListener(playbackStateListener);
            player.release();
            player = null;
        }
    }

    private void playPlayer() {
        if (player != null) {
            player.setPlayWhenReady(true);
        }
    }

    private void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

}
