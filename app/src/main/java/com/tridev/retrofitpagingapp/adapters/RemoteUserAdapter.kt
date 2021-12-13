package com.tridev.retrofitpagingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tridev.retrofitpagingapp.data.Users
import com.tridev.retrofitpagingapp.databinding.EachRowBinding
import javax.inject.Inject

class RemoteUserAdapter @Inject constructor(): PagingDataAdapter<Users, RemoteUserAdapter.RemoteUsersViewHolder>(Diff()) {


    override fun onBindViewHolder(holder: RemoteUsersViewHolder, position: Int) {

        val users=getItem(position)
        if (users != null) {
            holder.bind(users)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteUsersViewHolder {
        return RemoteUsersViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    class RemoteUsersViewHolder(private val binding: EachRowBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(users: Users){
            binding.apply {
                img.load(users.url)
                txtUserName.text=users.firstName+" "+users.lastName
                txtEmail.text=users.email
                txtId.text=users.id
            }
        }
    }

    class Diff:DiffUtil.ItemCallback<Users>(){
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean =
            oldItem.url==newItem.url

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean =
            oldItem==newItem

    }

}