package com.example.gymmaster;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseViewModel extends ViewModel {
    private final MutableLiveData<List<Exercise>> _exercises = new MutableLiveData<>();
    private List<Exercise> fullList = new ArrayList<>();

    public LiveData<List<Exercise>> getExercises() {
        return _exercises;
    }

    public void loadExercises(String type, String category) {
        fullList = ExerciseRepository.getExercises(type, category);
        _exercises.setValue(fullList);
    }

    public void filterExercises(String query) {
        if (query == null || query.isEmpty()) {
            _exercises.setValue(fullList);
            return;
        }

        String lowerQuery = query.toLowerCase();
        List<Exercise> filtered = fullList.stream()
                .filter(e -> e.getName().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
        
        _exercises.setValue(filtered);
    }
}
