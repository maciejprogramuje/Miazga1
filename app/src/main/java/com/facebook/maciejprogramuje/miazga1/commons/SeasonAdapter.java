package com.facebook.maciejprogramuje.miazga1.commons;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.SeasonItem;
import com.facebook.maciejprogramuje.miazga1.views.SeasonsFragment;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonItemViewHolder> {
    Context context;
    List<SeasonItem> seasonItems;
    SeasonsFragment seasonsFragment;

    public SeasonAdapter(Context context, List<SeasonItem> seasonItems, SeasonsFragment seasonsFragment) {
        this.context = context;
        this.seasonItems = seasonItems;
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
        SeasonItem seasonItem = seasonItems.get(position);
        seasonItemViewHolder.setSeasonItem(seasonItem);
    }

    @Override
    public int getItemCount() {
        return seasonItems.size();
    }
}
