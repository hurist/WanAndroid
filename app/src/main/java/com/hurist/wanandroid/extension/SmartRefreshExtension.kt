package com.hurist.wanandroid.extension

import com.scwang.smartrefresh.layout.SmartRefreshLayout

fun SmartRefreshLayout.finish() {
    finishLoadMore()
    finishRefresh()
}

fun SmartRefreshLayout.onRefreshAndLoadMore(
    refreshBlock: () -> Unit,
    loadMoreBlock: () -> Unit
) {
    setEnableLoadMore(true)
    setOnLoadMoreListener { loadMoreBlock() }
    setOnRefreshListener { refreshBlock() }
}