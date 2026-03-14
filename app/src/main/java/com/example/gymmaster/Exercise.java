package com.example.gymmaster;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Exercise implements Parcelable {
    private final String name;
    private final String category;
    private final String type;
    private final String difficulty;
    private final String videoId;
    private final String description;
    private final int imageResId;

    public Exercise(String name, String category, String type, String difficulty, String videoId, String description, int imageResId) {
        this.name = name;
        this.category = category;
        this.type = type; // "Gym" or "Home"
        this.difficulty = difficulty;
        this.videoId = videoId;
        this.description = description;
        this.imageResId = imageResId;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        category = in.readString();
        type = in.readString();
        difficulty = in.readString();
        videoId = in.readString();
        description = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(type);
        dest.writeString(difficulty);
        dest.writeString(videoId);
        dest.writeString(description);
        dest.writeInt(imageResId);
    }

    // Getters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public String getDifficulty() { return difficulty; }
    public String getVideoId() { return videoId; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
}
