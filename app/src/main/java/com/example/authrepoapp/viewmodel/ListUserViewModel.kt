package com.example.authrepoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.authrepoapp.model.Userdata

class ListUserViewModel: ViewModel() {

     val userData =  MutableLiveData<ArrayList<Userdata>>()
     val loading = MutableLiveData<Boolean>()
     val error = MutableLiveData<Boolean>()

    fun refresh() {
        fetchData()
    }

    private fun fetchData() {
        userData.value = arrayListOf(
            Userdata("CountryA"),
            Userdata("CountryB"),
            Userdata("CountryC"),
            Userdata("CountryD"),
            Userdata("CountryE"),
            Userdata("CountryF"),
            Userdata("CountryG"),
            Userdata("CountryH"),
        )
        loading.value = false
        error.value = false
    }
}