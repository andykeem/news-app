package loc.example.newsapiapp.repo

import android.content.Context
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import loc.example.newsapiapp.api.NewsService
import loc.example.newsapiapp.model.Article
import loc.example.newsapiapp.model.ArticlesResponse

interface NewsRepo {
    suspend fun fetchNews(): Flow<List<Article>>
}

internal class NewsRepository(
    private val json: Json,
    private val service: NewsService,
    private val context: Context,
    private val cache: Cache.Builder,
) : NewsRepo {
    override suspend fun fetchNews(): Flow<List<Article>> = flow {
//        val q = "tesla"
//        val body = service.getNews(q).bodyAsText()
        if (cache.get)
        val fileName = "news-tesla.json"
        val body = grabFakeBody(fileName)
        val articlesResponse = json.decodeFromString<ArticlesResponse>(body)
        emit(articlesResponse.articles)
    }.onEach {
        cache.build<String, List<Article>>().apply {
            put(CACHE_KEY_ARTICLES, it)
        }
    }
        .flowOn(Dispatchers.IO)

    private suspend fun grabFakeBody(fileName: String) = withContext(Dispatchers.IO) {
        val delayMs = (500L..2000L).shuffled().last()
        delay(delayMs)
        context.assets.open(fileName).use {
            val size = it.available()
            val buffer = ByteArray(size)
            it.read(buffer)
            it.close()
            String(buffer)
        }
    }

    companion object {
        const val CACHE_KEY_ARTICLES = "CACHE_KEY_ARTICLES"
    }
}