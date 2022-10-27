package com.example.nectar.repository;

import com.example.nectar.model.LoginRequest;
import com.example.nectar.model.LoginResponse;
import com.example.nectar.model.Product;
import com.example.nectar.model.SignUpRequest_Response;
import com.example.nectar.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;

public class Repository {
    public Call<ArrayList<Product>> getproducts(){
        return RetrofitClient.getInstance().getMyApi().getData();
    }

    public Call<ArrayList<String>> getCategories(){
        return RetrofitClient.getInstance().getMyApi().getCategories();
    }

    public Call<ArrayList<Product>> getCategory(String category){
        return RetrofitClient.getInstance().getMyApi().getCategory(category);
    }

    public Call<LoginResponse> getUser(String username, String password){
        return RetrofitClient.getInstance().getMyApi().getUser(new LoginRequest(username,password));
    }

    public Call<SignUpRequest_Response> AddUser(String email, String username, String password){
        return RetrofitClient.getInstance().getMyApi().AddUser(new SignUpRequest_Response(email, username, password));
    }
}
