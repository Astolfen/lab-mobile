package com.example.lab4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.adapter.SimpleStringAdapter;
import com.example.lab4.database.AppDatabase;
import com.example.lab4.entity.CharacterEntity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView filmsLabel, shortFilmsLabel, tvShowsLabel,
            videoGamesLabel, parkAttractionsLabel;

    private int characterId;
    private CharacterEntity character;

    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        filmsLabel = findViewById(R.id.filmsLabel);
        shortFilmsLabel = findViewById(R.id.shortFilmsLabel);
        tvShowsLabel = findViewById(R.id.tvShowsLabel);
        videoGamesLabel = findViewById(R.id.videoGamesLabel);
        parkAttractionsLabel = findViewById(R.id.parkAttractionsLabel);

        characterId = getIntent().getIntExtra("character_id", -1);

        if (characterId != -1) {
            loadCharacterFromDb(characterId);
        } else {
            Log.e(TAG, "character id -1");
        }
    }

    private void loadCharacterFromDb(int characterId) {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            character = db.characterDao().getCharacterById(characterId);
            Log.w("DA", "thread");
            runOnUiThread(() -> {
                if (character != null) {
                    displayCharacterDetails(character);
                }
            });
        }).start();
    }

    private void displayCharacterDetails(CharacterEntity character) {
        TextView characterName = findViewById(R.id.profileNamePerson);
        ImageView characterImage = findViewById(R.id.profileCharacterImage);

        characterName.setText(character.getName());
        Picasso.get().load(character.getImageUrl()).into(characterImage);


        setupRecyclerView(R.id.filmsRecyclerView, filmsLabel, character.getMovies());
        setupRecyclerView(R.id.shortFilmsRecyclerView, shortFilmsLabel, character.getShortFilms());
        setupRecyclerView(R.id.tvShowsRecyclerView, tvShowsLabel, character.getTvShows());
        setupRecyclerView(R.id.videoGamesRecyclerView, videoGamesLabel, character.getVideoGames());
        setupRecyclerView(R.id.parkAttractionsRecyclerView, parkAttractionsLabel, character.getParkAttractions());

    }

    private void setupRecyclerView(int recyclerViewId, TextView label, String data) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        data = data.substring(1, data.length() - 1);
        if (data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            label.setVisibility(View.GONE);
        } else {
            List<String> items = Arrays.asList(data.split(","));
            recyclerView.setVisibility(View.VISIBLE);
            label.setVisibility(View.VISIBLE);

            SimpleStringAdapter adapter = new SimpleStringAdapter(items, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
}

