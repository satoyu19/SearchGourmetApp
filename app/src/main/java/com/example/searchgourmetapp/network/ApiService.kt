package com.example.searchgourmetapp.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query

//API28以上はhttpsである必要がある
private const val BASE_URL = "https://webservice.recruit.co.jp/hotpepper/gourmet/v1/"

// TODO: APIキー代入してください 
private const val API_KEY = "06b438f9ea3e6be7"

//moshiインスタンス
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofitインスタンス
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface GourmetApiService {

    //緯度・経度、ジャンルからGET
    @GET("?key=${API_KEY}&order=4&format=json")
    suspend fun getNearPlace(
        @Query("lat") lat: Float,
        @Query("lng") lng: Float,
        @Query("range") range: String,
        @Query("keyword") keyword: String
    ): GourmetProperty

    object GourmetApi {
        val retrofitService: GourmetApiService by lazy {
            retrofit.create(GourmetApiService::class.java)
        }
    }
}