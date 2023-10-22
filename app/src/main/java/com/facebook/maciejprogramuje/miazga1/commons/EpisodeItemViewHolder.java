package com.facebook.maciejprogramuje.miazga1.commons;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.R;
import com.facebook.maciejprogramuje.miazga1.models.VideoDbHandler;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {
    TextView episodeNameTextView;
    int position;

    public EpisodeItemViewHolder(View itemView) {
        super(itemView);
        episodeNameTextView = itemView.findViewById(R.id.episode_name_text_view);

        itemView.setOnClickListener(view -> {
            String episodeNumber = episodeNameTextView.getText().toString();

            Toast.makeText(view.getContext(), "Clicked episode no." + episodeNumber, Toast.LENGTH_SHORT).show();
        });
    }


    @SuppressLint("SetTextI18n")
    public void setEpisodeItem(int position, VideoDbHandler db) {
        this.position = position;
        episodeNameTextView.setText("Epizod:" + db.getEpisode(position).getEpisodeName());
    }
}
