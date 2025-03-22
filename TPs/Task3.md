# Task 3: Understanding Dependency Injection and Implementing Add Product Feature

## Dependency Injection with Dagger Hilt

The Rayna app now uses Dagger Hilt for dependency injection across all layers of the application. Here's how it's structured:

### 1. Data Layer
- Repository implementations are provided via Hilt modules
- Data sources and their dependencies are injected into repositories
- Example: `ProductRepository` and `LocationRepository` are injected with their dependencies

### 2. Domain Layer
- Use cases are injected with their required repositories
- Examples:
    - `GetProductsUseCase` is injected with `ProductRepository`
    - `AddProductUseCase` is injected with `ProductRepository`
    - `GetLocationsCategoriesUseCase` is injected with `LocationRepository`

### 3. Presentation Layer
- ViewModels are annotated with `@HiltViewModel`
- Use cases are injected into ViewModels
- Example: `ProductViewModel` is injected with `GetProductsUseCase`

## Your Task: Implement Add Product Feature

### 1. Create Add Product View
Create a new composable function `AddProductScreen` in the presentation layer with the following requirements:

- Input fields for:
    - Product name (Arabic)
    - Category (dropdown selection)
    - Description
    - Price (numeric input)
    - Image URL
    - Rating (1-5 stars)
- Validation for all fields
- Submit button
- Success/Error feedback

### 2. Enhance AddProductViewModel

Update the `AddProductViewModel` to include:

```kotlin
// State for form fields
data class AddProductState(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: Double = 0.0,
    @DrawableRes val pictureUrl: Int = 0,
    val rating: Double = 0.0
)

// Events that can be triggered
sealed class AddProductEvent {
    data class NameChanged(val name: String) : AddProductEvent()
    data class CategorySelected(val category: String) : AddProductEvent()
    data class DescriptionChanged(val description: String) : AddProductEvent()
    data class PriceChanged(val price: Double) : AddProductEvent()
    data class ImageUrlChanged(@DrawableRes val url: Int) : AddProductEvent()
    data class RatingChanged(val rating: Double) : AddProductEvent()
    object Submit : AddProductEvent()
}
```

Required functionality:
1. State management for form fields
2. Input validation
3. Error handling
4. Integration with `AddProductUseCase`
5. Success/failure feedback

### Implementation Steps

1. **AddProductViewModel Implementation**
    - Add state management using `StateFlow`
    - Implement event handling
    - Add validation logic (e.g., non-empty fields, valid price/rating ranges)
    - Integrate with `AddProductUseCase` using injected dependency

2. **AddProductScreen Implementation**
    - Create UI components using Jetpack Compose
    - Connect UI events to ViewModel (e.g., text fields → event triggers)
    - Show loading state with progress indicator
    - Display error messages in snackbar
    - Show success confirmation dialog

3. **Navigation Integration**
    - Add navigation to Add Product screen (already integrated in main navigation)
    - Handle back navigation with proper state reset
    - Pass product data back to main product list screen

### Success Criteria

Your implementation should:
1. Successfully add new products to the repository
2. Validate all required fields (name, category, price, etc.)
3. Show appropriate loading states
4. Handle and display validation/formatting errors
5. Provide success feedback after product creation
6. Follow MVVM architecture patterns
7. Properly utilize dependency injection for `AddProductUseCase`

### Tips
- Use `viewModelScope` with coroutines for asynchronous operations
- Implement proper error handling using sealed classes
- Follow Material Design guidelines for the UI
- Use proper state management with `StateFlow`/`SharedFlow`
- Make use of Hilt's `@Inject` for repository dependencies
- Ensure proper Arabic text handling for RTL layout

### Bonus Challenges
1. Implement image picker instead of URL input
2. Add category autocomplete suggestions
3. Create unit tests for validation logic
4. Implement UI tests for form submission
5. Add rating stars visual representation
6. Implement real-time validation feedback

### Add Product UI Design

The Add Product screen should follow Rayna's design language:
- Use brand colors (e.g., primary accent color)
- Maintain right-to-left (RTL) layout for Arabic text
- Follow existing card and input field styles
- Use consistent typography and spacing
- Include product preview section for image/rating

Good luck implementing the Add Product feature with Dagger Hilt!!