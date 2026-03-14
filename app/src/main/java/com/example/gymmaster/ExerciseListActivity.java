package com.example.gymmaster;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.gymmaster.databinding.ActivityExerciseListBinding;

public class ExerciseListActivity extends AppCompatActivity {
    private ActivityExerciseListBinding binding;
    private ExerciseViewModel viewModel;
    private ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("WORKOUT_TYPE");
        String category = getIntent().getStringExtra("CATEGORY_NAME");

        if (category != null) {
            binding.tvListTitle.setText(String.format("%s %s", category, getString(R.string.exercises_suffix)));
        }

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        setupRecyclerView();
        setupViewModel(type, category);
        setupSearch();
    }

    private void setupRecyclerView() {
        adapter = new ExerciseAdapter(exercise -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("EXERCISE_OBJECT", exercise);
            startActivity(intent);
        });
        binding.rvExercises.setLayoutManager(new LinearLayoutManager(this));
        binding.rvExercises.setAdapter(adapter);
    }

    private void setupViewModel(String type, String category) {
        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        viewModel.getExercises().observe(this, exercises -> {
            if (exercises == null || exercises.isEmpty()) {
                binding.tvEmptyState.setVisibility(View.VISIBLE);
                binding.rvExercises.setVisibility(View.GONE);
            } else {
                binding.tvEmptyState.setVisibility(View.GONE);
                binding.rvExercises.setVisibility(View.VISIBLE);
                adapter.submitList(exercises);
            }
        });
        viewModel.loadExercises(type, category);
    }

    private void setupSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.filterExercises(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
