package com.example.gymmaster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseRepository {
    private static List<Exercise> library = new ArrayList<>();

    static {
        // --- MASTER DATA: GYM WORKOUTS ---
        library.add(new Exercise("Bench Press", "Chest", "Gym", "Hard", "rT7Dg4zWIjg",
                "1. Lie on bench.\n2. Lower bar to chest.\n3. Press up.", R.drawable.bench_press));
        library.add(new Exercise("Lat Pulldown", "Back", "Gym", "Medium", "CAwf7n6Luuc",
                "1. Sit at machine.\n2. Pull bar to chest.", R.drawable.lat_pulldown));
        library.add(new Exercise("Leg Press", "Legs", "Gym", "Easy", "IZxyjW7MPJQ",
                "1. Place feet on platform.\n2. Push away.", R.drawable.leg_press));
        library.add(new Exercise("Bicep Cable Curl", "Arms", "Gym", "Easy", "AsjG0_pOn_I",
                "1. Hold cable bar.\n2. Curl towards shoulders.", R.drawable.bicep_cable_curl));
        library.add(new Exercise("Treadmill Run", "Cardio", "Gym", "Medium", "8i3VzdGeW68",
                "1. Start at a slow walk.\n2. Increase speed to a comfortable jog.", R.drawable.treadmill_run));
        library.add(new Exercise("Gym Ab Roller", "Core", "Gym", "Hard", "7_6mDInR-zM",
                "1. Kneel on floor.\n2. Roll the wheel forward.\n3. Pull back using abs.", R.drawable.gym_ab_roller));

        // --- MASTER DATA: HOME WORKOUTS ---
        library.add(new Exercise("Push Ups", "Chest", "Home", "Medium", "IODxDxX7oi4",
                "1. Hands shoulder width.\n2. Lower chest to floor.", R.drawable.push_ups));
        library.add(new Exercise("Superman", "Back", "Home", "Easy", "z6PJMT2y8GQ",
                "1. Lie on stomach.\n2. Lift arms and legs.", R.drawable.superman));
        library.add(new Exercise("Bodyweight Squat", "Legs", "Home", "Easy", "gcNh17Ckjgg",
                "1. Feet shoulder width.\n2. Squat down.", R.drawable.bodyweight_squat));
        library.add(new Exercise("Chair Dips", "Arms", "Home", "Medium", "0326dy_-CzM",
                "1. Use a sturdy chair.\n2. Lower body and push up.", R.drawable.chair_dips));
        library.add(new Exercise("Plank", "Core", "Home", "Medium", "ASVnGg68_8",
                "1. Hold forearm position.\n2. Keep body straight.", R.drawable.plank));
        library.add(new Exercise("Jumping Jacks", "Cardio", "Home", "Easy", "iSSAk4XCs_4",
                "1. Jump while spreading legs.\n2. Clap hands overhead.", R.drawable.jumping_jacks));
        library.add(new Exercise("Mountain Climbers", "Cardio", "Home", "Medium", "cnyTQDSE884",
                "1. Start in plank.\n2. Bring knees to chest rapidly.", R.drawable.mountain_climbers));
    }

    public static List<Exercise> getExercises(String type, String category) {
        List<Exercise> filteredList = new ArrayList<>();
        for (Exercise e : library) {
            if (e.getType().equalsIgnoreCase(type) && e.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(e);
            }
        }
        return filteredList;
    }

    public static List<Exercise> getSuggestedExercises(String goal) {
        if (goal.equals("STRENGTH")) {
            return library.stream()
                    .filter(e -> e.getCategory().equals("Chest") || e.getCategory().equals("Legs") || e.getCategory().equals("Back"))
                    .limit(4)
                    .collect(Collectors.toList());
        } else if (goal.equals("CARDIO")) {
            return library.stream()
                    .filter(e -> e.getCategory().equals("Cardio") || e.getCategory().equals("Core"))
                    .limit(4)
                    .collect(Collectors.toList());
        } else {
            return library.stream().limit(4).collect(Collectors.toList());
        }
    }
}
