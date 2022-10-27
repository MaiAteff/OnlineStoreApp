package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nectar.R;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }
}