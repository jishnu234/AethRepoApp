package com.example.authrepoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authrepoapp.di.DaggerSquareComponent
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.model.network.SquareService
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListUserViewModel : ViewModel() {

    val userData = MutableLiveData<SquareModel>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    private val TAG = "ListUserViewModel"

    @Inject
    lateinit var squareService: SquareService

    init {
        DaggerSquareComponent.create().inject(this)
    }

    fun refresh() {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        loading.value = true
        try {
            if (squareService.getSquare().isSuccessful) {
                userData.value = squareService.getSquare().body()
                Log.d(TAG, "fetchData: ${userData.value}")
                loading.value = false
            }

        } catch (exception: Exception) {
            loading.value = false
            error.value = true
            exception.printStackTrace()
        }
    }
}