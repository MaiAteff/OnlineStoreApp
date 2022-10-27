package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nectar.R;

public class OrderAccepted extends AppCompatActivity {

    ImageView backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_accepted);

        backToHome = findViewById(R.id.backToHome);
        backToHome.setOnClickListener(view->{
            Intent intent = new Intent(OrderAccepted.this, HomePage.class);
            startActivity(intent);
        });
    }
}