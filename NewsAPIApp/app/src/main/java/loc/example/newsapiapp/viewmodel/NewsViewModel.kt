package loc.example.newsapiapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import loc.example.newsapiapp.model.Article
import loc.example.newsapiapp.model.UiState
import loc.example.newsapiapp.repo.NewsRepo

private const val TAG = "NewsViewModel"
class NewsViewModel(private val repo: NewsRepo) : ViewModel() {
    private val _articlesFlow = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val articlesFlow = _articlesFlow.asStateFlow()

    fun fetchLatestNews() {
        viewModelScope.launch {
            repo.fetchNews().collect {
                Log.d(TAG, "news articles: $it")
                _articlesFlow.value = UiState.Loaded(it)
            }
        }
    }
}