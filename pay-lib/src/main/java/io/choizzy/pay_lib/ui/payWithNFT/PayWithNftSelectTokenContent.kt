package io.choizzy.feature.payWithNFT

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.utils.safeClick
import io.choizzy.pay_lib.OutputToken
import io.choizzy.pay_lib.R
import io.choizzy.pay_lib.ui.payWithNFT.ChoizzyImage

@Composable
fun PayWithNftSelectTokenContent(
    modifier: Modifier,
    tokens: List<OutputToken>,
    onTokenSelected: (OutputToken) -> Unit,
) {
    LazyColumn(
        modifier
            .background(
                ChoizzyTheme.Colors.background
            )
    ) {
        item(key = "header") {
            val str = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFFF7109),
                        fontWeight = FontWeight.W800
                    )
                ) {
                    append("You")
                }
                append(" Receive")
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
                text = str,
                textAlign = TextAlign.Center,
                style = ChoizzyTheme.Typography.title1,
                color = ChoizzyTheme.Colors.white,
                fontWeight = FontWeight.W700
            )
        }
        items(tokens, key = { it.mintAddress }) { token ->
            TokenItem(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                token = token,
                onClick = { onTokenSelected(token) }
            )
        }
    }
}

@Composable
private fun TokenItem(modifier: Modifier, token: OutputToken, onClick: () -> Unit) {
    Row(
        modifier = modifier.safeClick(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChoizzyImage(
            modifier = Modifier.size(32.dp).clip(CircleShape),
            data = token.img
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = token.name,
                    style = ChoizzyTheme.Typography.body1,
                    color = ChoizzyTheme.Colors.white,
                    fontWeight = FontWeight.W600,
                    fontSize = 13.sp,
                    lineHeight = 24.sp
                )
                Spacer(Modifier.width(8.dp))
                TokenAddressCaption(modifier = Modifier, address = token.mintAddress)
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = token.symbol,
                style = ChoizzyTheme.Typography.caption,
                fontSize = 11.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.W600,
                color = ChoizzyTheme.Colors.white.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
private fun TokenAddressCaption(modifier: Modifier, address: String) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(ChoizzyTheme.Colors.white.copy(0.05f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .safeClick {
                openLink(context, "https://solscan.io/token/$address")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = address.take(4) + "..." + address.takeLast(4),
            style = ChoizzyTheme.Typography.caption,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.W500,
            color = ChoizzyTheme.Colors.white.copy(alpha = 0.5f)
        )
        Spacer(Modifier.width(8.dp))
        Image(
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_open_link),
            contentDescription = null
        )
    }
}

fun openLink(context: Context, link: String) {
    try {

        val uri: Uri =
            Uri.parse(link)

        val intent = Intent(Intent.ACTION_VIEW, uri)
        ContextCompat.startActivity(context, intent, null)
    } catch (_: Throwable) {

    }
}