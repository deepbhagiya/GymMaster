# Gym Master - Fitness Companion App

Gym Master is a professional, high-performance Android application designed to guide users through their fitness journey. Whether you are working out at a commercial gym or in the comfort of your own home, Gym Master provides the tutorials and personalization you need to succeed.

## 🚀 Features

*   **Hybrid Workout Modes**: Separate sections for **Gym** and **Home** workouts.
*   **Targeted Training**: Choose exercises based on specific muscle groups (Chest, Back, Legs, Arms, Cardio, Core).
*   **Personalized Suggestions**: Enter your height and weight to receive a BMI-based workout recommendation (Strength, Balanced, or Cardio).
*   **Interactive Video Tutorials**: Watch exercise guides directly within the app using the integrated YouTube player.
*   **Real-time Search**: Quickly find specific exercises using the instant-search functionality.
*   **Smart Fallback**: If a video is unavailable, the app provides a direct link to search for the best tutorials on YouTube.
*   **Pro UI/UX**: Features a sleek dark/neon theme, collapsing toolbars, and dynamic difficulty badges.

## 🛠️ Tools & Technologies Used

*   **Language**: Java
*   **Architecture**: MVVM (ViewModel), Repository Pattern
*   **UI Components**: Material 3, ConstraintLayout, CoordinatorLayout, CollapsingToolbarLayout
*   **Data Binding**: ViewBinding for safe and efficient UI interaction
*   **Libraries**:
    *   `androidx.recyclerview:recyclerview`: For smooth list management.
    *   `com.pierfrancescosoffritti.androidyoutubeplayer:core`: For inline YouTube video playback.
    *   `com.google.android.material:material`: For modern UI components and icons.
*   **Performance**: `Parcelable` for fast data passing and `DiffUtil` for optimized list updates.

## 📱 Screenshots



## 📂 Project Structure

*   `app/src/main/java/com/example/gymmaster/`: Contains all Java source code (Activities, Repository, ViewModel, Adapters).
*   `app/src/main/res/`: Contains all UI layouts, vector icons, and styling resources.
*   `app/build.gradle`: Project configuration and dependencies.

## 🏁 Getting Started

1. Clone this repository.
2. Open the project in Android Studio (Iguana or newer recommended).
3. Sync Gradle and run on an emulator or physical device (Min SDK: 24).

---
Developed as a professional fitness solution.
