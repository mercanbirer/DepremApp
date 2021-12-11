package com.example.deprem.repository

import com.example.deprem.api.DepremApi
import com.example.deprem.data.Resource
import com.example.deprem.model.Deprem
import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class DepremRepository @Inject constructor(
    private val depremApi: DepremApi,
) {



    fun getDeprems(
        api : String
    ): Flow<Resource<List<Deprem>>> {
        return flow {
            emit(Resource.loading(null))
            val movies = depremApi.getDeprem(
                api = api
            )
            e { "denememe" }
            emit(Resource.success(movies))
        }.retryWhen {    cause, attempt ->
            i { "attempt count -> $attempt" }
            i { cause.toString() }
            (cause is Exception).also {
                if (it) delay(10_000)
            }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)
    }

}