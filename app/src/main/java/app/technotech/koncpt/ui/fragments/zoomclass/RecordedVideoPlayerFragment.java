package app.technotech.koncpt.ui.fragments.zoomclass;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentRecordedVideoPlayerBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.fragments.liveclass.MainFragment;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class RecordedVideoPlayerFragment extends Fragment {
    private GeneralUtils generalUtils;
    FragmentRecordedVideoPlayerBinding binding;
    //Player
    private StyledPlayerView playerView;
    private ExoPlayer player;
    boolean fullscreen = false;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private MainFragment fragment;
    private Toolbar toolbar;
    private BottomNavigationView navigationView;
    private String videoUrl = "";
    private PlaybackStateListener playbackStateListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            videoUrl = bundle.getString("video_url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recorded_video_player, viewGroup, false);
        binding.setLifecycleOwner(this);
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
        fragment = MainFragment.getInstance();
        navigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        toolbar = ((MainActivity) requireActivity()).getToolbar();
        if (navigationView.getVisibility() == View.VISIBLE) {
            navigationView.setVisibility(View.GONE);
        }
        setHasOptionsMenu(true);
        createPlayer(view);
        initializePlayer(videoUrl);
    }

    private void createPlayer(View view) {
        try {
            player = null;
            player = new ExoPlayer.Builder(getActivity()).build();
            playerView = view.findViewById(R.id.video_view);
            binding.exoFullscreenIcon.setOnClickListener(view1 -> {
                if (fullscreen) {
                    binding.exoFullscreenIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_open));
                    toolbar.setVisibility(View.VISIBLE);
                    requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * requireActivity().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullscreen = false;
                } else {
                    binding.exoFullscreenIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_fullscreen_close));
                    toolbar.setVisibility(View.GONE);
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
            playerView.setPlayer(player);
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            playbackStateListener = new PlaybackStateListener();
        } catch (Exception ex) {
            ex.printStackTrace();
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
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT < 24 || player == null)) {
            createPlayer(binding.getRoot());
            initializePlayer(videoUrl);
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
            return false;
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
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
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
    public void onPause() {
        super.onPause();
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

    private void initializePlayer(String link) {
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
}
