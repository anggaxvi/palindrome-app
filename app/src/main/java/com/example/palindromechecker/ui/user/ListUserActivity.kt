package com.example.palindromechecker.ui.user

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.palindromechecker.R
import com.example.palindromechecker.data.response.DataItem
import com.example.palindromechecker.databinding.ActivityListUserBinding
import com.example.palindromechecker.ui.ViewModelFactory
import com.example.palindromechecker.ui.main.MainActivity

class ListUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListUserBinding
    private val viewModel: UserViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvListuser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvListuser.addItemDecoration(itemDecoration)

        viewModel.user.observe(this) {
            viewModel.user.observe(this) {
                setData(it)
            }
        }

        binding.btnBackmain.setOnClickListener {
            startActivity(Intent(this@ListUserActivity, MainActivity::class.java))
        }
    }

    private fun setData(data: PagingData<DataItem>) {
        binding.swipeRefresh.isRefreshing = false
        val adapter = UserPagingAdapter()
        binding.rvListuser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.submitData(lifecycle, data)
        adapter.addLoadStateListener {loadStates ->
//            binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            binding.tvEmpty.isVisible = loadStates.refresh is LoadState.Error && adapter.itemCount == 0
        }

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.setItemClickAdapter(object : UserPagingAdapter.OnItemClickAdapter {
            override fun onItemClick(datta: DataItem) {
                val intent = Intent(this@ListUserActivity, MainActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_NAME, "${datta.firstName} ${datta.lastName}")
                intent.putExtra(MainActivity.EXTRA_EMAIL, datta.email)
                intent.putExtra(MainActivity.EXTRA_IMG, datta.avatar)
                startActivity(intent)

            }

        })


    }




}