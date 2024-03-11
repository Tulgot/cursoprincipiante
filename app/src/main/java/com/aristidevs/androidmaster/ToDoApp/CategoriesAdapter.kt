package com.aristidevs.androidmaster.ToDoApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R

class CategoriesAdapter(private val categories: List<TaskCategory>, private val onItemSelected:(Int)->Unit):
    RecyclerView.Adapter<CategoriesHolder>() {

    //Crear Lyout de vista (diseño)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesHolder(view)
    }

    //cantidad de datos por cargar
    override fun getItemCount(): Int = categories.size


    //insertar datos en diseño
    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.render(categories[position], onItemSelected)

    }
}