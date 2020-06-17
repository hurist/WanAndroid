package com.hurist.wanandroid.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hurist.wanandroid.data.Response
import com.hurist.wanandroid.data.Retrofit
import com.hurist.wanandroid.extension.getFormatDate
import com.hurist.wanandroid.extension.logd

suspend fun <T> safeRequestCall(loadState: MutableLiveData<LoadState>, call: suspend() -> Response<T>): Result<T> {

    return try {
        val response = call()
        loadState.postValue(LoadState.Finish)
        if (response.isSuccess()) {
            Result.Success(response.data)
        } else {
            Result.Error(response.errorCode, response.errorMsg)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        loadState.postValue(LoadState.Finish)
        Result.Error(-1, e.message.toString() )
    }
}

suspend fun <T> measureTotalTime(tag: String, call: suspend() -> Response<T>): Response<T> {
    Log.d("&Measure $tag start：", System.currentTimeMillis().getFormatDate())
    val result = call()
    Log.d("&Measure $tag end：", System.currentTimeMillis().getFormatDate())
    return result
}