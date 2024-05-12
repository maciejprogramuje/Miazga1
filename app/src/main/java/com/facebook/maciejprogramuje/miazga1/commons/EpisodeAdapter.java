package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.views.EpisodesFragment;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeItemViewHolder> {
    private final VideoDbHandler miazgaVideoDb;
    Context context;
    private final int seasonNumber;
    EpisodesFragment episodesFragment;

    public EpisodeAdapter(Context context, int seasonNumber, EpisodesFragment episodesFragment) {
        this.context = context;
        this.seasonNumber = seasonNumber;
        this.episodesFragment = episodesFragment;
        this.miazgaVideoDb = new VideoDbHandler(context);
    }

    @NonNull
    @Override
    public EpisodeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.episode_item, viewGroup, false);

        return new EpisodeItemViewHolder(view, episodesFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeItemViewHolder episodeItemViewHolder, int position) {
        episodeItemViewHolder.setEpisodeItem(position, seasonNumber);
    }

    @Override
    public int getItemCount() {
        return miazgaVideoDb.getAllEpisodesFromSeasonFromDb(seasonNumber).size();
    }

    @Override
    public void onViewRecycled(@NonNull EpisodeItemViewHolder holder) {
        holder.watchedCheckbox.setOnCheckedChangeListener(null);
        super.onViewRecycled(holder);
    }
}
