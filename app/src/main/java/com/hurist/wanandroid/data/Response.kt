package com.hurist.wanandroid.data

data class Response<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
) {
    fun isSuccess(): Boolean {
        return errorCode == 0
    }
}