package loc.example.newsapiapp.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class HttpApi {
    val client = HttpClient(CIO)
    val endpoint: String
        get() = ENDPOINT

    companion object {
        const val ENDPOINT = "https://newsapi.org/v2/"
    }
}