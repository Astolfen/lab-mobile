package com.example.lab3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.adapter.SimpleStringAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView profileNamePerson;
    private ImageView profileCharacterImage;
    private TextView filmsLabel, shortFilmsLabel, tvShowsLabel,
            videoGamesLabel, parkAttractionsLabel;
    //=====================
    private RecyclerView filmsRecyclerView, shortFilmsRecyclerView, tvShowsRecyclerView,
            videoGamesRecyclerView, parkAttractionsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        profileNamePerson = findViewById(R.id.profileNamePerson);
        profileCharacterImage = findViewById(R.id.profileCharacterImage);

        filmsLabel = findViewById(R.id.filmsLabel);
        shortFilmsLabel = findViewById(R.id.shortFilmsLabel);
        tvShowsLabel = findViewById(R.id.tvShowsLabel);
        videoGamesLabel = findViewById(R.id.videoGamesLabel);
        parkAttractionsLabel = findViewById(R.id.parkAttractionsLabel);

        filmsRecyclerView = findViewById(R.id.filmsRecyclerView);
        shortFilmsRecyclerView = findViewById(R.id.shortFilmsRecyclerView);
        tvShowsRecyclerView = findViewById(R.id.tvShowsRecyclerView);
        videoGamesRecyclerView = findViewById(R.id.videoGamesRecyclerView);
        parkAttractionsRecyclerView = findViewById(R.id.parkAttractionsRecyclerView);


        Intent intent = getIntent();
        String name = intent.getStringExtra("profileNamePerson");
        String imgUrl = intent.getStringExtra("profileCharacterImage");
        ArrayList<String> films = intent.getStringArrayListExtra("films");
        ArrayList<String> shortFilms = intent.getStringArrayListExtra("shortFilms");
        ArrayList<String> tvShows = intent.getStringArrayListExtra("tvShows");
        ArrayList<String> videoGames = intent.getStringArrayListExtra("videoGames");
        ArrayList<String> parkAttractions = intent.getStringArrayListExtra("parkAttractions");

        profileNamePerson.setText(name);
        Picasso.get()
                .load(imgUrl)
                .into(profileCharacterImage);


        setupRecyclerView(filmsRecyclerView,filmsLabel,films);
        setupRecyclerView(shortFilmsRecyclerView,shortFilmsLabel,shortFilms);
        setupRecyclerView(tvShowsRecyclerView,tvShowsLabel,tvShows);
        setupRecyclerView(videoGamesRecyclerView,videoGamesLabel,videoGames);
        setupRecyclerView(parkAttractionsRecyclerView,parkAttractionsLabel,parkAttractions);
    }


    private void setupRecyclerView(RecyclerView recyclerView, TextView label, ArrayList<String> data) {
        if (data == null || data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            label.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            label.setVisibility(View.VISIBLE);

            SimpleStringAdapter adapter = new SimpleStringAdapter(data, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
}
