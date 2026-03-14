package com.example.gymmaster;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gymmaster.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity {
    private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("WORKOUT_TYPE");

        if (type != null) {
            binding.tvCategoryHeader.setText(String.format("%s %s", type, "Workouts"));
        }

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        binding.cardChest.setOnClickListener(v -> openList(type, "Chest"));
        binding.cardLegs.setOnClickListener(v -> openList(type, "Legs"));
        binding.cardBack.setOnClickListener(v -> openList(type, "Back"));
        binding.cardArms.setOnClickListener(v -> openList(type, "Arms"));
        binding.cardCardio.setOnClickListener(v -> openList(type, "Cardio"));
        binding.cardCore.setOnClickListener(v -> openList(type, "Core"));
    }

    private void openList(String type, String category) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra("WORKOUT_TYPE", type);
        intent.putExtra("CATEGORY_NAME", category);
        startActivity(intent);
    }
}
