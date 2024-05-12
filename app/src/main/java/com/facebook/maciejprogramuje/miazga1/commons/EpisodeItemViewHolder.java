package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
    ImageView thumbnailImageView;
    String videoPathString;
    int episodeId;
    int currentPositionOfMovie;

    public EpisodeItemViewHolder(View itemView, EpisodesFragment episodesFragment) {
        super(itemView);

        episodeNumberTextView = itemView.findViewById(R.id.episode_number_text_view);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);
        episodePropertiesTextView = itemView.findViewById(R.id.episode_properties_text_view);
        watchedCheckbox = itemView.findViewById(R.id.watched_checkbox);
        thumbnailImageView = itemView.findViewById(R.id.thumbnail_image_view);

        itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("videoPathString", videoPathString);
            bundle.putInt("episodeId", episodeId);
            bundle.putInt("currentPositionOfMovie", currentPositionOfMovie);

            NavHostFragment.findNavController(episodesFragment)
                    .navigate(R.id.action_EpisodesFragment_to_MovieFragment, bundle);
        });
    }

    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, int seasonNumber) {
        try (VideoDbHandler miazgaVideoDb = new VideoDbHandler(itemView.getContext())) {

            List<Episode> allEpisodesFromSeason = miazgaVideoDb.getAllEpisodesFromSeasonFromDb(seasonNumber);
            Episode episode = allEpisodesFromSeason.get(position);

            videoPathString = episode.getUri().toString();
            episodeId = episode.getEpisodeId();
            currentPositionOfMovie =  episode.getCurrentPosition();

            Bitmap videoThumbnail = null;
            videoThumbnail = itemView.getContext().getApplicationContext().getContentResolver().loadThumbnail(
                    episode.getUri(),
                    new Size(64, 64),
                    null);

            thumbnailImageView.setImageBitmap(videoThumbnail);
            episodeNumberTextView.setText("Odcinek " + episode.getEpisodeNumber());
            episodeNameTextView.setText(episode.getName());
            episodePropertiesTextView.setText("Sezon: " + episode.getSeasonNumber()
                    + ", czas: " + millisecondsToMinutes(episode.getDuration())
            );

            watchedCheckbox.setChecked(episode.getWatched() == 1);

            watchedCheckbox.setOnCheckedChangeListener((checkbox, isChecked) -> {
                if (isChecked) {
                    miazgaVideoDb.updateEpisodeWatchedInDb(episode.getEpisodeId(), 1);
                } else {
                    miazgaVideoDb.updateEpisodeWatchedInDb(episode.getEpisodeId(), 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String millisecondsToMinutes(int milliseconds) {
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds) + " min "
                + (TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60) + " sec";
    }

}
