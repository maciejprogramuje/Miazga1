package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.SeasonAdapter;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentSeasonsBinding;
import com.facebook.maciejprogramuje.miazga1.models.EpisodeItem;
import com.facebook.maciejprogramuje.miazga1.models.SeasonItem;

import java.util.ArrayList;
import java.util.List;

public class SeasonsFragment extends Fragment {
    private RecyclerView seasonRecyclerView;
    private FragmentSeasonsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<SeasonItem> seasonItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<EpisodeItem> episodeItems = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                episodeItems.add(new EpisodeItem(j));
            }

            seasonItems.add(new SeasonItem(i, episodeItems));
        }

        seasonRecyclerView = view.findViewById(R.id.season_recycler_view);
        seasonRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SeasonAdapter seasonAdapter = new SeasonAdapter(view.getContext(), seasonItems, SeasonsFragment.this);
        seasonRecyclerView.setAdapter(seasonAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}