package com.hurist.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.hurist.wanandroid.data.Response
import com.hurist.wanandroid.data.Retrofit
import com.hurist.wanandroid.data.responseData.ArticleList
import com.hurist.wanandroid.extension.getFormatDate
import com.hurist.wanandroid.extension.logd
import com.hurist.wanandroid.network.*
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val banner = MutableLiveData<String>()
    val articleListPage = MutableLiveData(0)   //如果不设置value可以让订阅者订阅时不会收到消息

    private val _loadState = MutableLiveData<LoadState>()
    val loadState = Transformations.distinctUntilChanged(_loadState)

    val data = Transformations.switchMap(articleListPage) {
        liveData {
            emit(safeRequestCall(_loadState) {
                return@safeRequestCall measureTotalTime("Fetch") {
                    fetchData(it)
                }
            })
        }

    }

    // 非并发模式
    private suspend fun fetchData(page: Int): Response<ArticleList> {
        val indexArticle = Retrofit.api.getIndex(page)
        if (indexArticle.isFailure()) {
            return indexArticle
        }
        if (page == 0) {
            val topArticle = Retrofit.api.fetchTopArticle()
            if (topArticle.isFailure()) {
                // 如果获取置顶文章失败就将错误信息放在indexResult里返回, 暂时的缓兵之计，感觉不太好
                return indexArticle.copy(topArticle.errorCode, topArticle.errorMsg)
            }
            return indexArticle.apply {
                this.data.datas.addAll(0, topArticle.data)
            }
        }
        return indexArticle
    }

    /**
     * 并发模式请求数据
     * 这种在有多个请求并发时花时间更短，注意不要在async后直接跟await，这样会跟同步的花的时间一毛一样
     */
    private suspend fun fetchDataAsync(page: Int): Response<ArticleList> {
        return withContext(Dispatchers.IO) {
            val indexArticle = async { Retrofit.api.getIndex(page) }
            if (page == 0) {
                val topArticle = async { Retrofit.api.fetchTopArticle() }

                val indexResult = indexArticle.await()
                val topResult = topArticle.await()
                if (indexResult.isFailure()) {
                    return@withContext indexResult
                }
                if (topResult.isFailure()) {
                    // 如果获取置顶文章失败就将错误信息放在indexResult里返回
                    return@withContext indexResult.copy(
                        errorCode = topResult.errorCode,
                        errorMsg = topResult.errorMsg
                    )
                }

                return@withContext indexResult.apply {
                    data.datas.addAll(0, topResult.data)
                }
            }
            return@withContext indexArticle.await()
        }
    }

    fun refresh() {
        articleListPage.value = 0
    }

    fun loadNextPageArticle() {
        articleListPage.value = articleListPage.value!! + 1
    }

}