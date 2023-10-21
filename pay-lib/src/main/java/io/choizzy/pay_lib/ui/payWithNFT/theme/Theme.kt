package io.choizzy.core.compose.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import io.choizzy.compose.components.theme.AppColors
import io.choizzy.compose.components.theme.AvenirFamily
import io.choizzy.compose.components.theme.ChoizzyTypography
import io.choizzy.compose.components.theme.LocalColors
import io.choizzy.compose.components.theme.LocalTypography
import io.choizzy.compose.components.theme.choizzyTypography
import io.choizzy.compose.components.theme.darkColors
import io.choizzy.compose.components.theme.lightColors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChoizzyPayTheme(
    content: @Composable () -> Unit,
) {
    val isDarkTheme: Boolean = true // isSystemInDarkTheme()

    val colors = remember {
        if (isDarkTheme) {
            darkColors()
        } else {
            lightColors()
        }
    }

    val typographyAvenir = remember {
        choizzyTypography(AvenirFamily)
    }
    CompositionLocalProvider(
        LocalTypography provides typographyAvenir,
        LocalColors provides colors,
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object ChoizzyTheme {
    val Typography: ChoizzyTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val Colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}
