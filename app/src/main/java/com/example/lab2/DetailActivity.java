package com.example.lab2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView profileNamePerson;
    private TextView characterDetail;
    private ImageView profileCharacterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        profileNamePerson = findViewById(R.id.profileNamePerson);
        characterDetail = findViewById(R.id.characterDetail);
        profileCharacterImage = findViewById(R.id.profileCharacterImage);

        String name = getIntent().getStringExtra("profileNamePerson");
        String details = getIntent().getStringExtra("characterDetails");
        int idImage = getIntent().getIntExtra("profileCharacterImage",0);

        profileNamePerson.setText(name);
        characterDetail.setText(details);
        profileCharacterImage.setImageResource(idImage);
    }
}
