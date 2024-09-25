package com.example.lab5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.adapter.CharacterAdapter;
import com.example.lab5.database.AppDatabase;
import com.example.lab5.entity.CharacterEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CharacterFragment extends Fragment implements RecyclerViewInterface {

    private CharacterAdapter characterAdapter;
    private List<CharacterEntity> characterList = new ArrayList<>();

    private OkHttpClient client;

    private RecyclerView recyclerView;

    private AppDatabase db;

    private static final String TAG = "ListFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.list_characters, container, false);
        recyclerView = view.findViewById(R.id.charactersRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.w(TAG, "onViewCreated");

        characterAdapter = new CharacterAdapter(characterList, getActivity(), this);
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = AppDatabase.getInstance(getActivity());
        client = new OkHttpClient();

        loadCharacters();
    }

    private void loadCharacters() {
        int countPage = 149;//149
        for (int i = 1; i <= countPage; i++) {
            String url = "https://api.disneyapi.dev/character?page=" + i;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Failed to fetch data", e);
                    loadCharactersFromDb();
                    getActivity().runOnUiThread(() -> characterAdapter.notifyDataSetChanged());
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
                                String imageUrl = "https://i.pinimg.com/736x/1a/7a/99/1a7a998b053b9397ff911624c3c14d37--image-icon-flat-abs.jpg";
                                if (characterObject.has("imageUrl")) {
                                    imageUrl = characterObject.getString("imageUrl");
                                }

                                CharacterEntity character = new CharacterEntity();
                                character.setId(characterObject.getInt("_id"));
                                character.setName(name);
                                character.setImageUrl(imageUrl);
                                character.setMovies(characterObject.optString("films"));
                                character.setShortFilms(characterObject.optString("shortFilms"));
                                character.setTvShows(characterObject.optString("tvShows"));
                                character.setVideoGames(characterObject.optString("videoGames"));
                                character.setParkAttractions(characterObject.optString("parkAttractions"));

                                characterList.add(character);
                                db.characterDao().insertCharacter(character);
                            }
                            getActivity().runOnUiThread(() -> characterAdapter.notifyDataSetChanged());
                        } catch (JSONException e) {
                            Log.e(TAG, "Failed to parse JSON", e);
                        }
                    }
                }
            });
        }
    }

    private void loadCharactersFromDb() {
        characterList.clear();
        characterList.addAll(db.characterDao().getAllCharacters());
    }

    @Override
    public void onItemClick(int position) {
        DetailFragment newFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", characterList.get(position).getId());
        newFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.place_fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
