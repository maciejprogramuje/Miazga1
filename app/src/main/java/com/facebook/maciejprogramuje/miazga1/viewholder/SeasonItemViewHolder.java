package com.facebook.maciejprogramuje.miazga1.viewholder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.maciejprogramuje.miazga1.MainActivity;
import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.model.Season;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonNumberTextView;

    public SeasonItemViewHolder(View itemView) {
        super(itemView);
        seasonNumberTextView = itemView.findViewById(R.id.seasonNumberTextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                                "Clicked Season No." + seasonNumberTextView.getText(),
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(Season season) {
        seasonNumberTextView.setText(Integer.toString(season.getSeasonNumber()));
    }
}
