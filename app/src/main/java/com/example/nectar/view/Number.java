package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nectar.R;

public class Number extends AppCompatActivity {

    ImageView back, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number);

        back = findViewById(R.id.back);
        back.setOnClickListener(view->{
            finish();
        });

        next = findViewById(R.id.next);
        next.setOnClickListener(view->{
            Intent intent = new Intent(Number.this, LogIn.class);
            startActivity(intent);
        });
    }
}