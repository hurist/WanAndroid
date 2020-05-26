package com.hurist.wanandroid.viewmodel

import androidx.lifecycle.*
import com.hurist.wanandroid.data.Retrofit
import com.hurist.wanandroid.data.responseData.ArticleList
import com.hurist.wanandroid.network.LoadState
import com.hurist.wanandroid.network.safeRequestCall
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {

    private val page = MutableLiveData(0)   //如果不设置value可以让订阅者订阅时不会收到消息
    val loadState = MutableLiveData<LoadState>()

    val data = Transformations.switchMap(page) {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            loadState.postValue(LoadState.Loading)
            emit(safeRequestCall<ArticleList> {
                Retrofit.api.getIndex(it)
            })
            loadState.postValue(LoadState.Finish)
        }
    }

    fun refresh() {
        page.value = 0
    }

    fun loadNextPage() {
        page.value = page.value!! + 1
    }

}