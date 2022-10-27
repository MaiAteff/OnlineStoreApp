package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nectar.R;
import com.example.nectar.adapter.FavouriteListAdapter;
import com.example.nectar.model.CartData;

public class Favourites extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView addAllToCart;
    ImageView shop, explore, cart, favourite, account;
    public static FavouriteListAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        shop = findViewById(R.id.shop);
        explore = findViewById(R.id.explore);
        cart = findViewById(R.id.cart);
        favourite = findViewById(R.id.favourite);
        account = findViewById(R.id.account);

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(Favourites.this, Cart.class);
            startActivity(intent);
        });

        explore.setOnClickListener(view->{
            Intent intent = new Intent(Favourites.this, Explore.class);
            startActivity(intent);
        });

        shop.setOnClickListener(view->{
            Intent intent = new Intent(Favourites.this, HomePage.class);
            startActivity(intent);
        });

        account.setOnClickListener(view->{
            Intent intent = new Intent(Favourites.this, Account.class);
            startActivity(intent);
        });

        addAllToCart = findViewById(R.id.addAllToCart);
        HomePage.favouriteOpened = true;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (!HomePage.favouriteData.isEmpty()){
            favouriteAdapter = new FavouriteListAdapter(Favourites.this,HomePage.favouriteData);
            recyclerView.setLayoutManager(new GridLayoutManager(Favourites.this,1, GridLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(favouriteAdapter);

            addAllToCart.setOnClickListener(view->{
                for(int i = 0 ; i < HomePage.favouriteData.size() ; i++){
                    HomePage.cartData.add(new CartData(
                            HomePage.favouriteData.get(i).image,
                            HomePage.favouriteData.get(i).title,1,
                            HomePage.favouriteData.get(i).price
                    ));
                }
            });

            favouriteAdapter.setClickListener(new FavouriteListAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(Favourites.this, ProductDetail.class);
                    intent.putExtra("category", HomePage.favouriteData.get(position).category);
                    intent.putExtra("id", HomePage.favouriteData.get(position).id);
                    intent.putExtra("title", HomePage.favouriteData.get(position).title);
                    intent.putExtra("price", HomePage.favouriteData.get(position).price);
                    intent.putExtra("image", HomePage.favouriteData.get(position).image);
                    intent.putExtra("rate", HomePage.favouriteData.get(position).rating.rate);
                    intent.putExtra("description", HomePage.favouriteData.get(position).description);
                    intent.putExtra("count", HomePage.favouriteData.get(position).rating.count);
                    intent.putExtra("fav",true);
                    startActivity(intent);
                }
            });
        }
    }
}