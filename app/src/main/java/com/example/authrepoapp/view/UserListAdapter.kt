package com.example.authrepoapp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.authrepoapp.R
import com.example.authrepoapp.model.SquareModel
import com.example.authrepoapp.view.UserListAdapter.*

class UserListAdapter (private val userList: SquareModel, private val context: Context):
    RecyclerView.Adapter<ViewHolder>() {

//    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet (newDataSet: SquareModel?) {
        userList.clear()
        newDataSet?.let {
            userList.addAll(newDataSet)
        }
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val listNameText: TextView = view.findViewById(R.id.list_name_text)
        val listDescText: TextView = view.findViewById(R.id.list_desc_text)
        val listImage: ImageView = view.findViewById(R.id.list_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_content, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listNameText.text = userList[position].name.replace(Regex("""[_,-]"""), " ").uppercase() ?: ""
        holder.listDescText.text = userList[position].description?.take(30)?.plus("..") ?: "seems to have no description"
        Log.d("TAG", "onBindViewHolder: ${userList[position].owner.avatarUrl}")
        Glide.with(context)
            .load(userList[position].owner.avatarUrl ?:
            "https://avatars.githubusercontent.com/u/82592?v=4")
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.listImage)
    }

    override fun getItemCount() = userList.size
}