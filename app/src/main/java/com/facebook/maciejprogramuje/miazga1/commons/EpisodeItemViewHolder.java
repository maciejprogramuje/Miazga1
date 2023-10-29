package com.facebook.maciejprogramuje.miazga1.commons;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.views.EpisodesFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {
    TextView episodePropertiesTextView;
    TextView episodeNameTextView;
    TextView episodeNumberTextView;
    CheckBox watchedCheckbox;
    String videoPathString;

    public EpisodeItemViewHolder(View itemView, EpisodesFragment episodesFragment) {
        super(itemView);

        episodeNumberTextView = itemView.findViewById(R.id.episode_number_text_view);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);
        episodePropertiesTextView = itemView.findViewById(R.id.episode_properties_text_view);
        watchedCheckbox = itemView.findViewById(R.id.watched_checkbox);

        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("videoPathString", videoPathString);

            NavHostFragment.findNavController(episodesFragment)
                    .navigate(R.id.action_EpisodesFragment_to_MovieFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, int seasonNumber) {
        try (VideoDbHandler miazgaVideoDb = new VideoDbHandler(itemView.getContext())) {

            List<Episode> allEpisodesFromSeason = miazgaVideoDb.getAllEpisodesFromSeason(seasonNumber);
            Episode episode = allEpisodesFromSeason.get(position);

            videoPathString = episode.getUri().toString();

            episodeNumberTextView.setText("Odcinek " + episode.getEpisodeNumber());
            episodeNameTextView.setText(episode.getName());
            episodePropertiesTextView.setText("Sezon: " + episode.getSeasonNumber()
                    + ", czas: " + millsecondsToMins(episode.getDuration())
            );

            watchedCheckbox.setChecked(episode.getWatched() == 1);

            watchedCheckbox.setOnCheckedChangeListener((checkbox, isChecked) -> {
                miazgaVideoDb.updateEpisodeWatched(episode.getEpisodeId(), isChecked);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String millsecondsToMins(int milliseconds) {
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds) + " min "
                + (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60) + " sec";
    }

    private int sizeDpToPixels(Context c, int sizeInDp) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (sizeInDp * scale + 0.5f);
    }
}
