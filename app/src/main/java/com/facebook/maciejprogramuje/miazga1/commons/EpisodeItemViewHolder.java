package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
    Uri videoPathUri;

    public EpisodeItemViewHolder(View itemView) {
        super(itemView);

        episodeNumberTextView = itemView.findViewById(R.id.episode_number_text_view);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);
        episodePropertiesTextView = itemView.findViewById(R.id.episode_properties_text_view);
        watchedCheckbox = itemView.findViewById(R.id.watched_checkbox);

        itemView.setOnClickListener(view -> {
            String episodeNumber = episodePropertiesTextView.getText().toString();

            Toast.makeText(view.getContext(), "Clicked episode no." + episodeNumber, Toast.LENGTH_SHORT).show();

            playMovie(view);
        });
    }

    private void playMovie(View view) {
        VideoView videoView = (VideoView) view.getRootView().findViewById(R.id.video_view);
        MediaController mc = new MediaController(view.getContext());
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.setVideoURI(videoPathUri);

        videoView.start();
    }

    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, int seasonNumber) {
        try (VideoDbHandler miazgaVideoDb = new VideoDbHandler(itemView.getContext())) {

            List<Episode> allEpisodesFromSeason = miazgaVideoDb.getAllEpisodesFromSeason(seasonNumber);
            Episode episode = allEpisodesFromSeason.get(position);

            videoPathUri = episode.getUri();

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
}
