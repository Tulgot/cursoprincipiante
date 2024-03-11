package com.aristidevs.androidmaster.rickandmorty

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRickandMorty {

    @GET("api/character/")
    suspend fun Response(@Query("name")  name: String): Response<ListRickandMorty>

//    fun Response(): Call<ListRickandMorty>


}