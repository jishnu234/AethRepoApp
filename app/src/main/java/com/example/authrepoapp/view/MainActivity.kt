package com.example.authrepoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authrepoapp.R
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.model.Userdata
import com.example.authrepoapp.viewmodel.ListUserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListUserViewModel
    private var listAdapter = UserListAdapter(SquareModel())
    private lateinit var recyclerView: RecyclerView
    private lateinit var circleLoading: ProgressBar
    private lateinit var errorText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ListUserViewModel::class.java]
        viewModel.refresh()

         recyclerView = findViewById(R.id.recyclerView)
         circleLoading  = findViewById(R.id.circular_indicator)
         errorText  = findViewById(R.id.errorText)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
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
                    Toast.makeText(this, it[0].description, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.error.observe(this) {
             it?.let { errorText.visibility = if (it) View.VISIBLE else View.GONE }
        }

        viewModel.loading.observe(this) {
            it?.let { circleLoading.visibility = if (it) View.VISIBLE else View.GONE }

            if (it) {
                errorText.visibility = View.GONE
                recyclerView.visibility = View.GONE
            } else {
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
}