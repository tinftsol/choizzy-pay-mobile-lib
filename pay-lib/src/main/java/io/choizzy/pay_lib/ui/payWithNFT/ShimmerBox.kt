package io.choizzy.core.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.valentinilk.shimmer.shimmer
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.theme.Space

@Composable
fun ShimmerBox(
    modifier: Modifier,
    radius: Dp = Space.spaceS,
    color: Color = ChoizzyTheme.Colors.labelTertiary
) {
    Box(
        modifier = modifier
            .shimmer()
            .clip(RoundedCornerShape(radius))
            .background(color)
    )
}