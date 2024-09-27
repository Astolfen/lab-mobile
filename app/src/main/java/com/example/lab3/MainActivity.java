package com.example.lab3;


import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.adapter.CharacterAdapter;
import com.example.lab3.entity.Character;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kotlin.text.UStringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> characterList = new ArrayList<>();
    private OkHttpClient client;
    private static final String TAG = "MainActivity";

    private String url = "https://api.disneyapi.dev/character";

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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                Log.w(TAG, "onScrollStateChanged");
//
//                int lastvisibleitemposition = layoutManager.findLastVisibleItemPosition();
//
//                if (lastvisibleitemposition == characterAdapter.getItemCount() - 1) {
//                    if (url != null) {
//                        loadCharacters();
//                    }
//                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.w(TAG, "onScrolled");

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (dy > 0) {  // Проверяем, если скролл вниз
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int visibleThreshold = 5;

                    if (url != null && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // Достигнут конец списка - загружаем больше данных
                        loadCharacters();
                    }
                }
            }
        });
        //        loadCharacters();
    }

    private void loadCharacters() {
        client = new OkHttpClient();
//        int countPage = 149;
//        for (int i = 1; i <= countPage; i++) {
//            String url = "https://api.disneyapi.dev/character?page=" + i;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed to fetch data", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    Log.d(TAG, "Response: " + jsonResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        JSONObject jsonInfo = jsonObject.getJSONObject("info");
                        String nextPageUrl = jsonInfo.optString("nextPage", null);
                        if (nextPageUrl != null) {
                            url = nextPageUrl;
                            Log.w(TAG, url);
                        } else {
                            Log.w(TAG, "nulllllll");
                            url = null;
                        }

                        JSONArray dataArray = jsonObject.getJSONArray("data");


                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject characterObject = dataArray.getJSONObject(i);

                            String name = characterObject.getString("name");
                            String imageUrl = "https://yt3.googleusercontent.com/iRLpuvr-WoAkDmOmXQiVnk7Gf4knJ6_OmIqZRmal4FeFxwbPLkMwIWm4QZlvH9t2GojQWZ4P=s900-c-k-c0x00ffffff-no-rj";
                            if (characterObject.has("imageUrl")) {
                                imageUrl = characterObject.getString("imageUrl");
                            }

                            JSONArray filmsArray = characterObject.optJSONArray("films");
                            List<String> films = new ArrayList<>();
                            if (filmsArray != null) {
                                for (int j = 0; j < filmsArray.length(); j++) {
                                    films.add(filmsArray.getString(j));
                                }
                            }

                            JSONArray shortFilmsArray = characterObject.optJSONArray("shortFilms");
                            List<String> shortFilms = new ArrayList<>();
                            if (shortFilmsArray != null) {
                                for (int j = 0; j < shortFilmsArray.length(); j++) {
                                    shortFilms.add(shortFilmsArray.getString(j));
                                }
                            }

                            JSONArray tvShowsArray = characterObject.optJSONArray("tvShows");
                            List<String> tvShows = new ArrayList<>();
                            if (tvShowsArray != null) {
                                for (int j = 0; j < tvShowsArray.length(); j++) {
                                    tvShows.add(tvShowsArray.getString(j));
                                }
                            }

                            JSONArray videoGamesArray = characterObject.optJSONArray("videoGames");
                            List<String> videoGames = new ArrayList<>();
                            if (videoGamesArray != null) {
                                for (int j = 0; j < videoGamesArray.length(); j++) {
                                    videoGames.add(videoGamesArray.getString(j));
                                }
                            }

                            JSONArray parkAttractionsArray = characterObject.optJSONArray("parkAttractions");
                            List<String> parkAttractions = new ArrayList<>();
                            if (parkAttractionsArray != null) {
                                for (int j = 0; j < parkAttractionsArray.length(); j++) {
                                    parkAttractions.add(parkAttractionsArray.getString(j));
                                }
                            }

                            Character character = new Character(name, imageUrl, films,
                                    shortFilms, tvShows, videoGames, parkAttractions);
                            characterList.add(character);
                        }

                        runOnUiThread(() -> {
                            characterAdapter.setCharacterList(characterList);
                            characterAdapter.notifyDataSetChanged();
                        });

                    } catch (JSONException e) {
                        Log.e(TAG, "Failed to parse JSON", e);
                    }
                }
            }
        });
    }
}
