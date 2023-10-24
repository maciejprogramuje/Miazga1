package com.facebook.maciejprogramuje.miazga1.commons;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.Season;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

import java.util.List;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonSecondLineTextView;
    TextView seasonFirstLineTextView;
    int seasonNumber;

    public SeasonItemViewHolder(View itemView, SeasonsFragment seasonsFragment) {
        super(itemView);
        seasonFirstLineTextView = itemView.findViewById(R.id.season_first_line_textview);
        seasonSecondLineTextView = itemView.findViewById(R.id.season_second_line_textview);

        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("seasonNumber", seasonNumber);

            NavHostFragment.findNavController(seasonsFragment)
                    .navigate(R.id.action_SeasonsFragment_to_EpisodesFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(Season season, List<Episode> allEpisodesFromSeason) {
        this.seasonNumber = season.getSeasonNumber();

        seasonFirstLineTextView.setText("Sezon nr. " + season.getSeasonNumber());

        seasonSecondLineTextView.setText("Liczba odcinków:" + allEpisodesFromSeason.size());
    }
}
