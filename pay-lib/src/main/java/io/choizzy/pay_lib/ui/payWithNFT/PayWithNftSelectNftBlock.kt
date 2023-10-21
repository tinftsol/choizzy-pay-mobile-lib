package io.choizzy.feature.payWithNFT

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.valentinilk.shimmer.shimmer
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.utils.safeClick
import io.choizzy.pay_lib.PayWithNFTState
import io.choizzy.pay_lib.R
import io.choizzy.pay_lib.ui.payWithNFT.ChoizzyImage

@Composable
fun PayWithNftSelectNftBlock(
    modifier: Modifier = Modifier, state: PayWithNFTState,
    onNftSelectionClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0x0DFFFFFF),
                    shape = RoundedCornerShape(size = 50.dp)
                )
                .clip(RoundedCornerShape(size = 50.dp))
                .background(
                    color = Color(0x0DFFFFFF),
                )
                .safeClick {
                    onNftSelectionClick()
                }
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                .conditional(state.showShimmerOnNFTSelection) {
                    shimmer()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.showShimmerOnNFTSelection) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Loading..",
                    style = ChoizzyTheme.Typography.body1,
                    color = ChoizzyTheme.Colors.white,
                    fontWeight = FontWeight(700),
                )
            } else {
                if (state.selectedNFTName.isNotEmpty()) {
                    ChoizzyImage(
                        modifier = Modifier.size(32.dp)
                            .clip(CircleShape),
                        data = state.selectedNFTImage,
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        modifier = Modifier.padding(4.dp).widthIn(max = 350.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = state.selectedNFTName,
                        style = ChoizzyTheme.Typography.body1,
                        color = ChoizzyTheme.Colors.white,
                        fontWeight = FontWeight(700),
                    )

                } else {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Select NFT",
                        style = ChoizzyTheme.Typography.body1,
                        color = ChoizzyTheme.Colors.white,
                        fontWeight = FontWeight(700),
                    )
                }
            }

            Icon(
                modifier = Modifier.size(24.dp),
                painter = rememberImagePainter(R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = ChoizzyTheme.Colors.white
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = if (state.selectedNFTName.isNotEmpty()) "1" else "_",
            style = ChoizzyTheme.Typography.body1,
            color = ChoizzyTheme.Colors.white,
            fontWeight = FontWeight(700),
        )
    }

}

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}