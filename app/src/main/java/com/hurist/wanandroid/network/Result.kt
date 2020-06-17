package com.hurist.wanandroid.network

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val errorCode: Int = 0, val errorMsg:String = ""): Result<Nothing>()
}

