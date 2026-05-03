package com.example.fake_store.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyRateDTO(
    val Cur_Abbreviation: String,
    val Cur_ID: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double,
    val Cur_Scale: Int,
    val Date: String
) {
    companion object {
        const val USD_TO_BYN_ID = 431
    }
}