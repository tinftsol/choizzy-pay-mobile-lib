package io.choizzy.pay_lib.ui.payWithNFT

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun safeRememberModalBottomSheetState(
    initialValue: ModalBottomSheetValue = ModalBottomSheetValue.Hidden,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    onStateHidden: () -> Unit
): ModalBottomSheetState {
    return rememberModalBottomSheetState(
        initialValue = initialValue,
        animationSpec = animationSpec,
        confirmStateChange = { value ->
            if (value == ModalBottomSheetValue.Hidden) {
                onStateHidden()
            }
            true
        }
    )
}