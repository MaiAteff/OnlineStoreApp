package com.example.nectar.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nectar.R;
import com.example.nectar.model.CartData;
import com.example.nectar.model.Product;
import com.example.nectar.model.Rating;
import com.example.nectar.view.Cart;
import com.example.nectar.view.Favourites;
import com.example.nectar.view.HomePage;

public class ProductDetail extends AppCompatActivity {

    ImageView back, img, favourite, add, sub, drop, drop1, addToCart;
    TextView title, price, description, review;
    EditText count;
    boolean fav = false, drop_ = false, drop1_ = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        img = findViewById(R.id.img);
        Glide.with(this).load(getIntent().getStringExtra("image")).into(img);

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            finish();
        });

        title = findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));

        favourite = findViewById(R.id.favourite);

        if(getIntent().getBooleanExtra("fav",true) == true){
            fav = true;
            favourite.setColorFilter(this.getResources().getColor(R.color.red));
        }else if(getIntent().getBooleanExtra("fav",true) == false){
            fav = false;
            favourite.setColorFilter(this.getResources().getColor(R.color.fav));
        }

        favourite.setOnClickListener(view->{
            if (!fav){
                favourite.setColorFilter(this.getResources().getColor(R.color.red));
                fav = true;
                HomePage.favouriteData.add(new Product(
                        getIntent().getIntExtra("id",-1)
                        ,getIntent().getStringExtra("title")
                        ,getIntent().getFloatExtra("price",0)
                        ,getIntent().getStringExtra("description")
                        ,getIntent().getStringExtra("category")
                        ,getIntent().getStringExtra("image")
                        ,new Rating(getIntent().getFloatExtra("price",0)
                                ,getIntent().getIntExtra("id",-1))
                ));
                if(HomePage.favouriteOpened){
                    Favourites.favouriteAdapter.notifyItemInserted(HomePage.favouriteData.size()-1);
                }
            }else{
                favourite.setColorFilter(this.getResources().getColor(R.color.fav));
                fav = false;
                HomePage.favouriteData.remove(HomePage.favouriteData.size()-1);
                if(HomePage.favouriteOpened){
                    Favourites.favouriteAdapter.notifyItemRemoved(HomePage.favouriteData.size()-1);
                }
            }
        });

        count = findViewById(R.id.count);

        add = findViewById(R.id.add);
        add.setOnClickListener(view -> {
            count.setText(String.valueOf(Integer.valueOf(count.getText().toString())+1));
        });

        sub = findViewById(R.id.sub);
        sub.setOnClickListener(view->{
            if (Integer.valueOf(count.getText().toString()) > 1){
                count.setText(String.valueOf(Integer.valueOf(count.getText().toString())-1));
            }
        });

        price = findViewById(R.id.price);
        price.setText("$" + String.valueOf(getIntent().getFloatExtra("price",0)));

        description = findViewById(R.id.description);
        description.setText(getIntent().getStringExtra("description"));

        drop = findViewById(R.id.drop);
        drop.setOnClickListener(view->{
            if (!drop_){
                drop.setRotation(90);
                description.setVisibility(View.VISIBLE);
                drop_ = true;
            }else{
                drop.setRotation(0);
                description.setVisibility(View.GONE);
                drop_ = false;
            }
        });

        review = findViewById(R.id.review);
        review.setText(String.valueOf(getIntent().getFloatExtra("rate",5))
                + "   ( " + getIntent().getIntExtra("count",0) + " reviews )" );

        drop1 = findViewById(R.id.drop1);
        drop1.setOnClickListener(view->{
            if (!drop1_){
                drop1.setRotation(90);
                review.setVisibility(View.VISIBLE);
                drop1_ = true;
            }else{
                drop1.setRotation(0);
                review.setVisibility(View.GONE);
                drop1_ = false;
            }
        });

        addToCart = findViewById(R.id.addToCart);
        addToCart.setOnClickListener(view-> {
            HomePage.cartData.add(new CartData(getIntent().getStringExtra("image")
                        ,getIntent().getStringExtra("title")
                        , Integer.valueOf(count.getText().toString())
                        ,getIntent().getFloatExtra("price",0)));
            if(HomePage.cartOpened){
                Cart.cartAdapter.notifyItemInserted(HomePage.cartData.size()-1);
            }
        });
    }
}