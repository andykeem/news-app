package loc.example.newsapiapp.api

import android.util.Log
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import loc.example.newsapiapp.BuildConfig

private const val TAG = "NewsService"
class NewsService(private val api: HttpApi) {

//    GET https://newsapi.org/v2/top-headlines?country=us&apiKey=987aa97c056746e6856b61f06abfc951
//    GET https://newsapi.org/v2/everything?q=tesla&from=2022-03-23&sortBy=publishedAt&apiKey=987aa97c056746e6856b61f06abfc951
    suspend fun getNews(q: String): HttpResponse {
        val url = "${api.endpoint}everything"
        val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        val from = "${now.year}-${now.month}-${now.dayOfMonth}"
        val response = api.client.get(url) {
            parameter("q", q)
            parameter("from", from)
            parameter("apiKey", BuildConfig.NEWS_API_KEY)
        }
        Log.d(TAG, "getNews: ${response.bodyAsText()}")
        api.client.close()
        return response
    }
}