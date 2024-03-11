package com.aristidevs.androidmaster.rickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.RickandmortyItemBinding
import com.squareup.picasso.Picasso

class RickandmortyAdapter(val rickandmortylist: List<results>?):
    RecyclerView.Adapter<RickandmortyAdapter.RickandmortyHolder>() {

    class RickandmortyHolder (view: View): RecyclerView.ViewHolder(view){

        private val binding = RickandmortyItemBinding.bind(view)

        fun render(listRickandMorty: results) {
            Picasso.get().load(listRickandMorty.image).into(binding.ivCharacter)
            binding.tvName.text = listRickandMorty.name
            binding.tvSpecies.text = listRickandMorty.species
            binding.tvGender.text = listRickandMorty.gender
            binding.tvStatus.text = listRickandMorty.status

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickandmortyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rickandmorty_item, parent, false)
        return RickandmortyHolder(view)
    }

    override fun getItemCount(): Int = rickandmortylist?.size!!

    override fun onBindViewHolder(holder: RickandmortyHolder, position: Int) {
        holder.render(rickandmortylist!![position])
    }
}