package io.choizzy.feature.payWithNFT

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.theme.Space
import io.choizzy.core.compose.utils.safeClick
import io.choizzy.pay_lib.models.PortfolioCollectionResponse
import io.choizzy.pay_lib.ui.payWithNFT.ChoizzyAvatar

@Composable
fun PayWithNftSelectNftContent(
    modifier: Modifier,
    collections: List<PortfolioCollectionResponse>,
    onNftSelected: (String, String, String, String) -> Unit,
) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier
            .background(
                ChoizzyTheme.Colors.background
            ),
        columns = GridCells.Fixed(2),
        state = lazyGridState
    ) {
        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier.statusBarsPadding()
            )
        }

        item(span = { GridItemSpan(2) }) {
            val str = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFF7109),
                        fontWeight = FontWeight.W800
                    )
                ) {
                    append("Select")
                }
                append(" NFT\nto Pay With")
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = str,
                textAlign = TextAlign.Center,
                style = ChoizzyTheme.Typography.title1,
                color = ChoizzyTheme.Colors.white,
                fontWeight = FontWeight.W700
            )
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
        }

        collections.flatMap {
            it.items.map { item -> it.slug to item }
        }.forEachIndexed { index, item ->
            item(key = item.second.id) {
                Box(
                    modifier = Modifier.padding(
                        top = Space.space2XS,
                        bottom = Space.space2XS,
                        start = if (index % 2 == 0) Space.spaceM else Space.space2XS,
                        end = if (index % 2 == 1) Space.spaceM else Space.space2XS
                    )
                ) {
                    ChoizzyAvatar(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .aspectRatio(1f)
                            .safeClick {
                                onNftSelected(
                                    item.first,
                                    item.second.name,
                                    item.second.imageUrl,
                                    item.second.id
                                )
                            },
                        data = item.second.imageUrl,
                        id = item.second.id
                    )

                }
            }
        }

        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
    }
}