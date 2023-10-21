package io.choizzy.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.utils.click
import io.choizzy.pay_lib.ui.payWithNFT.ProgressItem

@Composable
fun ChoizzyRoundedButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    textColor: Color = ChoizzyTheme.Colors.labelInverse,
    buttonColor: Color = ChoizzyTheme.Colors.baseInverse,
    iconColor: Color? = textColor,
    iconSize: Dp = 16.dp,
    shape: Shape = RoundedCornerShape(14.dp),
    progressColor: Color = Color.Black,
    text: String = "",
    icon: Int = 0,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .clip(shape)
            .background(if (enabled) buttonColor else ChoizzyTheme.Colors.labelTertiary)
            .click { if (enabled) onClick() }
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (icon != 0) {
            Icon(
                modifier = Modifier.size(iconSize),
                tint = iconColor ?: textColor,
                painter = painterResource(id = icon),
                contentDescription = null
            )
            if (text.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        if (isLoading) {
            ProgressItem(modifier = Modifier.fillMaxWidth(), color = progressColor)
        } else {
            if (text.isNotEmpty()) {
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    text = text,
                    style = ChoizzyTheme.Typography.body2,
                    color = textColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}