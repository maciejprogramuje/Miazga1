package com.facebook.maciejprogramuje.miazga1.viewholder;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.model.Season;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {
    TextView seasonNumberTextView;

    public SeasonItemViewHolder(View itemView) {
        super(itemView);
        seasonNumberTextView = itemView.findViewById(R.id.seasonNumberTextView);

        itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(),
                            "Clicked Season No." + seasonNumberTextView.getText(),
                            Toast.LENGTH_SHORT)
                    .show();

            /*NavHostFragment.findNavController( FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);*/

            /*NavHostFragment.findNavController( FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);*/
        });
    }

    @SuppressLint("SetTextI18n")
    public void setSeasonItem(Season season) {
        seasonNumberTextView.setText(Integer.toString(season.getSeasonNumber()));
    }
}
