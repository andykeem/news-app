package loc.example.newsapiapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import loc.example.newsapiapp.databinding.ArticleListItemBinding
import loc.example.newsapiapp.model.Article
import loc.example.newsapiapp.viewmodel.ArticleListItemViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ArticleAdapter : ListAdapter<Article, ArticleViewHolder>(object :
    DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { ArticleViewHolder(this) }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bindItem(getItem(position))
}

class ArticleViewHolder(private val bind: ArticleListItemBinding) :
    RecyclerView.ViewHolder(bind.root), KoinComponent {
    private val viewModel: ArticleListItemViewModel = get()

    init {
        bind.viewModel = viewModel
    }

    fun bindItem(item: Article) {
        bind.textView.text = item.title
        bind.item = item
    }
}
