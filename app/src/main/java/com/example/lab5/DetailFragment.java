package com.example.lab5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.adapter.SimpleStringAdapter;
import com.example.lab5.database.AppDatabase;
import com.example.lab5.entity.CharacterEntity;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class DetailFragment extends Fragment {
    private int id;

    private TextView filmsLabel, shortFilmsLabel, tvShowsLabel,
            videoGamesLabel, parkAttractionsLabel;

    private CharacterEntity character;
    private AppDatabase db;

    private static final String TAG = "DetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        id = bundle.getInt("id", -1);

        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filmsLabel = requireActivity().findViewById(R.id.filmsLabel);
        shortFilmsLabel = requireActivity().findViewById(R.id.shortFilmsLabel);
        tvShowsLabel = requireActivity().findViewById(R.id.tvShowsLabel);
        videoGamesLabel = requireActivity().findViewById(R.id.videoGamesLabel);
        parkAttractionsLabel = requireActivity().findViewById(R.id.parkAttractionsLabel);

        db = AppDatabase.getInstance(requireActivity());
        Log.w(TAG, Integer.toString(id));
        if (id != -1) {
            loadCharacterFromDb(id);
        } else {
            Log.e(TAG, "character id -1");
        }
    }

    private void loadCharacterFromDb(int characterId) {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance( requireActivity().getApplicationContext());
            character = db.characterDao().getCharacterById(characterId);
            Log.w("DA", "thread");
            requireActivity().runOnUiThread(() -> {
                if (character != null) {
                    displayCharacterDetails(character);
                }
            });
        }).start();
    }

    private void displayCharacterDetails(CharacterEntity character) {
        TextView characterName = requireActivity().findViewById(R.id.profileNamePerson);
        ImageView characterImage = requireActivity().findViewById(R.id.profileCharacterImage);

        characterName.setText(character.getName());
        Picasso.get().load(character.getImageUrl()).into(characterImage);


        setupRecyclerView(R.id.filmsRecyclerView, filmsLabel, character.getMovies());
        setupRecyclerView(R.id.shortFilmsRecyclerView, shortFilmsLabel, character.getShortFilms());
        setupRecyclerView(R.id.tvShowsRecyclerView, tvShowsLabel, character.getTvShows());
        setupRecyclerView(R.id.videoGamesRecyclerView, videoGamesLabel, character.getVideoGames());
        setupRecyclerView(R.id.parkAttractionsRecyclerView, parkAttractionsLabel, character.getParkAttractions());

    }

    private void setupRecyclerView(int recyclerViewId, TextView label, String data) {
        RecyclerView recyclerView = requireActivity().findViewById(recyclerViewId);
        data = data.substring(1, data.length() - 1);
        if (data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            label.setVisibility(View.GONE);
        } else {
            List<String> items = Arrays.asList(data.split(","));
            recyclerView.setVisibility(View.VISIBLE);
            label.setVisibility(View.VISIBLE);

            SimpleStringAdapter adapter = new SimpleStringAdapter(items, requireActivity());
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
            recyclerView.setAdapter(adapter);
        }
    }
}
