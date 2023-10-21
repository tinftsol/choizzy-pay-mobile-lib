package io.choizzy.pay_lib.usecase

import io.choizzy.pay_lib.models.AcceptSwapResponse
import io.choizzy.pay_lib.models.ProceedSwapRequestBody
import io.choizzy.pay_lib.repository.ChoizzyRepository
import javax.inject.Inject

class SwapUseCase @Inject constructor(private val repository: ChoizzyRepository) {


    suspend operator fun invoke(
        proceedSwapRequestBody: ProceedSwapRequestBody
    ): AcceptSwapResponse {
        return repository.swap(proceedSwapRequestBody)
    }

}
