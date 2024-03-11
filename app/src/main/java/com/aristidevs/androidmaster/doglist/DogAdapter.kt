package com.aristidevs.androidmaster.doglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R
import com.squareup.picasso.Picasso

class DogAdapter(private val DogItemList: List<String> = emptyList()):
    RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    class DogViewHolder(view:View): RecyclerView.ViewHolder(view){

        val ivImage: ImageView = view.findViewById(R.id.ivImage)

        fun render(DogItemList: String){
            Picasso.get().load(DogItemList).into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dogitems,parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int = DogItemList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.render(DogItemList[position])
    }

}