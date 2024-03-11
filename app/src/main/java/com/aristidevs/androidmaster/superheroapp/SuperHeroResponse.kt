package com.aristidevs.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperheroItemResponse>,
    )

data class SuperheroItemResponse(
    @SerializedName("id") val superheroid: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroimage: SuperHeroImage,

    )

data class SuperHeroImage(
    @SerializedName("url") val url: String,
)

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: SuperHeroPowerstats,
    @SerializedName("image") val image: SuperHeroDetailImage,
    @SerializedName("biography") val biography: Biography,


    )

data class SuperHeroPowerstats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String,
)

data class SuperHeroDetailImage(
    @SerializedName("url") val url: String
)

data class Biography(
    @SerializedName("full-name") val fullname: String,
    @SerializedName("publisher") val publisher: String,

)
