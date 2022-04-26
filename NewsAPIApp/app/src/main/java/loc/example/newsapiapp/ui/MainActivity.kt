package loc.example.newsapiapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import loc.example.newsapiapp.databinding.ActivityMainBinding
import loc.example.newsapiapp.ext.hide
import loc.example.newsapiapp.ext.show
import loc.example.newsapiapp.model.UiState
import loc.example.newsapiapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private val viewModel by viewModel<NewsViewModel>()
    private val articleAdapter by lazy { ArticleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        viewModel.fetchLatestNews()
        lifecycleScope.launchWhenStarted {
            viewModel.articlesFlow.collect {
                when (it) {
                    is UiState.Loaded -> {
                        bind.progressBar.hide()
                        articleAdapter.submitList(it.data)
                    }
                    UiState.Loading -> bind.progressBar.show()
                    is UiState.Error -> {}
                }
            }
        }

        bind.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = articleAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity,
                DividerItemDecoration.VERTICAL))
        }
    }
}
