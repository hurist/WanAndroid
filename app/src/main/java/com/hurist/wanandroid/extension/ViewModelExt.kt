package com.hurist.wanandroid.extension

import android.util.Log
import androidx.lifecycle.ViewModel

fun ViewModel.logd(tag: String = this.javaClass.simpleName, info: String) = Log.d(tag, info)
