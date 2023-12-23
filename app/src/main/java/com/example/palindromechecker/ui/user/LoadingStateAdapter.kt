package com.example.palindromechecker.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.palindromechecker.databinding.ItemLoadingBinding

class LoadingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateAdapter.LoadingHolder>() {
    class LoadingHolder(private val binding : ItemLoadingBinding,retry: () -> Unit) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState:LoadState){
            if (loadState is LoadState.Error){
                binding.errorMsg.text = loadState.error.localizedMessage
            }

            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }

    }

    override fun onBindViewHolder(holder: LoadingHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadingHolder(binding,retry)
    }

}