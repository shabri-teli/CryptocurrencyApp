package com.example.cryptocurrencyapp.Network

import com.example.cryptocurrencyapp.Model.CurrencyListDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val BASE_URL = "https://pro-api.coinmarketcap.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(NetworkClient.getUnsafeOkHttpClient())
    .baseUrl(BASE_URL)
    .build()

public val headers = mapOf(
    "X-CMC_PRO_API_KEY" to "8ceedb2b-e7bd-4843-b902-8a18feedf454",
    "Accept" to "application/json"
)

interface CurrencyApiService {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCurrencyList(
        @HeaderMap headers: Map<String, String>,
        @QueryMap params: Map<String, String>
    ): CurrencyListDataClass
}

object CurrencyApi {
    val retrofitService: CurrencyApiService by lazy { retrofit.create(CurrencyApiService::class.java) }
}
