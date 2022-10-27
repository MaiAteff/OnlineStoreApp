package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nectar.R;
import com.example.nectar.adapter.CartListAdapter;

import java.text.DecimalFormat;

public class Cart extends AppCompatActivity {


    RecyclerView recyclerView;
    public static CartListAdapter cartAdapter;
    TextView total;
    float total1 = 0;
    DecimalFormat dfrmt = new DecimalFormat();
    ImageView shop, explore, favourite, account, goToCheckOut;
    RelativeLayout bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        shop = findViewById(R.id.shop);
        explore = findViewById(R.id.explore);
        favourite = findViewById(R.id.favourite);
        account = findViewById(R.id.account);
        goToCheckOut = findViewById(R.id.goToCheckOut);
        bottom = findViewById(R.id.bottom);

        shop.setOnClickListener(view -> {
            Intent intent = new Intent(Cart.this, HomePage.class);
            startActivity(intent);
        });

        explore.setOnClickListener(view->{
            Intent intent = new Intent(Cart.this, Explore.class);
            startActivity(intent);
        });

        favourite.setOnClickListener(view->{
            Intent intent = new Intent(Cart.this, Favourites.class);
            startActivity(intent);
        });

        account.setOnClickListener(view->{
            Intent intent = new Intent(Cart.this, Account.class);
            startActivity(intent);
        });



        dfrmt.setMaximumFractionDigits(2);
        HomePage.cartOpened = true;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (!HomePage.cartData.isEmpty()){
            cartAdapter = new CartListAdapter(Cart.this,HomePage.cartData);
            recyclerView.setLayoutManager(new GridLayoutManager(Cart.this,1, GridLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(cartAdapter);
            cartAdapter.setClickListener(new CartListAdapter.SubClickListener() {
                @Override
                public void onSubClick(View view, int position) {
                    if (HomePage.cartData.get(position).count > 1){
                        HomePage.cartData.get(position).count -= 1;
                        cartAdapter.notifyItemChanged(position);
                        Total();
                    }
                }
            });

            cartAdapter.setClickListener(new CartListAdapter.AddClickListener() {
                @Override
                public void onAddClick(View view, int position) {
                    HomePage.cartData.get(position).count += 1;
                    cartAdapter.notifyItemChanged(position);
                    Total();
                }
            });

            cartAdapter.setClickListener(new CartListAdapter.DeleteClickListener() {
                @Override
                public void onDeleteClick(View view, int position) {
                    HomePage.cartData.remove(position);
                    cartAdapter.notifyItemRemoved(position);
                    Total();
                }
            });

        }

        total = findViewById(R.id.total);
        Total();

        goToCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Checkout checkout = new Checkout(total.getText().toString(),Cart.this);
                checkout.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
            }
        });
    }

    void Total(){
        total1 = 0;
        for (int i = 0 ; i < HomePage.cartData.size() ; i++){
            total1 += (HomePage.cartData.get(i).price * HomePage.cartData.get(i).count);
        }
        total.setText("$" + String.valueOf(dfrmt.format(total1)));
    }


}