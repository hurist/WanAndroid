package com.hurist.wanandroid.extension

import android.annotation.SuppressLint
import android.provider.ContactsContract
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

@SuppressLint("SimpleDateFormat")
fun Long.getFormatDate(): String {
    val date = Date(this)
    return SimpleDateFormat("HH:mm:ss:SSS").format(date)
}