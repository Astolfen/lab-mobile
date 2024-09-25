package com.example.lab4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.R;

import java.util.List;

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.StringViewHolder> {

    private List<String> items;
    private Context context;

    public SimpleStringAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public SimpleStringAdapter.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new SimpleStringAdapter.StringViewHolder(layoutInflater.inflate(R.layout.simple_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleStringAdapter.StringViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StringViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
