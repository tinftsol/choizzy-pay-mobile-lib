package io.choizzy.pay_lib.repository

import io.choizzy.pay_lib.api.ChoizzyApi
import io.choizzy.pay_lib.models.AcceptBidRequestBody
import io.choizzy.pay_lib.models.AcceptBidResponse
import io.choizzy.pay_lib.models.AcceptSwapResponse
import io.choizzy.pay_lib.models.BidResponse
import io.choizzy.pay_lib.models.OutputTokenDto
import io.choizzy.pay_lib.models.PortfolioResponse
import io.choizzy.pay_lib.models.ProceedSwapRequestBody
import io.choizzy.pay_lib.models.SwapQuoteResponse
import javax.inject.Inject

class ChoizzyRepository @Inject constructor(
    private val api: ChoizzyApi
) {
    suspend fun swap(
        request: ProceedSwapRequestBody
    ): AcceptSwapResponse {
        return api.swap(request)
    }

    suspend fun getBestBid(collectionSlug: String): BidResponse {
        return api.getBestPrice(collectionSlug)
    }

    suspend fun getSwapQuote(
        inputMint: String,
        outputMint: String,
        amount: String
    ): SwapQuoteResponse {
        return api.getSwapQuote(inputMint, outputMint, amount)
    }

    suspend fun acceptHigherBid(body: AcceptBidRequestBody): AcceptBidResponse {
        return api.acceptHigherPrice(body)
    }

    suspend fun portfolio(walletAddress: String): PortfolioResponse {
        return api.portfolio(walletAddress)
    }

    suspend fun tokens(): List<OutputTokenDto> {
        return api.tokens()
    }

}