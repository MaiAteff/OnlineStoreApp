package com.example.nectar.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nectar.R;
import com.example.nectar.adapter.CategoryListAdapter;
import com.example.nectar.viewModel.ViewModel;

public class Explore extends AppCompatActivity {

    ViewModel model;
    RecyclerView recyclerView;
    CategoryListAdapter adapter;
    ImageView shop, explore, cart, favourite, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore);

        shop = findViewById(R.id.shop);
        explore = findViewById(R.id.explore);
        cart = findViewById(R.id.cart);
        favourite = findViewById(R.id.favourite);
        account = findViewById(R.id.account);

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(Explore.this, Cart.class);
            startActivity(intent);
        });

        shop.setOnClickListener(view->{
            Intent intent = new Intent(Explore.this,HomePage.class);
            startActivity(intent);
        });

        favourite.setOnClickListener(view->{
            Intent intent = new Intent(Explore.this,Favourites.class);
            startActivity(intent);
        });

        account.setOnClickListener(view->{
            Intent intent = new Intent(Explore.this, Account.class);
            startActivity(intent);
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new CategoryListAdapter(Explore.this, Splash.categories, Splash.imgCategories);
        recyclerView.setLayoutManager(new GridLayoutManager(Explore.this,2, GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new CategoryListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Explore.this, CategoryPage.class);
                intent.putExtra("category",Splash.categories.get(position));
                startActivity(intent);
            }
        });

    }

}