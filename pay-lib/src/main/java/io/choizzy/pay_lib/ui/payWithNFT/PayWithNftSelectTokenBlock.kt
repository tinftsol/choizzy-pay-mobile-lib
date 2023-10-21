package io.choizzy.feature.payWithNFT

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import io.choizzy.core.compose.components.ShimmerBox
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.utils.safeClick
import io.choizzy.pay_lib.PayWithNFTState
import io.choizzy.pay_lib.R
import io.choizzy.pay_lib.ui.payWithNFT.ChoizzyImage

@Composable
fun PayWithNftSelectTokenBlock(
    modifier: Modifier = Modifier,
    state: PayWithNFTState,
    onTokenSelectionClick: () -> Unit,
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
                    onTokenSelectionClick()
                }
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                .conditional(state.showShimmerOnNFTSelection) {
                    shimmer()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.showShimmerOnTokenSelection) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Loading..",
                    style = ChoizzyTheme.Typography.body1,
                    color = ChoizzyTheme.Colors.white,
                    fontWeight = FontWeight(700),
                )
            } else {
                ChoizzyImage(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    data = state.outputToken?.img,
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    modifier = Modifier.padding(4.dp),
                    text = state.outputToken?.symbol.orEmpty(),
                    style = ChoizzyTheme.Typography.body1,
                    color = ChoizzyTheme.Colors.white,
                    fontWeight = FontWeight(700),
                )
            }
            Icon(
                modifier = Modifier.size(24.dp),
                painter = rememberImagePainter(R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = ChoizzyTheme.Colors.white
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (state.showShimmerOnOutputAmount) {
            ShimmerBox(modifier = Modifier.size(70.dp, 30.dp), 50.dp)
        } else {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = state.outputValue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ChoizzyTheme.Typography.body1,
                color = ChoizzyTheme.Colors.white,
                fontWeight = FontWeight(700),
            )
        }

    }
}