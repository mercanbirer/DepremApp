package com.example.deprem.api

import com.example.deprem.model.Deprem
import retrofit2.http.GET
import retrofit2.http.Query

interface DepremApi {
    //https://turkiyedepremapi.herokuapp.com/api

    @GET("api")
    suspend fun getDeprem(
        @Query("api") api : String,
    ): List<Deprem>
}