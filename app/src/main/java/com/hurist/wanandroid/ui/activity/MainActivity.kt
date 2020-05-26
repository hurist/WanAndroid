package com.hurist.wanandroid.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hurist.wanandroid.*
import com.hurist.wanandroid.extension.finish
import com.hurist.wanandroid.extension.onRefreshAndLoadMore
import com.hurist.wanandroid.network.Result.*
import com.hurist.wanandroid.network.LoadState
import com.hurist.wanandroid.ui.adapter.HomePageArticleAdapter
import com.hurist.wanandroid.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val adapter by lazy {
        HomePageArticleAdapter(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvArticles.adapter = adapter

        viewModel.loadState.observe(this, Observer {
            if (it == LoadState.Loading) {
                Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Loading Finish", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.data.observe(this, Observer {
            smartRefresh.finish()
            when(it) {
                is Success -> if(it.data.curPage == 1) adapter.setData(it.data.datas) else adapter.addData(it.data.datas)
                is Error -> Toast.makeText(this, it.errorMsg, Toast.LENGTH_LONG).show()
            }
        })

        smartRefresh.onRefreshAndLoadMore(viewModel::refresh, viewModel::loadNextPage)
    }
}
