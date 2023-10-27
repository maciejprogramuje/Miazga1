package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.EpisodeAdapter;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentEpisodesBinding;


public class EpisodesFragment extends Fragment {
    private FragmentEpisodesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int seasonNumber = requireArguments().getInt("seasonNumber");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        RecyclerView episodesRecyclerView = view.findViewById(R.id.episodes_recycler_view);
        episodesRecyclerView.setLayoutManager(linearLayoutManager);
        episodesRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        EpisodeAdapter episodeAdapter = new EpisodeAdapter(
                view.getContext(),
                seasonNumber,
                EpisodesFragment.this);
        episodesRecyclerView.setAdapter(episodeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}