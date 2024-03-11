package com.aristidevs.androidmaster.ToDoApp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.ToDoApp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoActivity : AppCompatActivity() {
    private lateinit var rvCategoria: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var rvTask: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton

    private val listofcategories = listOf(
        Other,
        Business,
        Personal
    )
    private val listoftasks = mutableListOf(
        Task("PruebaBusiness", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOther", Other)
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        initComponent()
        initUI()
        initListener()
    }

    private fun initListener() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            if(etTask.text.isEmpty()){
                Toast.makeText(this, "Nombre de tarea vacia", Toast.LENGTH_SHORT).show()
            }else{
                val selectedid = rgCategories.checkedRadioButtonId
                val selectedradiobutton: RadioButton = rgCategories.findViewById(selectedid)
                val currentCategory: TaskCategory = when(selectedradiobutton.text){
                    getString(R.string.todo_app_business)->Business
                    getString(R.string.todo_app_personal)->Personal
                    else -> Other
                }
                listoftasks.add(Task(etTask.text.toString(), currentCategory))
                updateTask()
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(listofcategories){onCategorySelected(it)}
        rvCategoria.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategoria.adapter = categoriesAdapter

        taskAdapter = TaskAdapter(listoftasks){position -> onItemSelected(position)}
        rvTask.layoutManager = LinearLayoutManager(this)
        rvTask.adapter = taskAdapter
    }

    private fun initComponent() {
        rvCategoria = findViewById(R.id.rvCategoria)
        rvTask = findViewById(R.id.rvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun onItemSelected(position: Int){
        listoftasks[position].isSelected = !listoftasks[position].isSelected
        updateTask()
    }

    private fun onCategorySelected(position: Int){
        listofcategories[position].isSelected = !listofcategories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }

    private fun updateTask(){
        val selectedCategories: List<TaskCategory> = listofcategories.filter { it.isSelected }
        val newtask = listoftasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.task = newtask
        taskAdapter.notifyDataSetChanged()
    }
}