package io.choizzy.feature.payWithNFT

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.choizzy.pay_lib.R

@Composable
internal fun PayWithNFTSuccess(modifier: Modifier = Modifier, onClick: () -> Unit) {
    PayWithNFTStateContainer(modifier = modifier,
        topTitle = "Successful!",
        title = "You exchanged NFTs for tokens ⚡",
        icon = R.drawable.ic_nft_pay_success,
        buttonName = "New exchange",
        onClick = onClick
    )
}