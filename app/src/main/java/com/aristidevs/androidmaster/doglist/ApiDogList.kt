package com.aristidevs.androidmaster.doglist


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiDogList {
    @GET("api/breed/{breed}/images")
    suspend fun ListOfDogs(@Path("breed") breed: String): Response<DogResponseList>
}
