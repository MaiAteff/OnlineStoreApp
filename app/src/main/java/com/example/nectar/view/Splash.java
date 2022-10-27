package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.nectar.R;
import com.example.nectar.model.Product;
import com.example.nectar.viewModel.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Splash extends AppCompatActivity {

    ViewModel model;
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<String> categories = new ArrayList<>();
    public static Map<String,String> imgCategories = new HashMap<>();
    static boolean p = false, c = false, i = false;
    private static CChangedListener vChangedListener;
    private static PChangedListener pChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        model = new ViewModelProvider(this).get(ViewModel.class);

        model.getProducts();
        model.arrayListMutableLiveData.observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> data) {
                products = data;
                setP(true);
            }
        });
    }
    void setC (boolean c){
        this.c = c;
        setChangedListener(view ->{
            if (vChangedListener != null) vChangedListener.onCChanged(c);
        });
        getImg(0);
    }

    void setP (boolean p){
        this.p = p;
        setChangedListener(view ->{
            if (pChangedListener != null) pChangedListener.onPChanged(p);
        });
        getCategories();
    }

    void getCategories(){
        model.getCategories();
        model.categoriesMutableLiveData.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> data) {
                categories = data;
                setC(true);
            }
        });
    }
    void getImg(int i){
        model.getCategory(categories.get(i));
        model.categoryMutableLiveData.observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> data) {
                imgCategories.put(data.get(0).category,data.get(data.size()-1).image);
                if (i < (categories.size()-1)){
                    getImg(i+1);
                    return;
                }else if (imgCategories.size() == categories.size()  && !Splash.i){
                    Intent intent = new Intent(Splash.this, Onbording.class);
                    startActivity(intent);
                    Splash.i = true;
                }
            }
        });
    }

    interface CChangedListener
    {
        void onCChanged(boolean c);
    }

    void setChangedListener(CChangedListener itemClickListener) {
        this.vChangedListener = itemClickListener;
    }

    interface PChangedListener
    {
        void onPChanged(boolean p);

    }

    void setIChangedListener(PChangedListener iChangedListener) {
        this.pChangedListener = iChangedListener;
    }
}