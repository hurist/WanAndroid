package com.hurist.wanandroid.utils

import androidx.lifecycle.*

/**
 * 1、在observer的时候返回全部值
 * 2、在setValue的时候清空内部数据
 * 3、在addValue的时候返回新的数据
 */

class ListLiveData <T> : LiveData<T>() {

    private val datas = arrayListOf<T>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    fun addValue() {

    }
}