package loc.example.newsapiapp.model

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Loaded<S>(val data: S) : UiState<S>()
    data class Error(val cause: Throwable) : UiState<Nothing>()
}