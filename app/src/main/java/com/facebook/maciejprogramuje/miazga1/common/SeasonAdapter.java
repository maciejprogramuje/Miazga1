package com.facebook.maciejprogramuje.miazga1.common;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.model.Season;
import com.facebook.maciejprogramuje.miazga1.viewholder.SeasonItemViewHolder;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonItemViewHolder> {
    Context context;
    List<Season> seasons;

    public SeasonAdapter(Context context, List<Season> seasons) {
        this.context = context;
        this.seasons = seasons;
    }

    @NonNull
    @Override
    public SeasonItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.season_item, viewGroup, false);
        return new SeasonItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonItemViewHolder seasonItemViewHolder, int position) {
        Season season = seasons.get(position);
        seasonItemViewHolder.setSeasonItem(season);
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }
}
