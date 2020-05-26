package com.hurist.wanandroid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*



fun main() {
    GlobalScope.launch {
        testA()
        print("HAHAHAHAHA")
        testB()
    }
}

suspend fun testA() {

}
suspend fun testB() {

}