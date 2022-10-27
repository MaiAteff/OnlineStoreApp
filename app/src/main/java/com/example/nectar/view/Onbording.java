package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nectar.R;

public class Onbording extends AppCompatActivity {

    ImageView start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onbording);

        start = findViewById(R.id.start);
        start.setOnClickListener(view->{
            Intent intent = new Intent(Onbording.this, SignIn.class);
            startActivity(intent);
        });

    }
}