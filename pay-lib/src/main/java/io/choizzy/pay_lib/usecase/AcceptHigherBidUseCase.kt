package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.models.AcceptBidRequestBody
import io.choizzy.pay_lib.models.AcceptBidResponse
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class AcceptHigherBidUseCase @Inject constructor(val repository: ChoizzyRepository) {
    suspend operator fun invoke(
        body: AcceptBidRequestBody
    ): AcceptBidResponse {
        return repository.acceptHigherBid(body)
    }
}