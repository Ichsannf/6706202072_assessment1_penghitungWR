package org.d3if6706202072.assessment1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if6706202072.assessment1.model.TentangAplikasi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "Ichsannf/WR-API/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface AssesmentApiService {
        @GET("hitungWR.json")
        suspend fun getWR(): TentangAplikasi
}

object WRApi {
    val service: AssesmentApiService by lazy {
        retrofit.create(AssesmentApiService::class.java)
    }
    fun getImageUrl():String{
        return BASE_URL + "241241.png"
    }

}

enum class ApiStatus {LOADING,SUCCESS,FAILED}