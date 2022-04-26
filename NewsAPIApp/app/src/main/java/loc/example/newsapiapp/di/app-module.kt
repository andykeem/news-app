package loc.example.newsapiapp.di

import android.content.Context
import io.github.reactivecircus.cache4k.Cache
import kotlinx.serialization.json.Json
import loc.example.newsapiapp.App
import loc.example.newsapiapp.api.HttpApi
import loc.example.newsapiapp.api.NewsService
import loc.example.newsapiapp.repo.NewsRepo
import loc.example.newsapiapp.repo.NewsRepository
import loc.example.newsapiapp.viewmodel.ArticleListItemViewModel
import loc.example.newsapiapp.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

val appModule = module {
    single {
        Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
    }
    single { HttpApi() }
    single { NewsService(get()) }
    single<Context> { App() }
    single { Cache.Builder() }
    single<NewsRepo> { NewsRepository(get(), get(), androidContext(), get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { ArticleListItemViewModel() }
}