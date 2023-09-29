package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.SeasonItem;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonNumberTextView;

    public SeasonItemViewHolder(View itemView, SeasonsFragment seasonsFragment) {
        super(itemView);
        seasonNumberTextView = itemView.findViewById(R.id.seasonNumberTextView);

        itemView.setOnClickListener(view -> {
            String seasonNumber = seasonNumberTextView.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("seasonNumber", seasonNumber);

            NavHostFragment.findNavController(seasonsFragment)
                    .navigate(R.id.action_SeasonsFragment_to_EpisodesFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(SeasonItem seasonItem) {
        seasonNumberTextView.setText(Integer.toString(seasonItem.getSeasonNumber()));
    }
}