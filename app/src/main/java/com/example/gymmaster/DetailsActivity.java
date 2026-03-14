package com.example.gymmaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gymmaster.databinding.ActivityDetailsBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Exercise exercise = getIntent().getParcelableExtra("EXERCISE_OBJECT");

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (exercise != null) {
            binding.collapsingToolbar.setTitle(exercise.getName());
            binding.detailsDescription.setText(exercise.getDescription());
            binding.detailsImage.setImageResource(exercise.getImageResId());

            boolean hasVideo = exercise.getVideoId() != null && 
                               !exercise.getVideoId().isEmpty() && 
                               !exercise.getVideoId().equalsIgnoreCase("none");

            if (hasVideo) {
                getLifecycle().addObserver(binding.youtubePlayerView);
                binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youtubePlayer) {
                        youtubePlayer.cueVideo(exercise.getVideoId(), 0);
                        binding.youtubePlayerView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(@NonNull YouTubePlayer youtubePlayer, @NonNull PlayerConstants.PlayerError error) {
                        // Handle cases where the video might be deleted or unavailable
                        binding.youtubePlayerView.setVisibility(View.GONE);
                        binding.btnWatchVideo.setText(R.string.search_tutorial);
                        setupSearchFallback(exercise);
                    }
                });

                binding.btnWatchVideo.setText(R.string.watch_tutorial);
                binding.btnWatchVideo.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/watch?v=" + exercise.getVideoId()));
                    startActivity(intent);
                });
            } else {
                binding.youtubePlayerView.setVisibility(View.GONE);
                binding.btnWatchVideo.setText(R.string.search_tutorial);
                setupSearchFallback(exercise);
            }
        }
    }

    private void setupSearchFallback(Exercise exercise) {
        binding.btnWatchVideo.setOnClickListener(v -> {
            String query = Uri.encode(exercise.getName() + " exercise tutorial");
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/results?search_query=" + query));
            startActivity(intent);
        });
    }
}
