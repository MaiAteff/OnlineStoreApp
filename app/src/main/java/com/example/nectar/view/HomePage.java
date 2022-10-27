package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.nectar.R;
import com.example.nectar.adapter.MyListAdapter;
import com.example.nectar.model.CartData;
import com.example.nectar.model.Product;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    public static boolean cartOpened = false;
    public static boolean favouriteOpened = false;
    public static ArrayList<CartData> cartData = new ArrayList<>();
    public static ArrayList<Product> favouriteData = new ArrayList<>();
    RecyclerView recyclerView;
    MyListAdapter adapter;
    ImageView shop, explore, cart, favourite, account;
    RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        fun();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fun();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fun();
    }

    void fun(){
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        shop = findViewById(R.id.shop);
        explore = findViewById(R.id.explore);
        cart = findViewById(R.id.cart);
        favourite = findViewById(R.id.favourite);
        account = findViewById(R.id.account);

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, Cart.class);
            startActivity(intent);
        });

        explore.setOnClickListener(view->{
            Intent intent = new Intent(HomePage.this, Explore.class);
            startActivity(intent);
        });

        favourite.setOnClickListener(view->{
            Intent intent = new Intent(HomePage.this, Favourites.class);
            startActivity(intent);
        });

        account.setOnClickListener(view->{
            Intent intent = new Intent(HomePage.this, Account.class);
            startActivity(intent);
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new MyListAdapter(HomePage.this, Splash.products);
        recyclerView.setLayoutManager(new GridLayoutManager(HomePage.this,2, GridLayoutManager.VERTICAL,false));

        adapter.setClickListener(new MyListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(HomePage.this, ProductDetail.class);
                intent.putExtra("category",Splash.products.get(position).category);
                intent.putExtra("id",Splash.products.get(position).id);
                intent.putExtra("title",Splash.products.get(position).title);
                intent.putExtra("price",Splash.products.get(position).price);
                intent.putExtra("image",Splash.products.get(position).image);
                intent.putExtra("rate",Splash.products.get(position).rating.rate);
                intent.putExtra("description",Splash.products.get(position).description);
                intent.putExtra("count",Splash.products.get(position).rating.count);
                intent.putExtra("fav",false);
                startActivity(intent);
            }
        });

        adapter.setClickListener(new MyListAdapter.addClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                cartData.add(new CartData(Splash.products.get(position).image,Splash.products.get(position).title,
                        1,Splash.products.get(position).price));
                if(cartOpened){
                    Cart.cartAdapter.notifyItemInserted(cartData.size()-1);
                }
            }
        });

        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
    }
}