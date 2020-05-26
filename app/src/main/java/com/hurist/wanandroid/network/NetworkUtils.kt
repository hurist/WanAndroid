package com.hurist.wanandroid.network

import com.hurist.wanandroid.data.Response

suspend fun <T> safeRequestCall(call: suspend() -> Response<T>): Result<T> {
    return try {
        val response = call()
        if (response.isSuccess()) {
            Result.Success(response.data)
        } else {
            Result.Error(
                response.errorCode,
                response.errorMsg
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Result.Error(-1, "发生了一些错误")
    }
}