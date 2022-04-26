package loc.example.newsapiapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import loc.example.newsapiapp.model.Article

private const val TAG = "ArticleListItemViewMode"

class ArticleListItemViewModel : ViewModel() {

    fun onItemClick(item: Article) {
        Log.d(TAG, "onClick: item: ${item.id}")
    }
}