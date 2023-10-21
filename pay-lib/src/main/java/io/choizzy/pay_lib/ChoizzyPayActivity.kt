package io.choizzy.pay_lib

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.android.AndroidInjection
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.theme.ChoizzyPayTheme
import io.choizzy.pay_lib.repository.ChoizzyRepository
import io.choizzy.pay_lib.ui.payWithNFT.PayWithNFTScreen
import io.choizzy.pay_lib.usecase.AcceptHigherBidUseCase
import io.choizzy.pay_lib.usecase.GetCollectionBestBidUseCase
import io.choizzy.pay_lib.usecase.GetSwapQuoteUseCase
import io.choizzy.pay_lib.usecase.PortfolioUseCase
import io.choizzy.pay_lib.usecase.SwapUseCase
import io.choizzy.pay_lib.usecase.TopTokensUseCase
import javax.inject.Inject


interface SignAndSendTransactions {
    fun signAndSendTransactions(transactions: List<IntArray>): Boolean
}

class ChoizzyPayActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ChoizzyRepository

    @Inject
    lateinit var portfolioUseCase: PortfolioUseCase

    @Inject
    lateinit var getTopSwapTokensUseCase: TopTokensUseCase

    @Inject
    lateinit var getCollectionBestBidUseCase: GetCollectionBestBidUseCase

    @Inject
    lateinit var accept: AcceptHigherBidUseCase

    @Inject
    lateinit var swapQuoteUseCase: GetSwapQuoteUseCase

    @Inject
    lateinit var swapUseCase: SwapUseCase

    private val walletAddress by lazy {
        requireNotNull(intent.getStringExtra(WALLET_ADDRESS))
    }

    private val viewModel: ChoizzyPayViewModel by viewModels {
        ChoizzyPayViewModel.provideFactory(
            signAndSendTransactions,
            walletAddress,
            portfolioUseCase,
            getTopSwapTokensUseCase,
            getCollectionBestBidUseCase,
            accept,
            swapQuoteUseCase,
            swapUseCase,
            this
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            ChoizzyPayTheme {
                Box(modifier = Modifier.fillMaxSize().background(ChoizzyTheme.Colors.black)) {
                    PayWithNFTScreen(
                        modifier = Modifier.fillMaxSize(),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }


    companion object {

        private lateinit var signAndSendTransactions: SignAndSendTransactions

        private const val WALLET_ADDRESS = "wallet_address"

        fun intent(
            context: Context,
            walletAddress: String,
            signAndSendTransactions: SignAndSendTransactions,
        ): Intent {
            ChoizzyPayActivity.signAndSendTransactions = signAndSendTransactions
            return Intent(context, ChoizzyPayActivity::class.java)
                .putExtra(WALLET_ADDRESS, walletAddress)
        }
    }
}

