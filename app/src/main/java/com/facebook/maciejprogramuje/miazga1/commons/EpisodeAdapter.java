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
    Context context;
    EpisodesFragment episodesFragment;
    VideoDbHandler db;

    public EpisodeAdapter(Context context, VideoDbHandler db, EpisodesFragment episodesFragment) {
        this.context = context;
        this.db = db;
        this.episodesFragment = episodesFragment;
    }

    @NonNull
    @Override
    public EpisodeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.episode_item, viewGroup, false);
        return new EpisodeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeItemViewHolder episodeItemViewHolder, int position) {
        episodeItemViewHolder.setEpisodeItem(position, db);
    }

    @Override
    public int getItemCount() {
        return db.getEpisodesCount();
    }
}
