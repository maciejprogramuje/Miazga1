package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.views.EpisodesFragment;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {
    TextView episodeNumberTextView;

    public EpisodeItemViewHolder(View itemView, EpisodesFragment episodesFragment) {
        super(itemView);
        episodeNumberTextView = itemView.findViewById(R.id.episode_number_text_view);

        itemView.setOnClickListener(view -> {
            String episodeNumber = episodeNumberTextView.getText().toString();

            Toast.makeText(view.getContext(), "Clicked episode no." + episodeNumber, Toast.LENGTH_SHORT).show();
        });
    }

    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(Episode episode) {
        episodeNumberTextView.setText(episode.getEpisodeName());
    }
}
