package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.OutputToken
import io.choizzy.pay_lib.models.AcceptBidRequestBody
import io.choizzy.pay_lib.models.AcceptBidResponse
import io.choizzy.pay_lib.models.PortfolioResponse
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class TopTokensUseCase @Inject constructor(val repository: ChoizzyRepository) {
    suspend operator fun invoke(): List<OutputToken> {
        return repository.tokens()
            .map {
                OutputToken(
                    img = it.logoURI.orEmpty(),
                    name = it.name,
                    symbol = it.symbol,
                    decimals = it.decimals,
                    mintAddress = it.address,
                    coinGeckoId = it.extensions?.coingeckoId
                )
            }
    }
}