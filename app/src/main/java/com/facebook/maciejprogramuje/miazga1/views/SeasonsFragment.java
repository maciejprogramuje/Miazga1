package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.maciejprogramuje.miazga1.MediaTest;
import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.SeasonAdapter;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentSeasonsBinding;

public class SeasonsFragment extends Fragment {
    private FragmentSeasonsBinding binding;
    private VideoDbHandler miazgaVideoDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MediaTest mediaTest = new MediaTest(view);
        miazgaVideoDb = new VideoDbHandler(view.getContext());
        miazgaVideoDb.fillDatabase(mediaTest.getVideoList());

        RecyclerView seasonRecyclerView = view.findViewById(R.id.season_recycler_view);
        seasonRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SeasonAdapter seasonAdapter = new SeasonAdapter(view.getContext(),
                miazgaVideoDb,
                SeasonsFragment.this);
        seasonRecyclerView.setAdapter(seasonAdapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}