package io.choizzy.pay_lib.models

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class BidResponse(
    val address: String,
    val ownerAddress: String,
    val sellNowPrice: String,
    val sellNowPriceLamports: String
)