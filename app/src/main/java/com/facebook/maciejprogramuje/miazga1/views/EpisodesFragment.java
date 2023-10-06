package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.EpisodeAdapter;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentEpisodesBinding;
import com.facebook.maciejprogramuje.miazga1.models.MovieDbHandler;

import java.util.Objects;

public class EpisodesFragment extends Fragment {
    private FragmentEpisodesBinding binding;
    private MovieDbHandler dbEpisodesFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbEpisodesFragment = new MovieDbHandler(view.getContext());

        int seasonPosition = Objects.requireNonNull(getArguments()).getInt("seasonPosition");

        binding.textviewEpisodesOfSeasonNo.setText(String.format("seasonPosition no. %s", seasonPosition));

        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(EpisodesFragment.this)
                .navigate(R.id.action_EpisodesFragment_to_SeasonsFragment));

        RecyclerView episodesRecyclerView = view.findViewById(R.id.episodes_recycler_view);
        episodesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        EpisodeAdapter episodeAdapter = new EpisodeAdapter(view.getContext(),
                dbEpisodesFragment,
                EpisodesFragment.this);
        episodesRecyclerView.setAdapter(episodeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbEpisodesFragment.close();
    }

}