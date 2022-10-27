package com.example.nectar.network;

import com.example.nectar.model.LoginRequest;
import com.example.nectar.model.LoginResponse;
import com.example.nectar.model.SignUpRequest_Response;
import com.example.nectar.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://fakestoreapi.com/";

    @GET("products")
    Call<ArrayList<Product>> getData();

    @GET("products/categories")
    Call<ArrayList<String>> getCategories();

    @GET("products/category/{category}/")
    Call<ArrayList<Product>> getCategory(@Path("category") String category);

    @POST("auth/login")
    Call<LoginResponse> getUser(@Body LoginRequest loginRequest);

    @POST("users")
    Call<SignUpRequest_Response> AddUser(@Body SignUpRequest_Response signUpRequest);


}
