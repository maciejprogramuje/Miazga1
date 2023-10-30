package com.facebook.maciejprogramuje.miazga1.views;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.EpisodeAdapter;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentEpisodesBinding;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {
    private FragmentMovieBinding binding;

    private int currentPositionOfMovie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);

        currentPositionOfMovie = -1;

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Uri videoPathUri = Uri.parse(requireArguments().getString("videoPathString"));
        currentPositionOfMovie = requireArguments().getInt("currentPositionOfMovie");

        MediaController mc = new MediaController(getActivity());
        mc.setAnchorView(binding.videoView);
        mc.setMediaPlayer(binding.videoView);
        binding.videoView.setMediaController(mc);
        binding.videoView.setVideoURI(videoPathUri);


        int orientation = binding.videoView.getResources().getConfiguration().orientation;
        if (orientation == 1) {
            binding.videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else if (orientation == 2) {
            binding.videoView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        if (currentPositionOfMovie != -1) {
            binding.videoView.seekTo(currentPositionOfMovie);
        }

        binding.videoView.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding.videoView.pause();
        currentPositionOfMovie = binding.videoView.getCurrentPosition();

        Bundle bundle = new Bundle();
        bundle.putInt("currentPositionOfMovie", currentPositionOfMovie);

        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();

        binding.videoView.pause();
        currentPositionOfMovie = binding.videoView.getCurrentPosition();

        Bundle bundle = new Bundle();
        bundle.putInt("currentPositionOfMovie", currentPositionOfMovie);
    }
}