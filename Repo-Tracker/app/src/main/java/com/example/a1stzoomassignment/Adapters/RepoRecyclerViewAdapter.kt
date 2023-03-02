package com.example.a1stzoomassignment.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a1stzoomassignment.DataModels.Repo
import com.example.a1stzoomassignment.Helpers.Constants
import com.example.a1stzoomassignment.R
import com.example.a1stzoomassignment.databinding.RepoViewholderBinding

class RepoRecyclerViewAdapter(val context : Context, val showDialog:(Repo)-> Unit):
    ListAdapter<Repo, RepoRecyclerViewAdapter.RepoViewHolder>(
        Diffcallback
    ) {

    val animation = AnimationUtils.loadAnimation(context, R.anim.viewholder_fade_in)

    class RepoViewHolder(private var binding: RepoViewholderBinding): RecyclerView.ViewHolder(binding.root){

        val vhLay = binding.itemLay

        fun bind(repo: Repo){
            binding.apply {
                ownerNameTv.text = repo.ownerName
                repositaryNameTv.text = repo.repoName
                repoDescTv.text = repo.repoDescription
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
        holder.itemView.startAnimation(animation)
        holder.vhLay.setOnClickListener {
            showDialog(repo)
        }
    }
    companion object{
        private val Diffcallback = object: DiffUtil.ItemCallback<Repo>(){
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.repoName == newItem.repoName && oldItem.ownerName == newItem.ownerName
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }

}