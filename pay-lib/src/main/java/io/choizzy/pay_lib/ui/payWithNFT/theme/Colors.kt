package io.choizzy.compose.components.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object Colors {

    object Light {
        val white = Color(0xFFFFFFFF)
        val black = Color(0xFF000000)
        val label = Color(0xFF16161A)
        val background = Color(0xFF0E0B11)
        val labelSecondary = Color(0xFF737376)
        val labelTertiary = Color(0xFFA2A2A3)
        val labelInverse = Color(0xFFFFFFFF)
        val divider = Color(0x1416161A)
        val fill = Color(0x0A16161A)
        val base = Color(0xFFFFFFFF)
        val baseSecondary = Color(0xFFF2F2F7)
        val baseInverse = Color(0xFF16161A)
        val nav = Color(0x66D1D1D6)
        val fog = Color(0x6616161A)
        val fullscreen = Color(0xCC16161A)
        val action = Color(0xFF0066FF)
        val actionSecondary = Color(0x1A3470F6)
        val brand = Color(0xFFFEDA03)
        val success = Color(0xFFA9FD22)
        val error = Color(0xFFFFC8C8)
        val successContainer = Color(0xFF2CB462)
        val errorContainer = Color(0xFFFC5230)
        val tapBarColor = Color(0xFFFBFBFB)
        val tapBarIconsColor = Color(0xFFA2A2A3)
    }

    object Dark {
        val white = Color(0xFFFFFFFF)
        val black = Color(0xFF000000)
        val label = Color(0xFFFFFFFF)
        val background = Color(0xFF0E0B11)
        val labelSecondary = Color(0x99FFFFFF)
        val labelTertiary = Color(0x66FFFFFF)
        val labelInverse = Color(0xFF16161A)
        val divider = Color(0x14FFFFFF)
        val divider2 = Color(0xFF1B1C1E)
        val fill = Color(0x0AFFFFFF)
        val base = Color(0xFF16161A)
        val baseSecondary = Color(0xFF292930)
        val baseInverse = Color(0xFFFFFFFF)
        val nav = Color(0x66575767)
        val fog = Color(0x66151619)
        val fullscreen = Color(0xCC151619)
        val action = Color(0xFF3D87F5)
        val actionSecondary = Color(0x1A3D87F5)
        val brand = Color(0xFFFEDA03)
        val success = Color(0xFFA9FD22)
        val error = Color(0xFFFFC8C8)
        val successContainer = Color(0xFF18C35C)
        val errorContainer = Color(0xFFFF6243)
        val tabBarColor = Color(0xFF1E1E24)
        val tabBarIconsColor = Color(0xFFFFFFFF)
    }
}

@Immutable
data class AppColors(
    val white: Color,
    val black: Color,
    val label: Color,
    val background: Color,
    val labelSecondary: Color,
    val labelTertiary: Color,
    val labelInverse: Color,
    val divider: Color,
    val divider2: Color,
    val fill: Color,
    val base: Color,
    val baseSecondary: Color,
    val baseInverse: Color,
    val nav: Color,
    val fog: Color,
    val fullscreen: Color,
    val action: Color,
    val actionSecondary: Color,
    val brand: Color,
    val success: Color,
    val error: Color,
    val successContainer: Color,
    val errorContainer: Color,
    val tabBarColor: Color,
    val tabBarIconColor: Color
) {

    companion object {
        fun unspecified() = AppColors(
            white = Color.Unspecified,
            black = Color.Unspecified,
            label = Color.Unspecified,
            labelSecondary = Color.Unspecified,
            labelTertiary = Color.Unspecified,
            labelInverse = Color.Unspecified,
            divider = Color.Unspecified,
            fill = Color.Unspecified,
            base = Color.Unspecified,
            baseSecondary = Color.Unspecified,
            baseInverse = Color.Unspecified,
            nav = Color.Unspecified,
            fog = Color.Unspecified,
            fullscreen = Color.Unspecified,
            action = Color.Unspecified,
            background = Color.Unspecified,
            actionSecondary = Color.Unspecified,
            brand = Color.Unspecified,
            success = Color.Unspecified,
            error = Color.Unspecified,
            divider2 = Color.Unspecified,
            successContainer = Color.Unspecified,
            errorContainer = Color.Unspecified,
            tabBarColor = Color.Unspecified,
            tabBarIconColor = Color.Unspecified
        )
    }
}

val LocalColors = staticCompositionLocalOf {
    AppColors.unspecified()
}

fun lightColors() = AppColors(
    white = Colors.Light.white,
    black = Colors.Light.black,
    label = Colors.Light.label,
    labelSecondary = Colors.Light.labelSecondary,
    labelTertiary = Colors.Light.labelTertiary,
    labelInverse = Colors.Light.labelInverse,
    divider = Colors.Light.divider,
    divider2 = Colors.Light.divider,
    fill = Colors.Light.fill,
    background = Colors.Light.background,
    base = Colors.Light.base,
    baseSecondary = Colors.Light.baseSecondary,
    baseInverse = Colors.Light.baseInverse,
    nav = Colors.Light.nav,
    fog = Colors.Light.fog,
    fullscreen = Colors.Light.fullscreen,
    action = Colors.Light.action,
    actionSecondary = Colors.Light.actionSecondary,
    brand = Colors.Light.brand,
    success = Colors.Light.success,
    error = Colors.Light.error,
    successContainer = Colors.Light.successContainer,
    errorContainer = Colors.Light.errorContainer,
    tabBarColor = Colors.Light.tapBarColor,
    tabBarIconColor = Colors.Light.tapBarIconsColor
)

fun darkColors() = AppColors(
    white = Colors.Dark.white,
    black = Colors.Dark.black,
    label = Colors.Dark.label,
    labelSecondary = Colors.Dark.labelSecondary,
    labelTertiary = Colors.Dark.labelTertiary,
    labelInverse = Colors.Dark.labelInverse,
    divider = Colors.Dark.divider,
    divider2 = Colors.Dark.divider2,
    fill = Colors.Dark.fill,
    base = Colors.Dark.base,
    background = Colors.Light.background,
    baseSecondary = Colors.Dark.baseSecondary,
    baseInverse = Colors.Dark.baseInverse,
    nav = Colors.Dark.nav,
    fog = Colors.Dark.fog,
    fullscreen = Colors.Dark.fullscreen,
    action = Colors.Dark.action,
    actionSecondary = Colors.Dark.actionSecondary,
    brand = Colors.Dark.brand,
    success = Colors.Dark.success,
    error = Colors.Dark.error,
    successContainer = Colors.Dark.successContainer,
    errorContainer = Colors.Dark.errorContainer,
    tabBarColor = Colors.Dark.tabBarColor,
    tabBarIconColor = Colors.Dark.tabBarIconsColor
)
