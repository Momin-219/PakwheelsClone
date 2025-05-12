package com.example.pakwheelsclone;

import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pakwheelsclone.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerViewCarAds;
    private CarAdAdapter carAdAdapter;
    private List<CarAd> carAdList;
    private FirebaseFirestore db;

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

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Set up RecyclerView
        recyclerViewCarAds = binding.recyclerViewCarAds;
        recyclerViewCarAds.setLayoutManager(new LinearLayoutManager(this));
        carAdList = new ArrayList<>();
        carAdAdapter = new CarAdAdapter(carAdList);
        recyclerViewCarAds.setAdapter(carAdAdapter);

        // Fetch car ads from Firestore
        fetchCarAds();

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;

        // Define colors for selected and unselected states
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked }, // Selected
                new int[] { -android.R.attr.state_checked } // Unselected
        };
        int[] colors = new int[] {
                ContextCompat.getColor(this, R.color.nav_selected), // Selected: Blue (#FF2196F3)
                ContextCompat.getColor(this, R.color.nav_unselected) // Unselected: Gray (#FF757575)
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);

        // Apply colors to icons and text
        bottomNavigationView.setItemIconTintList(colorStateList);
        bottomNavigationView.setItemTextColor(colorStateList);

        // Set up navigation item selection listener using if-else
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                binding.homeText.setText("Home - Pakwheels Clone");
                return true;
            } else if (itemId == R.id.nav_my_ads) {
                binding.homeText.setText("My Ads");
                return true;
            } else if (itemId == R.id.nav_sell_now) {
                binding.homeText.setText("Sell Now");
                return true;
            } else if (itemId == R.id.nav_chat) {
                binding.homeText.setText("Chat");
                return true;
            } else if (itemId == R.id.nav_more) {
                binding.homeText.setText("More");
                return true;
            }
            return false;
        });

        // Set default selected item to Home
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    private void fetchCarAds() {
        db.collection("car_ads")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        carAdList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String title = document.getString("title");
                            String price = document.getString("price");
                            String imageUrl = document.getString("imageUrl");
                            if (title != null && price != null && imageUrl != null) {
                                carAdList.add(new CarAd(title, price, imageUrl));
                            }
                        }
                        carAdAdapter.notifyDataSetChanged();
                    } else {
                        // Handle errors (e.g., log or show a toast)
                        binding.homeText.setText("Error loading ads");
                    }
                });
    }
}