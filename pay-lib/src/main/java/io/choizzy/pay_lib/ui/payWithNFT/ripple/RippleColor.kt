package io.choizzy.pay_lib.ui.payWithNFT.ripple

import androidx.compose.ui.graphics.Color as ComposeColor

sealed class RippleColor {

    data class ColorRes(
        val colorRes: Int
    ) : RippleColor()

    data class Color(
        val color: ComposeColor
    ) : RippleColor()
}
