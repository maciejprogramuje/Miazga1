package com.facebook.maciejprogramuje.miazga1.commons;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonNameTextView;
    int position;

    public SeasonItemViewHolder(View itemView, SeasonsFragment seasonsFragment) {
        super(itemView);
        seasonNameTextView = itemView.findViewById(R.id.seasonNameTextView);

        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("seasonPosition", position);

            NavHostFragment.findNavController(seasonsFragment)
                    .navigate(R.id.action_SeasonsFragment_to_EpisodesFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(int position, VideoDbHandler db) {
        this.position = position;
        seasonNameTextView.setText("Sezon:" + db.getSeason(position).getSeasonNumber());
    }
}
