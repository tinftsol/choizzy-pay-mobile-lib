package io.choizzy.pay_lib.ui.payWithNFT.ripple

import androidx.compose.ui.unit.Dp
import io.choizzy.pay_lib.R

sealed class ChoizzyRipple {

    data class Ripple(
        val radius: Dp = Dp.Unspecified,
        val isBounded: Boolean = true,
        val color: RippleColor = RippleColor.ColorRes(colorRes = R.color.grey)
    ) : ChoizzyRipple()

    object WithoutRipple : ChoizzyRipple()
}
