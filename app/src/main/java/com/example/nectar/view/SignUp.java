package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nectar.R;
import com.example.nectar.viewModel.ViewModel;

public class SignUp extends AppCompatActivity {

    ImageView signUp;
    EditText username,password,email;
    TextView logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        email = findViewById(R.id.username);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(view->{
            ViewModel.userSignUpData(email.getText().toString(), username.getText().toString(), password.getText().toString(), SignUp.this);
        });

        logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(view->{
            Intent intent = new Intent(SignUp.this,LogIn.class);
            startActivity(intent);
        });
    }
}