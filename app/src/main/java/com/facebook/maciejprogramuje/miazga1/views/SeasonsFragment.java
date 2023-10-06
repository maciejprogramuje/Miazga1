package com.facebook.maciejprogramuje.miazga1.views;

import android.database.Cursor;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.commons.SeasonAdapter;
import com.facebook.maciejprogramuje.miazga1.models.MovieDbHandler;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentSeasonsBinding;
import com.facebook.maciejprogramuje.miazga1.models.Season;

import java.util.List;

public class SeasonsFragment extends Fragment {
    private FragmentSeasonsBinding binding;
    private MovieDbHandler dbSeasonsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSeasonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbSeasonsFragment = new MovieDbHandler(view.getContext());
        //db.fillDatabase();

        RecyclerView seasonRecyclerView = view.findViewById(R.id.season_recycler_view);
        seasonRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SeasonAdapter seasonAdapter = new SeasonAdapter(view.getContext(),
                dbSeasonsFragment,
                SeasonsFragment.this);
        seasonRecyclerView.setAdapter(seasonAdapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}