package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.models.SwapQuoteResponse
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class GetSwapQuoteUseCase @Inject constructor(val repository: ChoizzyRepository) {

    suspend operator fun invoke(
        inputMint: String,
        outputMint: String,
        amount: String
    ): SwapQuoteResponse {
        return repository.getSwapQuote(inputMint, outputMint, amount)
    }

}
