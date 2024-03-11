package com.aristidevs.androidmaster.doglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.androidmaster.databinding.ActivityDogListBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogListBinding
//    private val dogslist = listOf("text1","text2","text3","text4")
    private lateinit var adapter: DogAdapter
    private val TAG: String = "dogbreedsearch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toast = Toast.makeText(this, "favor de escribir la raza de perro", Toast.LENGTH_SHORT).show()

        binding.searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchByName(query.lowercase())
                }else{
                    toast
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })


    }

    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = getRetrofit()
                .create(ApiDogList::class.java)
                .ListOfDogs(query)

            val mylistofdogs = myResponse.body()?.message
            if (myResponse.isSuccessful){
                Log.i("dogbreedsearch", "si funciona =)")
                Log.i(TAG, "searchByName: $mylistofdogs")

                runOnUiThread{
                    if (mylistofdogs != null) {
                        dogAdapter(mylistofdogs)
                    }
                }
            }else{
                Log.i(TAG, "no funicona =(")
            }
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://dog.ceo")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun dogAdapter(doglist: List<String>){
        adapter = DogAdapter(doglist)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }


}