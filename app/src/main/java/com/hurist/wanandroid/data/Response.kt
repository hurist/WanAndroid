package com.hurist.wanandroid.data

data class Response<T>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    val data: T
) {
    fun isSuccess(): Boolean {
        return errorCode == 0
    }

    fun isFailure(): Boolean {
        return errorCode != 0
    }
}

val ErrorResponse = Response(0, "", Any())

