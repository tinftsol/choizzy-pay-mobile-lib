package io.choizzy.core.compose.utils

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.colorResource
import io.choizzy.pay_lib.ui.payWithNFT.ripple.ChoizzyRipple
import io.choizzy.pay_lib.ui.payWithNFT.ripple.RippleColor
import kotlinx.coroutines.launch

private const val DEBOUNCE_TIME_MS = 300L

interface ChoizzyAnimation {

    suspend fun animate()
}

fun Modifier.safeClick(
    interactionSource: MutableInteractionSource? = null,
    ripple: ChoizzyRipple = ChoizzyRipple.Ripple(),
    animation: ChoizzyAnimation? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "safeClick"
        properties["onClick"] = onClick
    }
) {
    var lastClickTime by remember { mutableStateOf(0L) }

    val click = rememberUpdatedState(onClick)
    click(
        interactionSource = interactionSource,
        ripple = ripple,
        animation = animation,
        onClick = {
            if (SystemClock.elapsedRealtime() - lastClickTime >= DEBOUNCE_TIME_MS) {
                click.value.invoke()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    )
}

fun Modifier.click(
    interactionSource: MutableInteractionSource? = null,
    ripple: ChoizzyRipple = ChoizzyRipple.Ripple(),
    animation: ChoizzyAnimation? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "click"
        properties["onClick"] = onClick
    }
) {
    val click = rememberUpdatedState(onClick)
    val scope = if (animation != null) rememberCoroutineScope() else null
    clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = if (ripple is ChoizzyRipple.Ripple) {
            rememberRipple(
                bounded = ripple.isBounded,
                color = when (val color = ripple.color) {
                    is RippleColor.ColorRes -> colorResource(id = color.colorRes)
                    is RippleColor.Color -> color.color
                },
                radius = ripple.radius
            )
        } else {
            null
        },
        onClick = {
            scope?.launch {
                animation?.animate()
                click.value()
            } ?: click.value()
        }
    ).apply {
        if (animation != null) {
        }
    }
}
