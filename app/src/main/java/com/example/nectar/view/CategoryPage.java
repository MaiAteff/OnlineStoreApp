package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nectar.R;
import com.example.nectar.adapter.MyListAdapter;
import com.example.nectar.model.CartData;
import com.example.nectar.model.Product;
import com.example.nectar.viewModel.ViewModel;

import java.util.ArrayList;

public class CategoryPage extends AppCompatActivity {

    ViewModel model;
    RecyclerView recyclerView;
    MyListAdapter adapter;
    RelativeLayout progressBar;
    ImageView back;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_page);

        text = findViewById(R.id.text);
        text.setText(getIntent().getStringExtra("category").substring(0,1).toUpperCase()
                + getIntent().getStringExtra("category").substring(1));
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        model = new ViewModelProvider(this).get(ViewModel.class);

        model.getCategory(getIntent().getStringExtra("category"));
        model.categoryMutableLiveData.observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> data) {
                adapter = new MyListAdapter(CategoryPage.this,data);
                recyclerView.setLayoutManager(new GridLayoutManager(CategoryPage.this,2, GridLayoutManager.VERTICAL,false));


                adapter.setClickListener(new MyListAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(CategoryPage.this, ProductDetail.class);
                        intent.putExtra("title",data.get(position).title);
                        intent.putExtra("price",data.get(position).price);
                        intent.putExtra("image",data.get(position).image);
                        intent.putExtra("rate",data.get(position).rating.rate);
                        intent.putExtra("description",data.get(position).description);
                        intent.putExtra("count",data.get(position).rating.count);
                        startActivity(intent);
                    }
                });

                adapter.setClickListener(new MyListAdapter.addClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        HomePage.cartData.add(new CartData(data.get(position).image,data.get(position).title,
                                1,data.get(position).price));
                        if(HomePage.cartOpened){
                            Cart.cartAdapter.notifyItemInserted(HomePage.cartData.size()-1);
                        }
                    }
                });

                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(view->{
            finish();
        });

    }
}