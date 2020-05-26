package com.hurist.wanandroid.network

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val errorCode: Int, val errorMsg:String): Result<Nothing>()
}