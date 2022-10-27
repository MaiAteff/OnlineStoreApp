package com.example.nectar.viewModel;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.nectar.model.LoginRequest;
import com.example.nectar.model.LoginResponse;
import com.example.nectar.model.SignUpRequest_Response;
import com.example.nectar.model.Product;
import com.example.nectar.network.RetrofitClient;
import com.example.nectar.repository.Repository;
import com.example.nectar.view.HomePage;
import com.example.nectar.view.LogIn;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends androidx.lifecycle.ViewModel {

    static Repository repository = new Repository();
    public MutableLiveData<ArrayList<Product>> arrayListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<String>> categoriesMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Product>> categoryMutableLiveData = new MutableLiveData<>();

    public void getProducts() {
        Call<ArrayList<Product>> call = repository.getproducts();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                arrayListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    public void getCategories() {
        Call<ArrayList<String>> call = repository.getCategories();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                categoriesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });
    }

    public void getCategory(String category) {
        Call<ArrayList<Product>> call = repository.getCategory(category);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                categoryMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    public static void userLogInData(String username, String password, Context context) {
        Call<LoginResponse> call = repository.getUser(username,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(context, HomePage.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public static void userSignUpData(String email, String username, String password, Context context) {
        Call<SignUpRequest_Response> call = repository.AddUser(email,username,password);
        call.enqueue(new Callback<SignUpRequest_Response>() {
            @Override
            public void onResponse(Call<SignUpRequest_Response> call, Response<SignUpRequest_Response> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(context, LogIn.class);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SignUpRequest_Response> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}
