package io.choizzy.pay_lib

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import io.choizzy.pay_lib.models.AcceptBidRequestBody
import io.choizzy.pay_lib.models.PortfolioCollectionResponse
import io.choizzy.pay_lib.models.ProceedSwapRequestBody
import io.choizzy.pay_lib.models.SwapQuoteResponse
import io.choizzy.pay_lib.usecase.AcceptHigherBidUseCase
import io.choizzy.pay_lib.usecase.GetCollectionBestBidUseCase
import io.choizzy.pay_lib.usecase.GetSwapQuoteUseCase
import io.choizzy.pay_lib.usecase.PortfolioUseCase
import io.choizzy.pay_lib.usecase.SwapUseCase
import io.choizzy.pay_lib.usecase.TopTokensUseCase
import kotlin.math.pow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OutputToken(
    val img: String,
    val name: String,
    val symbol: String,
    val decimals: Int,
    val mintAddress: String,
    val coinGeckoId: String?,
)

enum class PayWithNftBottomSheetType {
    NFT_SELECTION,
    TOKEN_SELECTION,
}

data class PayWithNFTState(
    val inputToken: String = "So11111111111111111111111111111111111111112",
    val outputToken: OutputToken? = null,
    val showShimmerOnNFTSelection: Boolean = true,
    val showShimmerOnOutputAmount: Boolean = false,
    val showShimmerOnTokenSelection: Boolean = true,
    val ownedCollections: List<PortfolioCollectionResponse> = emptyList(),
    val isWalletConnected: Boolean = true,
    val outputTokens: List<OutputToken> = listOf(),
    val walletAddress: String = "",
    val shortenAddress: String = "",
    val outputValue: String = "_",
    val selectedNFTImage: String = "",
    val selectedCollectionSlug: String = "",
    val selectedNFTName: String = "",
    val swapQuoteResponse: SwapQuoteResponse? = null,
    val shouldShowBsDialog: Boolean = false,
    val bsType: PayWithNftBottomSheetType = PayWithNftBottomSheetType.NFT_SELECTION,
    val isSwapButtonAvailable: Boolean = false,
    val errorText: String = "",
    val isSwapButtonLoading: Boolean = false,
    val minPriceLamports: String = "",
    val mint: String = "",
    val pool: String = "",
    val seller: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)

