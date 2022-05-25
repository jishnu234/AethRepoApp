package com.example.authrepoapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.authrepoapp.R
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.model.Userdata
import com.example.authrepoapp.view.UserListAdapter.*

class UserListAdapter (private val userList: SquareModel): RecyclerView.Adapter<ViewHolder>() {

//    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet (newDataSet: SquareModel?) {
        userList.clear()
        newDataSet?.let {
            userList.addAll(newDataSet)
        }
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val listTextView: TextView = view.findViewById(R.id.list_user_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_content, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listTextView.text = userList[position].description
    }

    override fun getItemCount() = userList.size
}