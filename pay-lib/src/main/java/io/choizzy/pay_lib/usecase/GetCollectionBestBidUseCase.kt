package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.models.BidResponse
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class GetCollectionBestBidUseCase @Inject constructor(val repository: ChoizzyRepository) {

    suspend operator fun invoke(
        slug: String
    ): BidResponse {
        return repository.getBestBid(slug)
    }
}