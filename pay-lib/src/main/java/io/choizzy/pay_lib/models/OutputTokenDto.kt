package io.choizzy.pay_lib.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class OutputTokenDto(
    @Json val address: String,
    @Json val name: String,
    @Json val symbol: String,
    @Json val logoURI: String? = null,
    @Json val decimals: Int,
    @Json val chainId: Int,
    @Json val extensions: ExtensionsDto? = null
)

@JsonClass(generateAdapter = true)
data class ExtensionsDto(
    val coingeckoId: String? = null
)
