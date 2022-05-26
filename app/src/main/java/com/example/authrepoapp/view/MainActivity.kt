package com.example.authrepoapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.authrepoapp.R
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.viewmodel.ListUserViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: ListUserViewModel
    private lateinit var listAdapter: UserListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var shimmerAnim: ShimmerFrameLayout
    private lateinit var errorText: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hooks
        viewModel = ViewModelProvider(this)[ListUserViewModel::class.java]
        listAdapter = UserListAdapter(SquareModel(), this)
        recyclerView = findViewById(R.id.recyclerView)
        shimmerAnim  = findViewById(R.id.shimmer_loading)
        errorText  = findViewById(R.id.errorText)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setOnRefreshListener(this)

        viewModel.refresh()
        recyclerView.apply {
            layoutManager = LinearLayoutManager( context )
            adapter = listAdapter
        }

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.userData.observe (this) { countries ->
            countries.let {
                Log.d("TAG", "observeViewModel: Data Updated ${it}")
                it?.let {
                    listAdapter.updateDataSet(it)
//                    recyclerView.adapter = listAdapter
                }
            }
        }

        viewModel.error.observe(this) {
             it?.let { errorText.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe(this) {
            it?.let { shimmerAnim.visibility = if (it) View.VISIBLE else View.GONE }

            if (it) {
                errorText.visibility = View.GONE
                recyclerView.visibility = View.GONE
            } else {
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onRefresh() {
        viewModel.refresh()
        swipeRefreshLayout.isRefreshing = false
    }
}