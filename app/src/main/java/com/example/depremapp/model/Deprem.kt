package com.example.depremapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deprem(
    @SerialName("boylam")
    val boylam: String,
    @SerialName("buyukluk")
    val buyukluk: String,
    @SerialName("derinlik")
    val derinlik: String,
    @SerialName("enlem")
    val enlem: String,
    @SerialName("saat")
    val saat: String,
    @SerialName("sehir")
    val sehir: String,
    @SerialName("tarih")
    val tarih: String,
    @SerialName("yer")
    val yer: String
)