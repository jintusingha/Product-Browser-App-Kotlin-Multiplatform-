# Product Browser App — Kotlin Multiplatform

A Kotlin Multiplatform Mobile (KMM) app built with Compose Multiplatform, targeting Android and iOS. The app consumes product data from [DummyJSON](https://dummyjson.com/docs/products) and allows users to browse, search, and filter products by category.

---

## Business Requirements

- View a list of products showing name, price, thumbnail, brand, and rating
- Tap a product to view detailed information including title, description, brand, price, and rating
- Search products by keyword using the API
- Filter products by category using filter chips

---

## Project Architecture

The app follows **Clean Architecture** with three layers:

### Data Layer
- `ProductDto` — raw API response model
- `ProductRemoteDataSourceImpl` — makes API calls using Ktor
- `ProductRepositoryImpl` — implements the domain repository
- `ProductMapper` — maps `ProductDto` to `Product` domain model
- `HttpClientFactory` — creates the Ktor HTTP client (OkHttp on Android, Darwin on iOS)

### Domain Layer
- `Product` — domain model
- `ProductRepository` — repository interface
- `GetProductsUseCase` — fetches all products
- `SearchProductsUseCase` — searches products by keyword

### Presentation Layer
- `ProductViewModel` — manages UI state using `StateFlow`
- `ProductUiState` — sealed class with Loading, Success, Error states
- `ProductListScreen` — displays product list with search bar and category filter chips
- `ProductDetailScreen` — displays full product details

### Navigation
- `ScreenRoute` — sealed class defining all app routes
- `AppNavHost` — Compose Navigation host wiring all screens

### Dependency Injection
- Koin is used for DI (`koin-core`, `koin-compose`, `koin-compose-viewmodel`)

---

## How to Build and Run

### Prerequisites
- Android Studio Hedgehog or later
- JDK 11+

### Android

1. Clone the repository:
```
git clone https://github.com/jintusingha/Product-Browser-App-Kotlin-Multiplatform-.git
```
2. Open the project in Android Studio
3. Wait for Gradle sync to complete
4. Select an Android emulator or physical device
5. Click **Run**

### iOS

The app is configured to target iOS (iosArm64, iosSimulatorArm64) using Kotlin Multiplatform and Compose Multiplatform. However, the iOS build has not been tested due to unavailability of a Mac machine and Xcode during development.

To build for iOS you would need:
- A Mac machine
- Xcode 15+
- Open `iosApp/iosApp.xcodeproj` in Xcode and run

---

## Running Tests

```
./gradlew :composeApp:testDebugUnitTest --rerun-tasks
```

Test report available at:
```
composeApp/build/reports/tests/testDebugUnitTest/index.html
```

---

## Trade-offs and Assumptions

- **No local caching** — products are fetched fresh every time the app launches. Could be improved with SQLDelight for offline support.
- **String-based navigation** — used string-based route navigation instead of type-safe navigation to keep it consistent with production app patterns.
- **Only productId passed in nav route** — full product data is looked up from ViewModel memory to avoid URL encoding issues with special characters in product titles and descriptions.
- **Ktor engine** — using OkHttp for Android and Darwin for iOS. CIO engine caused runtime crashes on Android so it was replaced.
- **Koin for DI** — used Koin instead of manual DI for cleaner and more scalable code across the shared module.
- **Coil3 for images** — used Coil3 as it is the only image loading library with full KMP/Compose Multiplatform support.
- **iOS not tested** — iOS build is configured in the project but could not be verified due to unavailability of a Mac/Xcode environment during development.
- **Brand and category are nullable** — not all products from DummyJSON include brand or category so these are treated as optional fields.