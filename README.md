# BragiMovies 🎬

An Android app that displays a list of movies by genre using The Movie Database (TMDb) API. Built with modern Android development tools and patterns.

---

## 📦 How to Build the App

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yauheniBulat/bragi_movies.git
   cd bragi_movies
Open in Android Studio:

Open Android Studio.

Choose "Open an existing project" and select the project directory.

Add your TMDb API key:

Find or create in the root of the project file local.properties and add string

TMDB_API_KEY=your_key_here

Replace placeholder with your key.

Build & Run:

Connect an emulator or device.

Hit ▶️ Run.

🏗️ General Architecture
Architecture Pattern: MVVM (Model - View - ViewModel)
Layers:

Presentation Layer: Jetpack Compose UI, ViewModel, and state management with StateFlow.

Domain Layer: Contains business logic (UseCases) and domain models.

Data Layer: Responsible for fetching data from MovieApi and mapping DTOs to domain models.

Dependency Injection: Hilt
Reactive Streams: Kotlin Coroutines + Flow

📚 Libraries Used

Jetpack Compose	UI toolkit
Hilt	Dependency Injection
Retrofit	Network requests
OkHttp	HTTP client
Kotlin Coroutines	Asynchronous operations
Coil	Image loading
Gson	JSON serialization
Navigation Compose	In-app navigation

✨ Features
View list of movies by genre

Filter movies by selected genre

See details like rating, budget, and revenue

Clean architecture with Hilt & Flow