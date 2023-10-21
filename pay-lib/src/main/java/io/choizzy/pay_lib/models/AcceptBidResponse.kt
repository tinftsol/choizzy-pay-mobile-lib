package io.choizzy.pay_lib.models

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class AcceptBidResponse(
    val data: IntArray
)

@JsonClass(generateAdapter = true)
data class AcceptBidRequestBody(
    val minPriceLamports: String,
    val mint: String,
    val pool: String,
    val seller: String
)