package io.choizzy.pay_lib.api

import io.choizzy.pay_lib.models.AcceptBidRequestBody
import io.choizzy.pay_lib.models.AcceptBidResponse
import io.choizzy.pay_lib.models.AcceptSwapResponse
import io.choizzy.pay_lib.models.BidResponse
import io.choizzy.pay_lib.models.OutputTokenDto
import io.choizzy.pay_lib.models.PortfolioResponse
import io.choizzy.pay_lib.models.ProceedSwapRequestBody
import io.choizzy.pay_lib.models.SwapQuoteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChoizzyApi {

    @GET("pay/bestBid")
    suspend fun getBestPrice(@Query("slug") collectionSlug: String): BidResponse

    @POST("pay/acceptBid")
    suspend fun acceptHigherPrice(@Body body: AcceptBidRequestBody): AcceptBidResponse

    @GET("pay/swapQuote")
    suspend fun getSwapQuote(
        @Query("inputMint") inputMint: String,
        @Query("outputMint") outputMint: String,
        @Query("amount") amount: String
    ): SwapQuoteResponse

    @POST("pay/swap")
    suspend fun swap(
        @Body body: ProceedSwapRequestBody
    ): AcceptSwapResponse

    @GET("pay/portfolio")
    suspend fun portfolio(
        @Query("wallet") publicKey: String
    ): PortfolioResponse

    @GET("tokens")
    suspend fun tokens(): List<OutputTokenDto>
}