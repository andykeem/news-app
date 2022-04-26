package loc.example.newsapiapp.model

import kotlinx.serialization.Serializable
import java.util.*

@Serializable()
data class Article(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) {
    val id: UUID
        get() = UUID.randomUUID()
}
