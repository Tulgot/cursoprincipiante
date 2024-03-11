package com.aristidevs.androidmaster.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R

class SuperheroAdapter(var superheroList: List<SuperheroItemResponse> = emptyList(),
                       private val onItemSelected:(String)->Unit):
    RecyclerView.Adapter<SuperheroViewHolder>(){

    fun updateList(list: List<SuperheroItemResponse>){
        superheroList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero,parent,false)
        return SuperheroViewHolder(view)
    }

    override fun getItemCount(): Int = superheroList.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheroList[position], onItemSelected)
    }
}