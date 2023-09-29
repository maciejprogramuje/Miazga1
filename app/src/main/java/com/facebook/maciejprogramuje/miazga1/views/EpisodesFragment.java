package com.facebook.maciejprogramuje.miazga1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.databinding.FragmentEpisodesBinding;

import java.util.Objects;

public class EpisodesFragment extends Fragment {
    private FragmentEpisodesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String seasonNumber = Objects.requireNonNull(getArguments()).getString("seasonNumber");

        binding.textviewEpisodesOfSeasonNo.setText(seasonNumber);

        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(EpisodesFragment.this)
                .navigate(R.id.action_EpisodesFragment_to_SeasonsFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}