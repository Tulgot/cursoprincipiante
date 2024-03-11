package com.aristidevs.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected:(String)->Unit){
        binding.tvheroname.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.superheroimage.url).into(binding.ivheroimage)
        binding.root.setOnClickListener{onItemSelected(superheroItemResponse.superheroid) }
    }
}