class ChoizzyPayViewModel constructor(
    private val signAndSendTransactions: SignAndSendTransactions,
    private val walletAddress: String,
    private val portfolioUseCase: PortfolioUseCase,
    private val getTopSwapTokensUseCase: TopTokensUseCase,
    private val getCollectionBestBidUseCase: GetCollectionBestBidUseCase,
    private val accept: AcceptHigherBidUseCase,
    private val swapQuoteUseCase: GetSwapQuoteUseCase,
    private val swapUseCase: SwapUseCase,
) : ViewModel() {

    protected val _state = MutableStateFlow(PayWithNFTState())

    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        Log.d("BIBI", "error: $t")
        _state.update {
            it.copy(
                isSwapButtonLoading = false,
                errorText = "Something went wrong, click here to try again",
                showShimmerOnOutputAmount = false
            )
        }
    }

    init {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val tokens = getTopSwapTokensUseCase()

            _state.update {
                it.copy(
                    shortenAddress = walletAddress.take(4).plus("...")
                        .plus(walletAddress.takeLast(4)),
                    outputTokens = tokens,
                    outputToken = tokens.first(),
                    walletAddress = walletAddress,
                    isWalletConnected = true
                )
            }

            load()
        }
    }

    fun onTryAgainClicked() {
        _state.update {
            it.copy(isError = false)
        }
        onSwapClicked()
    }

    fun onNewExchangeClicked() {
        _state.update {
            it.copy(isSuccess = false)
        }
        load()
    }

    fun load() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val walletAddress = _state.value.walletAddress
            val tokens = getTopSwapTokensUseCase()

            println("top tokens: $tokens")
            println("state: ${_state.value}")
            val ownedCollections = portfolioUseCase(walletAddress).collections
            Log.d("BIBI", "ownedCollections: $ownedCollections")

            _state.update {
                it.copy(
                    ownedCollections = ownedCollections,
                    outputToken = tokens.firstOrNull(),
                    outputTokens = tokens,
                    showShimmerOnNFTSelection = false,
                    showShimmerOnOutputAmount = false,
                    showShimmerOnTokenSelection = false,
                    errorText = ""
                )
            }
        }
    }

    fun onNftSelected(
        slug: String,
        name: String,
        imageUrl: String,
        mint: String,
    ) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val outputToken = requireNotNull(_state.value.outputToken)
            _state.update {
                it.copy(
                    selectedCollectionSlug = slug,
                    selectedNFTName = name.takeIf { it.isNotEmpty() } ?: slug,
                    selectedNFTImage = imageUrl,
                    mint = mint,
                    outputValue = "_",
                    showShimmerOnOutputAmount = true,
                    swapQuoteResponse = null,
                    errorText = "",
                    shouldShowBsDialog = false
                )
            }

            changeButtonSwapStateIfNeeded()

            val bestBid = getCollectionBestBidUseCase(slug)

            if (bestBid.sellNowPriceLamports == "" && bestBid.sellNowPrice == "") {
                _state.update {
                    it.copy(
                        errorText = "You can't Pay with this NFT",
                        showShimmerOnOutputAmount = false,
                    )
                }
                return@launch
            }

            _state.update {
                it.copy(
                    minPriceLamports = bestBid.sellNowPriceLamports,
                    mint = mint,
                    pool = bestBid.address,
                    seller = _state.value.walletAddress,
                )
            }

            val swapQuote = swapQuoteUseCase(
                inputMint = state.value.inputToken,
                outputMint = outputToken.mintAddress,
                amount = bestBid.sellNowPriceLamports,
            )

            _state.update {
                it.copy(
                    swapQuoteResponse = swapQuote,
                    outputValue = (swapQuote.outAmount.toLong() / 10.0.pow(requireNotNull(state.value.outputToken).decimals)).toString(),
                    showShimmerOnOutputAmount = false
                )
            }

            changeButtonSwapStateIfNeeded()
        }
    }

    fun onOutputTokenSelected(outputToken: OutputToken) {
        _state.update {
            it.copy(
                outputToken = outputToken,
                outputValue = "_",
                showShimmerOnOutputAmount = true,
                swapQuoteResponse = null,
                errorText = "",
                shouldShowBsDialog = false
            )
        }

        changeButtonSwapStateIfNeeded()

        viewModelScope.launch(context = coroutineExceptionHandler) {
            val bestBid = getCollectionBestBidUseCase(state.value.selectedCollectionSlug)

            _state.update {
                it.copy(
                    minPriceLamports = bestBid.sellNowPriceLamports,
                    pool = bestBid.address,
                    seller = _state.value.walletAddress,
                )
            }

            if (bestBid.sellNowPriceLamports == "" && bestBid.sellNowPrice == "") {
                _state.update {
                    it.copy(
                        errorText = "You can't Pay with this NFT",
                        showShimmerOnOutputAmount = false,
                    )
                }
                return@launch
            }

            val swapQuote = swapQuoteUseCase(
                inputMint = state.value.inputToken,
                outputMint = requireNotNull(state.value.outputToken).mintAddress,
                amount = bestBid.sellNowPriceLamports,
            )

            _state.update {
                it.copy(
                    swapQuoteResponse = swapQuote,
                    outputValue = (swapQuote.outAmount.toLong() / 10.0.pow(outputToken.decimals)).toString(),
                    showShimmerOnOutputAmount = false
                )
            }


            changeButtonSwapStateIfNeeded()
        }
    }

    fun selectNftClicked() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _state.update { it.copy(shouldShowBsDialog = false) }
            delay(300)
            _state.update {
                it.copy(
                    shouldShowBsDialog = true,
                    bsType = PayWithNftBottomSheetType.NFT_SELECTION
                )
            }
        }
    }

    fun selectTokenClicked() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _state.update { it.copy(shouldShowBsDialog = false) }
            delay(300)
            _state.update {
                it.copy(
                    shouldShowBsDialog = true,
                    bsType = PayWithNftBottomSheetType.TOKEN_SELECTION
                )
            }
        }
    }

    fun hideDialog() {
        _state.update { it.copy(shouldShowBsDialog = false) }
    }

    private fun changeButtonSwapStateIfNeeded() {
        _state.update {
            it.copy(
                isSwapButtonAvailable = it.swapQuoteResponse != null
            )
        }
    }

    fun onSwapClicked() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _state.update {
                it.copy(
                    isSwapButtonLoading = true,
                )
            }

            val bidTrx = accept(
                body = AcceptBidRequestBody(
                    minPriceLamports = state.value.minPriceLamports,
                    mint = state.value.mint,
                    pool = state.value.pool,
                    seller = state.value.seller,
                )
            )

            val swapTrx = swapUseCase(
                ProceedSwapRequestBody(
                    userPublicKey = state.value.walletAddress,
                    quoteResponse = state.value.swapQuoteResponse!!
                )
            )

            val result = signAndSendTransactions.signAndSendTransactions(
                listOf(
                    bidTrx.data,
                    swapTrx.data,
                )
            )

            _state.update {
                it.copy(
                    isSwapButtonLoading = false,
                    isSuccess = result,
                    isError = !result
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            signAndSendTransactions: SignAndSendTransactions,
            walletAddress: String,
            portfolioUseCase: PortfolioUseCase,
            getTopSwapTokensUseCase: TopTokensUseCase,
            getCollectionBestBidUseCase: GetCollectionBestBidUseCase,
            accept: AcceptHigherBidUseCase,
            swapQuoteUseCase: GetSwapQuoteUseCase,
            swapUseCase: SwapUseCase,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    return ChoizzyPayViewModel(
                        signAndSendTransactions,
                        walletAddress,
                        portfolioUseCase,
                        getTopSwapTokensUseCase,
                        getCollectionBestBidUseCase,
                        accept,
                        swapQuoteUseCase,
                        swapUseCase,
                    ) as T
                }
            }
    }

}
