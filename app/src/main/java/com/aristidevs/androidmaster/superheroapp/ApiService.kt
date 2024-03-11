package com.aristidevs.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api.php/7082718481787928/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroResponse>

    @GET("/api.php/7082718481787928/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroid: String): Response<SuperHeroDetailResponse>
}