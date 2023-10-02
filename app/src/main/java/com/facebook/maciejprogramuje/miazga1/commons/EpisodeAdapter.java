package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Episode;
import com.facebook.maciejprogramuje.miazga1.views.EpisodesFragment;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeItemViewHolder> {
    Context context;
    List<Episode> episodes;
    EpisodesFragment episodesFragment;

    public EpisodeAdapter(Context context, List<Episode> episodes, EpisodesFragment episodesFragment) {
        this.context = context;
        this.episodes = episodes;
        this.episodesFragment = episodesFragment;
    }

    @NonNull
    @Override
    public EpisodeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.episode_item, viewGroup, false);
        return new EpisodeItemViewHolder(view, episodesFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeItemViewHolder episodeItemViewHolder, int position) {
        Episode episode = episodes.get(position);
        episodeItemViewHolder.setEpisodeItem(episode);
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
