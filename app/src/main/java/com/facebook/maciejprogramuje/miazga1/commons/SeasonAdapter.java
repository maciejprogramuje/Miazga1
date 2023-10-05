package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.MovieDbHandler;
import com.facebook.maciejprogramuje.miazga1.models.Season;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonItemViewHolder> {
    Context context;
    MovieDbHandler db;
    SeasonsFragment seasonsFragment;

    public SeasonAdapter(Context context, MovieDbHandler db, SeasonsFragment seasonsFragment) {
        this.context = context;
        this.db = db;
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
        Season season = db.getSeason(position);
        seasonItemViewHolder.setSeasonItem(season);
    }

    @Override
    public int getItemCount() {
        return db.getSeasonsCount();
    }
}
