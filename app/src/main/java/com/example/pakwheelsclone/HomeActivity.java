package com.example.pakwheelsclone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pakwheelsclone.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String email = currentUser.getEmail();
            binding.homeText.setText("Welcome, " + email + "!");
        } else {
            binding.homeText.setText("Home - Pakwheels Clone");
        }
    }
}