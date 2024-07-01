package com.example.cookbook

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookbook.databinding.SearchRvBinding

class SearchAdapter(var dataList: ArrayList<Recipe>, var context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.searchImg)
        holder.binding.searchTxt.text = dataList.get(position).tittle
    }

    //implementing filter function in search bar
    fun filterList(filterList:ArrayList<Recipe>){
        dataList=filterList
        notifyDataSetChanged()
    }




}