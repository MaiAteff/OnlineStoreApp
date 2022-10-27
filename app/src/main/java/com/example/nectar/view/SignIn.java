package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.nectar.R;

public class SignIn extends AppCompatActivity {

    LinearLayout number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        number = findViewById(R.id.number);
        number.setOnClickListener(view->{
            Intent intent = new Intent(SignIn.this, Number.class);
            startActivity(intent);
        });
    }
}