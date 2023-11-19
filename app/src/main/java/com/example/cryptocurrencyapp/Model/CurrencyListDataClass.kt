package com.example.cryptocurrencyapp.Model

import com.squareup.moshi.Json
import java.io.Serializable

data class CurrencyListDataClass(
    val data: List<DataItem>,
    val status: Status,
): Serializable

data class DataItem (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "symbol") val symbol: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "cmc_rank") val cmc_rank: Int? = 0,
    @Json(name = "num_market_pairs") val num_market_pairs: Int,
    @Json(name = "circulating_supply") val circulating_supply: Double,
    @Json(name = "total_supply") val total_supply: Double,
    @Json(name = "max_supply") val max_supply: String ?= null,
    @Json(name = "infinite_supply") val infinite_supply: Boolean,
    @Json(name = "last_updated") val last_updated: String,
    @Json(name = "date_added") val date_added: String,
    @Json(name = "tags") val tags: List<String>,
    /*val platform: JsonElement? = null,
    val self_reported_circulating_supply: JsonElement? = null,
    val self_reported_market_cap: JsonElement? = null,*/

    @Json(name = "quote") val quote: Map<String, Quote>
)

data class Quote (
    @Json(name = "price") val price: Double,
    @Json(name = "volume_24h") val volume_24h: Double,
    @Json(name = "volume_change_24h") val volume_change_24h: Double,
    @Json(name = "percent_change_1h") val percent_change_1h: Double,
    @Json(name = "percent_change_24h") val percent_change_24h: Double,
    @Json(name = "percent_change_7d") val percent_change_7d: Double,
    @Json(name = "market_cap") val market_cap: Double,
    @Json(name = "market_cap_dominance") val market_cap_dominance: Double,
    @Json(name = "fully_diluted_market_cap") val fully_diluted_market_cap: Double,
    @Json(name = "last_updated") val last_updated: String
):Serializable

data class Status (
    @Json(name = "timestamp") val timestamp: String,
    @Json(name = "error_code") val error_code: Int,
    @Json(name = "error_message") val error_message: String ?= null,
    @Json(name = "elapsed") val elapsed: Int,
    @Json(name = "credit_count") val credit_count: Int
):Serializable
