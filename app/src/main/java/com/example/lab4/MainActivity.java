package com.example.lab4;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.adapter.CharacterAdapter;
import com.example.lab4.database.AppDatabase;
import com.example.lab4.entity.CharacterEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<CharacterEntity> characterList = new ArrayList<>();
    private OkHttpClient client;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.charactersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        characterAdapter = new CharacterAdapter(characterList, this);
        recyclerView.setAdapter(characterAdapter);

        loadCharacters();
    }

    private void loadCharacters() {
        client = new OkHttpClient();
        int countPage = 149;//149
        for (int i = 1; i <= countPage; i++) {
            String url = "https://api.disneyapi.dev/character?page=" + i;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> {
                        Log.e(TAG, "Failed to fetch data", e);
                        loadCharactersFromDb();
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String jsonResponse = response.body().string();
                        Log.d(TAG, "Response: " + jsonResponse);

                        try {
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            List<CharacterEntity> characters = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject characterObject = dataArray.getJSONObject(i);

                                String name = characterObject.getString("name");
                                String imageUrl = "https://yt3.googleusercontent.com/iRLpuvr-WoAkDmOmXQiVnk7Gf4knJ6_OmIqZRmal4FeFxwbPLkMwIWm4QZlvH9t2GojQWZ4P=s900-c-k-c0x00ffffff-no-rj";
                                if (characterObject.has("imageUrl")) {
                                    imageUrl = characterObject.getString("imageUrl");
                                }

                                CharacterEntity character = new CharacterEntity();
                                character.setName(name);
                                character.setImageUrl(imageUrl);
                                character.setMovies(characterObject.optString("films"));
                                character.setShortFilms(characterObject.optString("shortFilms"));
                                character.setTvShows(characterObject.optString("tvShows"));
                                character.setVideoGames(characterObject.optString("videoGames"));
                                character.setParkAttractions(characterObject.optString("parkAttractions"));

                                characters.add(character);
                            }

                            runOnUiThread(() -> {
                                updateRecyclerView(characters);
                                saveCharactersToDb(characters);
                            });

                        } catch (JSONException e) {
                            Log.e(TAG, "Failed to parse JSON", e);
                        }
                    }
                }
            });
        }
    }

    private void updateRecyclerView(List<CharacterEntity> characters) {
        characterList.addAll(characters);
        characterAdapter.notifyDataSetChanged();
    }

    private void saveCharactersToDb(List<CharacterEntity> characters) {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            for (CharacterEntity character : characters) {
                character.setId((int) db.characterDao().insertCharacter(character));
            }

        }).start();
    }

    private void loadCharactersFromDb() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            List<CharacterEntity> characters = db.characterDao().getAllCharacters();
            runOnUiThread(() -> {
                if (characters != null && !characters.isEmpty()) {
                    updateRecyclerView(characters);
                } else {
                    Log.e(TAG, "No data available");
                }
            });
        }).start();
    }

}