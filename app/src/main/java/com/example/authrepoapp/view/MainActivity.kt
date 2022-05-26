package com.example.authrepoapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.authrepoapp.databinding.ActivityMainBinding
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.viewmodel.ListUserViewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: ListUserViewModel
    private lateinit var listAdapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //hooks
        viewModel = ViewModelProvider(this)[ListUserViewModel::class.java]
        listAdapter = UserListAdapter(SquareModel(), this)

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        viewModel.refresh()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.userData.observe(this) { countries ->
            countries.let {
                Log.d("TAG", "observeViewModel: Data Updated ${it}")
                it?.let {
                    listAdapter.updateDataSet(it)
                }
            }
        }

        viewModel.error.observe(this) {
            it?.let {
                binding.connectionLostScreen.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    binding.connectionLostScreen.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.connectionLostScreen.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loading.observe(this) {
            it?.let { binding.shimmerLoading.visibility = if (it) View.VISIBLE else View.GONE }

            if (it) {
                binding.connectionLostScreen.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onRefresh() {
        viewModel.refresh()
        binding.swipeRefreshLayout.isRefreshing = false
    }
}