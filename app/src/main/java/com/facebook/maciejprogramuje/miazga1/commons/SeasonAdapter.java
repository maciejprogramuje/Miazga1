package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.Season;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonItemViewHolder> {
    Context context;
    List<Season> seasons;
    SeasonsFragment seasonsFragment;

    public SeasonAdapter(Context context, List<Season> seasons, SeasonsFragment seasonsFragment) {
        this.context = context;
        this.seasons = seasons;
        this.seasonsFragment = seasonsFragment;
    }

    @NonNull
    @Override
    public SeasonItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.season_item, viewGroup, false);

        return new SeasonItemViewHolder(view, seasonsFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonItemViewHolder seasonItemViewHolder, int position) {
        seasonItemViewHolder.setSeasonItem(position, seasons);
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }
}
