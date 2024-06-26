package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonItemViewHolder> {
    Context context;
    SeasonsFragment seasonsFragment;

    public SeasonAdapter(Context context, SeasonsFragment seasonsFragment) {
        this.context = context;
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
        seasonItemViewHolder.setSeasonItem(position);
    }

    @Override
    public int getItemCount() {
        try (VideoDbHandler miazgaVideoDb = new VideoDbHandler(context)) {
            return miazgaVideoDb.getAllSeasonsFromDb().size();
        }
    }
}
