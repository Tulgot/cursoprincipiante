package com.aristidevs.androidmaster.rickandmorty

import com.google.gson.annotations.SerializedName


data class ListRickandMorty(@SerializedName("info") val info: info,
                            @SerializedName("results") val results: List<results>)

data class info(
    val count: Int,
    val pages: Int,
)

data class results(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: List<origin>,
    val location: List<location>,
    val episode: List<String>,
    val url: String,
    val created: String

)

data class origin(
    val name: String,
    val url: String
)

class location(
    val name: String,
    val url: String
)

