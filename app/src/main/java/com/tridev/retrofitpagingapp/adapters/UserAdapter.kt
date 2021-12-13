package com.tridev.retrofitpagingapp.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tridev.retrofitpagingapp.activities.AddUserDetails
import com.tridev.retrofitpagingapp.databinding.UserItemContainerBinding
import com.tridev.retrofitpagingapp.db.UserEntity

class UserAdapter(private val onClicked:OnItemClicked) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    val context: Context?=null

    inner class UserViewHolder(val binding: UserItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)
    var usersList: List<UserEntity>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemContainerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = usersList[position]
        holder.binding.apply {
            txtUser.text = users.firstName+users.lastName
            txtMail.text = users.email
            txtUserId.text = users.id.toString()

            btnDelete.setOnClickListener {
                onClicked.onItemClicked(users)
            }

            btnEdit.setOnClickListener{
                context?.startActivity(Intent(context,AddUserDetails::class.java))
            }

        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }


}

interface OnItemClicked {
    fun onItemClicked(userEntity: UserEntity)
}