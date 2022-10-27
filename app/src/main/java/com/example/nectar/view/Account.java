package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nectar.R;

public class Account extends AppCompatActivity {

    ImageView shop, explore, cart, favourite, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        shop = findViewById(R.id.shop);
        explore = findViewById(R.id.explore);
        cart = findViewById(R.id.cart);
        favourite = findViewById(R.id.favourite);
        logOut = findViewById(R.id.logOut);

        logOut.setOnClickListener(view->{
            Intent intent = new Intent(Account.this, LogIn.class);
            startActivity(intent);
        });

        shop.setOnClickListener(view -> {
            Intent intent = new Intent(Account.this, HomePage.class);
            startActivity(intent);
        });

        explore.setOnClickListener(view->{
            Intent intent = new Intent(Account.this, Explore.class);
            startActivity(intent);
        });

        favourite.setOnClickListener(view->{
            Intent intent = new Intent(Account.this, Favourites.class);
            startActivity(intent);
        });

        cart.setOnClickListener(view->{
            Intent intent = new Intent(Account.this, Cart.class);
            startActivity(intent);
        });
    }
}