package io.choizzy.pay_lib.models

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class PortfolioResponse(
    val totalFloor: String,
    val collections: List<PortfolioCollectionResponse>
)

@JsonClass(generateAdapter = true)
data class PortfolioCollectionResponse(
    val slug: String,
    val name: String,
    val imageUrl: String,
    val mintCount: Int,
    val floorPrice: String,
    val floorPriceTotal: String,
    val floor24h: String,
    val items: List<PortfolioCollectionItemResponse>
)

@JsonClass(generateAdapter = true)
data class PortfolioCollectionItemResponse(
    val id: String,
    val name: String,
    val imageUrl: String
)