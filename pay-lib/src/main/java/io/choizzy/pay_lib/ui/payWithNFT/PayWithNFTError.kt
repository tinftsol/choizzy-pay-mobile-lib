package io.choizzy.feature.payWithNFT

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.choizzy.pay_lib.R

@Composable
internal fun PayWithNFTError(modifier: Modifier = Modifier, onClick: () -> Unit) {
    PayWithNFTStateContainer(modifier = modifier,
        topTitle = "Oops...",
        title = "Something went\nwrong",
        icon = R.drawable.ic_nft_pay_error,
        buttonName = "Try again",
        onClick = onClick
    )
}