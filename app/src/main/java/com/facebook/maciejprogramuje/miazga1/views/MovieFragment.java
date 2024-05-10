package com.facebook.maciejprogramuje.miazga1.views;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.facebook.maciejprogramuje.miazga1.databinding.FragmentMovieBinding;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;

import java.util.Objects;

public class MovieFragment extends Fragment {
    private FragmentMovieBinding binding;
    Uri videoPathUri;
    int episodeId;
    private int currentPositionOfMovie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoPathUri = Uri.parse(requireArguments().getString("videoPathString"));
        episodeId = requireArguments().getInt("episodeId");

        MediaController mc = new MediaController(getActivity());
        mc.setAnchorView(binding.videoView);
        mc.setMediaPlayer(binding.videoView);
        binding.videoView.setMediaController(mc);
        binding.videoView.setVideoURI(videoPathUri);

        binding.videoView.setOnCompletionListener(mediaPlayer -> {
            try (VideoDbHandler videoDbHandler = new VideoDbHandler(view.getContext())) {
                videoDbHandler.updateEpisodeWatchedInDb(episodeId, true);
            }
        });

        binding.videoView.start();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // poziomo

            // Hide the status bar
            View decorView = requireActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            // Hide the action bar
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();

            binding.videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            // pionowo
            // Show the status bar
            View decorView = requireActivity().getWindow().getDecorView(); // Hide the status bar.
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            // Show the action bar
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();

            binding.videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //todo - zapisywanie do bazy czasu obejrzanego filmu
        /*try (VideoDbHandler videoDbHandler = new VideoDbHandler(getContext())) {
            videoDbHandler.updateEpisodeWatchedInDb(episodeId, true);
        }*/

        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();

        currentPositionOfMovie = binding.videoView.getCurrentPosition();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.videoView.seekTo(currentPositionOfMovie);
    }
}