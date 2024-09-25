package com.example.lab5.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.R;
import com.example.lab5.RecyclerViewInterface;
import com.example.lab5.entity.CharacterEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    private List<CharacterEntity> characterList;
    private Context context;

    public CharacterAdapter(List<CharacterEntity> characterList, Context context,RecyclerViewInterface recyclerViewInterface) {
        this.characterList = characterList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false),recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        CharacterEntity character = characterList.get(position);
        holder.characterName.setText(character.getName());
        Picasso.get()
                .load(character.getImageUrl())
                .into(holder.characterImage);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView characterName;
        ImageView characterImage;

        public CharacterViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            characterName = itemView.findViewById(R.id.characterName);
            characterImage = itemView.findViewById(R.id.characterImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

