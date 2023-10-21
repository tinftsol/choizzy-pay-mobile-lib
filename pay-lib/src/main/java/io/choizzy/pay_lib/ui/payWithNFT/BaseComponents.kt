package io.choizzy.pay_lib.ui.payWithNFT

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.choizzy.core.compose.theme.ChoizzyTheme

@Composable
fun ProgressItem(modifier: Modifier, color: Color = ChoizzyTheme.Colors.label) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = color,
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun ProgressOverlay(modifier: Modifier = Modifier.fillMaxSize()) {
    Box(
        modifier = modifier.background(Color(0x40000000)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier,
            color = ChoizzyTheme.Colors.label,
            strokeWidth = 2.dp
        )
    }
}

@Composable
fun CloseButton(
    modifier: Modifier,
    icon: Int,
    tint: Color = ChoizzyTheme.Colors.label,
    onClick: () -> Unit
) {
    Icon(
        painter = painterResource(id = icon),
        tint = tint,
        contentDescription = null
    )
}

@Composable
fun Divider(modifier: Modifier = Modifier.fillMaxWidth()) {
    Box(
        modifier = modifier
            .height(1.dp)
            .background(ChoizzyTheme.Colors.divider, RoundedCornerShape(2.dp))
    )
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = ChoizzyTheme.Colors.divider,
    height: Dp,
    thickness: Dp
) {
    Box(
        modifier = modifier
            .height(height)
            .width(thickness)
            .background(color)
    ) {
        androidx.compose.material.Divider(
            color = color,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

const val LINK_ANNOTATION_TAG = "link"