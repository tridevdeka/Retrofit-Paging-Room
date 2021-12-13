package com.tridev.retrofitpagingapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.tridev.retrofitpagingapp.R
import com.tridev.retrofitpagingapp.adapters.OnItemClicked
import com.tridev.retrofitpagingapp.adapters.RemoteUserAdapter
import com.tridev.retrofitpagingapp.adapters.UserAdapter
import com.tridev.retrofitpagingapp.databinding.ActivityMainBinding
import com.tridev.retrofitpagingapp.db.UserEntity
import com.tridev.retrofitpagingapp.viewmodel.LocalViewModel
import com.tridev.retrofitpagingapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnItemClicked{
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()

    @Inject
    lateinit var remoteUserAdapter: RemoteUserAdapter
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this, AddUserDetails::class.java))
        }

        binding.btnRemote.setOnClickListener {
            binding.recyclerviewRemote.isVisible = true
            binding.recyclerviewLocal.isVisible = false
            val highlight = resources.getDrawable(R.drawable.bg_button)
            binding.btnRemote.setBackground(highlight)
            binding.btnLocal.setBackground(resources.getDrawable(R.drawable.bg_border))

            initialization()
        }

        binding.btnLocal.setOnClickListener {
            binding.btnAdd.isVisible=true
            binding.recyclerviewRemote.isVisible = false
            val highlight = resources.getDrawable(R.drawable.bg_button)
            binding.btnLocal.setBackground(highlight)
            binding.btnRemote.setBackground(resources.getDrawable(R.drawable.bg_border))
            setupRecyclerView()
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllUsers.collectLatest {
                remoteUserAdapter.submitData(it)
            }
        }


    }

    private fun initialization() {
        binding.apply {
            recyclerviewRemote.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 1)
                adapter = remoteUserAdapter
            }
        }
    }



    private fun setupRecyclerView() {

        userAdapter = UserAdapter(this)

        binding.recyclerviewLocal.apply {
            adapter = userAdapter
        }

        localViewModel.users.observe(this) {
            updateUi(it)
            userAdapter.usersList = it
        }
    }

    private fun updateUi(list: List<UserEntity>) {
        if (list.isNotEmpty()) {
            binding.recyclerviewLocal.visibility = View.VISIBLE
        } else {
            binding.recyclerviewLocal.visibility = View.GONE
        }
    }

    override fun onItemClicked(userEntity: UserEntity) {
        localViewModel.deleteUsers(userEntity)
    }

}