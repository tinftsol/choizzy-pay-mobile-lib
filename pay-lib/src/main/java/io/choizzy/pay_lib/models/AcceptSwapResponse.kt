package io.choizzy.pay_lib.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class AcceptSwapResponse(
    val data: IntArray
)

@JsonClass(generateAdapter = true)
data class SwapQuoteResponse(
    val inputMint: String,
    val inAmount: String,
    val outputMint: String,
    val outAmount: String,
    val otherAmountThreshold: String,
    val swapMode: String,
    val slippageBps: Long,
    val platformFee: Long? = null,
    val priceImpactPct: String,
    val routePlan: List<RoutePlanItem>,
    val contextSlot: Long? = null,
    val timeTaken: Double? = null
)

@JsonClass(generateAdapter = true)
data class RoutePlanItem(
    val swapInfo: SwapInfoItem,
    val percent: Long
)

@JsonClass(generateAdapter = true)
data class SwapInfoItem(
    val ammKey: String,
    val label: String?,
    val inputMint: String,
    val outputMint: String,
    val inAmount: String,
    val outAmount: String,
    val feeAmount: String,
    val feeMint: String
)

@JsonClass(generateAdapter = true)
data class PlatformFee(
    val amount: String?,
    val feeBps: Long?
)

@JsonClass(generateAdapter = true)
data class JupSwapTransaction(
    val swapTransaction: String
)

@JsonClass(generateAdapter = true)
data class ProceedSwapRequestBody(
    val userPublicKey: String,
    val quoteResponse: SwapQuoteResponse
)