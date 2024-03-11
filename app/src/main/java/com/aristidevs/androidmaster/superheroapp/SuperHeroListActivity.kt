package com.aristidevs.androidmaster.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.androidmaster.databinding.ActivitySuperHeroListBinding
import com.aristidevs.androidmaster.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private val TAG: String = "superheroapi"
    private lateinit var adapter: SuperheroAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniUI()

    }

    private fun iniUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        adapter = SuperheroAdapter{navigateToDetail(it)}
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter

    }


    private fun searchByName(query:String) {
        binding.progressbar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myresponse = getretrofig().create(ApiService::class.java).getSuperheroes(query)
            if (myresponse.isSuccessful){
                val response: SuperHeroResponse? = myresponse.body()
                if (response != null){
                    Log.i(TAG, response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroes)
                        binding.progressbar.isVisible = false

                    }
                }
                Log.i(TAG, "si funicona =)")
            }else{
                Log.i(TAG, "no funciona =(")
            }
        }
    }

    private fun getretrofig(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String){
     val intent = Intent(this, DetailSuperheroActivity::class.java)
     intent.putExtra(EXTRA_ID,id)
     startActivity(intent)
    }

}