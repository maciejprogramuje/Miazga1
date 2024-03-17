package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.MediaStoreHelper;
import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.SeasonAdapter;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentSeasonsBinding;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;

public class SeasonsFragment extends Fragment {
    private FragmentSeasonsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MediaStoreHelper mediaStoreHelper = new MediaStoreHelper(view);
        try (VideoDbHandler miazgaVideoDb = new VideoDbHandler(view.getContext())) {
            miazgaVideoDb.fillDatabase(mediaStoreHelper.getVideos());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        RecyclerView seasonRecyclerView = view.findViewById(R.id.season_recycler_view);
        seasonRecyclerView.setLayoutManager(linearLayoutManager);
        seasonRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        SeasonAdapter seasonAdapter = new SeasonAdapter(view.getContext(),
                SeasonsFragment.this);
        seasonRecyclerView.setAdapter(seasonAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}