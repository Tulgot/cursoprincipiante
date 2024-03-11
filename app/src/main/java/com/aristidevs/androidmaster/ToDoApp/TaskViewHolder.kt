package com.aristidevs.androidmaster.ToDoApp

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
    private val tvTask: TextView = view.findViewById(R.id.tvTask)

    fun render(task: Task){
        tvTask.text = task.name
        val cbtaskcolor = when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Personal -> R.color.todo_perosnal_category
            TaskCategory.Other -> R.color.todo_other_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(
        ContextCompat.getColor(cbTask.context, cbtaskcolor)
        )

        cbTask.isChecked = task.isSelected

        if(task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}