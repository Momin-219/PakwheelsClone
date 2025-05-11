package com.example.pakwheelsclone;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pakwheelsclone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set welcome text
        binding.welcomeText.setText("Welcome to Pakwheels Clone");

        // Login button click
        binding.loginButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        // Sign Up button click
        binding.signUpButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        });
    }
}