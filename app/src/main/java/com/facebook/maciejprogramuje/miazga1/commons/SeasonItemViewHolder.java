package com.facebook.maciejprogramuje.miazga1.commons;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Season;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

import java.util.List;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonNameTextView;
    int position;
    int seasonNumber;
    Season season;

    public SeasonItemViewHolder(View itemView, SeasonsFragment seasonsFragment) {
        super(itemView);
        seasonNameTextView = itemView.findViewById(R.id.seasonNameTextView);

        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("seasonNumber", seasonNumber);

            NavHostFragment.findNavController(seasonsFragment)
                    .navigate(R.id.action_SeasonsFragment_to_EpisodesFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(int position, List<Season> seasons) {
        this.position = position;
        this.season = seasons.get(position);
        this.seasonNumber = season.getSeasonNumber();

        seasonNameTextView.setText("Sezon:" + season.getSeasonNumber()
                + ", " + season.getSeasonName()
                + ", " + season.getSeasonId()
        );
    }
}
