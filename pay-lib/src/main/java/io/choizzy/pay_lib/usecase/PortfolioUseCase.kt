package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.models.PortfolioResponse
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class PortfolioUseCase @Inject constructor(val repository: ChoizzyRepository) {
    suspend operator fun invoke(walletAddress: String): PortfolioResponse {
        return repository.portfolio(walletAddress)
    }
}