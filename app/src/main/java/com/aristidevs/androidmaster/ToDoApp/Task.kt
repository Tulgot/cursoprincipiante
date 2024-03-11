package com.aristidevs.androidmaster.ToDoApp

data class Task(val name:String, val category: TaskCategory, var isSelected: Boolean = false) {
}