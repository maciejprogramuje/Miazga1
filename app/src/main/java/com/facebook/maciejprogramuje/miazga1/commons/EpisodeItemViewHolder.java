package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {
    TextView episodePropertiesTextView;
    TextView episodeNameTextView;
    TextView episodeNumberTextView;
    CheckBox watchedCheckbox;
    int position;
    Episode episode;
    VideoDbHandler miazgaVideoDb;

    public EpisodeItemViewHolder(View itemView) {
        super(itemView);

        episodeNumberTextView = itemView.findViewById(R.id.episode_number_text_view);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);
        episodePropertiesTextView = itemView.findViewById(R.id.episode_properties_text_view);
        watchedCheckbox = itemView.findViewById(R.id.watched_checkbox);

        miazgaVideoDb = new VideoDbHandler(itemView.getContext());

        itemView.setOnClickListener(view -> {
            String episodeNumber = episodePropertiesTextView.getText().toString();

            Toast.makeText(view.getContext(), "Clicked episode no." + episodeNumber, Toast.LENGTH_SHORT).show();
        });

        watchedCheckbox.setOnCheckedChangeListener((checkbox, isChecked) -> {
            miazgaVideoDb.updateEpisodeWatched(episode.getEpisodeId(), isChecked);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, List<Episode> episodes) {
        this.position = position;
        this.episode = episodes.get(position);

        episodeNumberTextView.setText("Odcinek " + episode.getEpisodeNumber());

        episodeNameTextView.setText(episode.getEpisodeName());

        episodePropertiesTextView.setText("Sezon: " + episode.getSeasonFK()
                + ", czas: " + millsecondsToMins(episode.getDuration())
        );

        watchedCheckbox.setChecked(episode.isWatched());
    }

    private String millsecondsToMins(int milliseconds) {
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds) + " min "
                + (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60) + " sec";
    }
}
