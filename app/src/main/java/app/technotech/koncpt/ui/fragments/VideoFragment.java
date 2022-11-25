package app.technotech.koncpt.ui.fragments;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.DownloadModel;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.FragmentVideoBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.utils.DebugLog;


public class VideoFragment extends Fragment {


    //private String TEST_URL_MP4 = "/storage/emulated/0/SATCrusher/JKEbjHci7y1603949637.mp4";
    private FragmentVideoBinding binding;
    private Toolbar toolbar;
    private BottomNavigationView mBottomNavigationView;


    private ExoPlayer player;
    AppCompatImageView fullscreenButton;
    boolean fullscreen = false;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private PlaybackStateListener playbackStateListener;

    private DownloadModel data;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = new Gson().fromJson(getArguments().getString("data"), new TypeToken<DownloadModel>() {
            }.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = ((MainActivity) requireActivity()).getToolbar();
        mBottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();


        if (mBottomNavigationView.getVisibility() == View.VISIBLE) {
            mBottomNavigationView.setVisibility(View.GONE);
        }

        createPlayer(view);
        initializePlayer(data.getVideo_url());

        binding.getRoot().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        return true;
                    }
                }
                return false;
            }
        });


    }


    private void createPlayer(View view) {
        try {
            player = null;
            player = new ExoPlayer.Builder(getActivity()).build();
            fullscreenButton = binding.videoView.findViewById(R.id.exo_fullscreen_icon);
            fullscreenButton.setOnClickListener(view1 -> {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_open));

                    toolbar.setVisibility(View.VISIBLE);

                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.videoView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * requireActivity().getResources().getDisplayMetrics().density);
                    binding.videoView.setLayoutParams(params);

                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_close));

                    toolbar.setVisibility(View.GONE);

                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.videoView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    binding.videoView.setLayoutParams(params);

                    fullscreen = true;
                }
            });
            binding.videoView.setPlayer(player);
            binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            playbackStateListener = new PlaybackStateListener();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
    }


    private class PlaybackStateListener implements Player.Listener {

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
            createPlayer(binding.getRoot());
            initializePlayer(data.getVideo_url());
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