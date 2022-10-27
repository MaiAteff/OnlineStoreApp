package com.example.nectar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nectar.R;
import com.example.nectar.viewModel.ViewModel;

public class LogIn extends AppCompatActivity {

    ImageView logIn;
    EditText username,password;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(view->{
            ViewModel.userLogInData(username.getText().toString(), password.getText().toString(),LogIn.this);
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(view->{
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
        });
    }



}