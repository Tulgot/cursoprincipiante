package com.aristidevs.androidmaster.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.aristidevs.androidmaster.databinding.ActivityRickandMortyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class RickandMortyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRickandMortyBinding
    private val TAG: String = "listrickandmorty"
    private lateinit var adapter: RickandmortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickandMortyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchbyname(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false

        })


    }

    private fun searchbyname(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val myresponse = getRetrofit().create(ApiRickandMorty::class.java).Response(query)
            val rickandmortylist = myresponse.body()?.results
            if (myresponse.isSuccessful) {
                Log.i(TAG, "onCreate: success")
                Log.i(TAG, "onCreate: ${myresponse.body().toString()}")
                runOnUiThread {
                    if (rickandmortylist != null){
                        rickandmortyadapter(rickandmortylist)
                    }

                }
            } else {
                Log.i(TAG, "onCreate: fail")
            }
        }
    }

    private fun rickandmortyadapter(listrickanmorty: List<results>?) {
        adapter = RickandmortyAdapter(listrickanmorty)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//        val api = Retrofit
//            .Builder()
//            .baseUrl("https://rickandmortyapi.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(ApiRickandMorty::class.java)
//
//        api.Response().enqueue(object : Callback<ListRickandMorty>{
//            override fun onResponse(
//                call: Call<ListRickandMorty>,
//                response: Response<ListRickandMorty>
//            ) {
//                if (response.isSuccessful){
//                    Log.i(TAG, "onResponse: success")
//                }
//            }
//
//            override fun onFailure(call: Call<ListRickandMorty>, t: Throwable) {
//                Log.i(TAG, "onFailure: ${t.message}")
//            }
//
//        })
    }
}
