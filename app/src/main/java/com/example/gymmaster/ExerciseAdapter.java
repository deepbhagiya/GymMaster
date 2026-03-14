package com.example.gymmaster;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gymmaster.databinding.ItemExerciseBinding;

public class ExerciseAdapter extends ListAdapter<Exercise, ExerciseAdapter.ExerciseViewHolder> {

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Exercise exercise);
    }

    public ExerciseAdapter(OnItemClickListener listener) {
        super(new ExerciseDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseBinding binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ExerciseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = getItem(position);
        holder.bind(exercise, listener);
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private final ItemExerciseBinding binding;

        public ExerciseViewHolder(@NonNull ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Exercise exercise, OnItemClickListener listener) {
            binding.exerciseTitle.setText(exercise.getName());
            binding.exerciseCategory.setText(exercise.getCategory());
            binding.exerciseImage.setImageResource(exercise.getImageResId());
            binding.exerciseDifficulty.setText(exercise.getDifficulty());
            
            // Dynamic difficulty colors for a professional look
            int color;
            switch (exercise.getDifficulty().toLowerCase()) {
                case "easy":
                    color = Color.parseColor("#4CAF50"); // Green
                    break;
                case "medium":
                    color = Color.parseColor("#FFC107"); // Amber
                    break;
                case "hard":
                    color = Color.parseColor("#FF5252"); // Red
                    break;
                default:
                    color = ContextCompat.getColor(itemView.getContext(), R.color.accent_neon);
                    break;
            }
            binding.exerciseDifficulty.setBackgroundTintList(ColorStateList.valueOf(color));

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(exercise);
                }
            });
        }
    }

    private static class ExerciseDiffCallback extends DiffUtil.ItemCallback<Exercise> {
        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                   oldItem.getCategory().equals(newItem.getCategory()) &&
                   oldItem.getDifficulty().equals(newItem.getDifficulty());
        }
    }
}
