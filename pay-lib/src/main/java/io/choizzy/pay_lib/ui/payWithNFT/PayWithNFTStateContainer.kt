package io.choizzy.feature.payWithNFT

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.choizzy.core.compose.components.ChoizzyRoundedButton
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.theme.Space

@Composable
fun PayWithNFTStateContainer(
    modifier: Modifier = Modifier,
    topTitle: String,
    title: String,
    icon: Int,
    buttonName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(120.dp))
        Text(
            modifier = Modifier,
            text = topTitle,
            textAlign = TextAlign.Center,
            style = ChoizzyTheme.Typography.title1,
            lineHeight = 42.sp,
            color = Color(0xFFFF7109),
            fontWeight = FontWeight.W700
        )
        Text(
            modifier = Modifier,
            text = title,
            textAlign = TextAlign.Center,
            style = ChoizzyTheme.Typography.title1,
            lineHeight = 42.sp,
            color = ChoizzyTheme.Colors.white,
            fontWeight = FontWeight.W700
        )
        Spacer(Modifier.height(40.dp))
        Image(painterResource(icon), contentDescription = null)
        Spacer(modifier = Modifier.height(80.dp))
        ChoizzyRoundedButton(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = Space.space3XL),
            text = buttonName,
            onClick = onClick,
            buttonColor = Color(0xFF003AFF),
            textColor = ChoizzyTheme.Colors.white,
            shape = RoundedCornerShape(30.dp),
            contentPadding = PaddingValues(16.dp),
            enabled = true,
            progressColor = ChoizzyTheme.Colors.white
        )
    }
}