package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.Season;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;

import java.util.List;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {
    TextView episodeNameTextView;
    CheckBox watchedCheckbox;
    int position;
    Episode episode;
    VideoDbHandler miazgaVideoDb;

    public EpisodeItemViewHolder(View itemView) {
        super(itemView);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);
        watchedCheckbox = itemView.findViewById(R.id.watched_checkbox);

        miazgaVideoDb = new VideoDbHandler(itemView.getContext());

        itemView.setOnClickListener(view -> {
            String episodeNumber = episodeNameTextView.getText().toString();

            Toast.makeText(view.getContext(), "Clicked episode no." + episodeNumber, Toast.LENGTH_SHORT).show();
        });

        watchedCheckbox.setOnCheckedChangeListener((checkbox, isChecked) -> {
            miazgaVideoDb.updateEpisodeWatched(episode.getEpisodeId(), isChecked);

            miazgaVideoDb.getEpisode(episode.getEpisodeId());
        });
    }


    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, List<Episode> episodes) {
        this.position = position;
        this.episode = episodes.get(position);

        episodeNameTextView.setText("Epizod:"
                + episode.getEpisodeName()
        );

        watchedCheckbox.setChecked(episode.isWatched());
    }
}
