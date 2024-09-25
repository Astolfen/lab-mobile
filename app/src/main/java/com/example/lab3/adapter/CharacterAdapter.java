package com.example.lab3.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.DetailActivity;
import com.example.lab3.R;
import com.example.lab3.entity.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characterList;
    private Context context;

    public CharacterAdapter(List<Character> characterList, Context context) {
        this.characterList = characterList;
        this.context = context;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.characterName.setText(character.getName());
        Picasso.get()
                .load(character.getImageUrl())
                .into(holder.characterImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("profileNamePerson", character.getName());
            intent.putExtra("profileCharacterImage", character.getImageUrl());
            intent.putStringArrayListExtra("films",
                    new ArrayList<>(character.getFilms()));
            intent.putStringArrayListExtra("shortFilms",
                    new ArrayList<>(character.getShortFilms()));
            intent.putStringArrayListExtra("tvShows",
                    new ArrayList<>(character.getTvShows()));
            intent.putStringArrayListExtra("videoGames",
                    new ArrayList<>(character.getVideoGames()));
            intent.putStringArrayListExtra("parkAttractions",
                    new ArrayList<>(character.getParkAttractions()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView characterName;
        ImageView characterImage;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.characterName);
            characterImage = itemView.findViewById(R.id.characterImage);
        }
    }
}

