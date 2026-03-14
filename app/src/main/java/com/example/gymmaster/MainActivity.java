package com.example.gymmaster;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gymmaster.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGym.setOnClickListener(v -> startStepTwo("Gym"));
        binding.btnHome.setOnClickListener(v -> startStepTwo("Home"));
        binding.btnSuggest.setOnClickListener(v -> {
            Intent intent = new Intent(this, SuggestActivity.class);
            startActivity(intent);
        });
    }

    private void startStepTwo(String type) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("WORKOUT_TYPE", type);
        startActivity(intent);
    }
}
