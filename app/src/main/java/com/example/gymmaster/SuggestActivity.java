package com.example.gymmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gymmaster.databinding.ActivitySuggestBinding;
import java.util.List;

public class SuggestActivity extends AppCompatActivity {
    private ActivitySuggestBinding binding;
    private ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuggestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        setupRecyclerView();

        binding.btnCalculate.setOnClickListener(v -> calculateAndSuggest());
    }

    private void setupRecyclerView() {
        adapter = new ExerciseAdapter(exercise -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("EXERCISE_OBJECT", exercise);
            startActivity(intent);
        });
        binding.rvSuggestions.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSuggestions.setAdapter(adapter);
    }

    private void calculateAndSuggest() {
        String weightStr = binding.etWeight.getText().toString();
        String heightStr = binding.etHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, R.string.enter_values_toast, Toast.LENGTH_SHORT).show();
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr) / 100; // convert to meters
        float bmi = weight / (height * height);

        String category;
        String suggestion;
        String goal;

        if (bmi < 18.5) {
            category = "Underweight";
            suggestion = getString(R.string.suggest_strength);
            goal = "STRENGTH";
        } else if (bmi < 25) {
            category = "Normal";
            suggestion = getString(R.string.suggest_balanced);
            goal = "BALANCED";
        } else {
            category = "Overweight";
            suggestion = getString(R.string.suggest_cardio);
            goal = "CARDIO";
        }

        binding.tvBmiResult.setText(getString(R.string.bmi_result_prefix, String.format("%.1f", bmi), category));
        binding.tvSuggestion.setText(suggestion);
        
        List<Exercise> suggestions = ExerciseRepository.getSuggestedExercises(goal);
        adapter.submitList(suggestions);

        binding.resultLayout.setVisibility(View.VISIBLE);
    }
}
