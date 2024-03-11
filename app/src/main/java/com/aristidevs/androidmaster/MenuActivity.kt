package com.aristidevs.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aristidevs.androidmaster.ToDoApp.ToDoActivity
import com.aristidevs.androidmaster.doglist.DogListActivity
import com.aristidevs.androidmaster.firstapp.FirstAppActivity
import com.aristidevs.androidmaster.imccalculator.imcCalculatorActivity
import com.aristidevs.androidmaster.rickandmorty.RickandMortyActivity
import com.aristidevs.androidmaster.settings.SettingsActivity
import com.aristidevs.androidmaster.superheroapp.SuperHeroListActivity

class MenuActivity : AppCompatActivity() {
    lateinit var btnSaludApp : Button
    lateinit var btnIMCApp : Button
    lateinit var btnToDo: Button
    lateinit var btnSuperHeroApp: Button
    lateinit var btnDogList: Button
    lateinit var btnRickandMorty: Button
    lateinit var btnSettings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        initComponents()
        initListener()

    }

    private fun initListener() {
        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnToDo.setOnClickListener { navigateToToDo() }
        btnSuperHeroApp.setOnClickListener{ navigateToSuperHeroApp() }
        btnDogList.setOnClickListener{ navigateToDogList() }
        btnRickandMorty.setOnClickListener{ navigateToRickandMorty() }
        btnSettings.setOnClickListener{ navigateToSettings() }
    }

    private fun navigateToRickandMorty() {
        val intent = Intent(this, RickandMortyActivity::class.java)
        startActivity(intent)
    }

    private fun initComponents() {
        btnSaludApp = findViewById(R.id.btnSaludApp)
        btnIMCApp = findViewById(R.id.btnIMCApp)
        btnToDo = findViewById(R.id.btnToDo)
        btnSuperHeroApp = findViewById(R.id.btnSuperHeroApp)
        btnDogList = findViewById(R.id.btnDogList)
        btnRickandMorty = findViewById(R.id.btnRyckandmorty)
        btnSettings = findViewById(R.id.btnSettings)
    }

    private fun navigateToDogList() {
        val intent = Intent(this, DogListActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCApp() {
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToToDo() {
        val intent = Intent(this, ToDoActivity::class.java)
        startActivity(intent)
    }
//    private fun navigateToRickandMorty() {
//        val intent = Intent(this, RickandMortyActivity::class.java)
//        startActivity(intent)
//    }
}