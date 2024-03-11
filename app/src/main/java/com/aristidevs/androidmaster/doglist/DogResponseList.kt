package com.aristidevs.androidmaster.doglist

import com.google.gson.annotations.SerializedName

data class DogResponseList(@SerializedName("message") val message: List<String>,
                           @SerializedName("status") val status: String)
