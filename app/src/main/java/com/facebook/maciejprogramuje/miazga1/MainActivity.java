package com.facebook.maciejprogramuje.miazga1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.maciejprogramuje.miazga1.common.SeasonAdapter;
import com.facebook.maciejprogramuje.miazga1.model.Season;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView seasonRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<Season> seasons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            seasons.add(new Season(i));
        }

        seasonRecyclerView = findViewById(R.id.season_recycler_view);
        seasonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SeasonAdapter seasonAdapter = new SeasonAdapter(this, seasons);
        seasonRecyclerView.setAdapter(seasonAdapter);
    }
}