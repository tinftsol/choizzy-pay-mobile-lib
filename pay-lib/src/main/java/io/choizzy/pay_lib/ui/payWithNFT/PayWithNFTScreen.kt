package io.choizzy.pay_lib.ui.payWithNFT

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.choizzy.core.compose.components.ChoizzyRoundedButton
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.core.compose.theme.Space
import io.choizzy.core.compose.utils.safeClick
import io.choizzy.feature.payWithNFT.PayWithNFTError
import io.choizzy.feature.payWithNFT.PayWithNFTSuccess
import io.choizzy.feature.payWithNFT.PayWithNftSelectNftBlock
import io.choizzy.feature.payWithNFT.PayWithNftSelectNftContent
import io.choizzy.feature.payWithNFT.PayWithNftSelectTokenBlock
import io.choizzy.feature.payWithNFT.PayWithNftSelectTokenContent
import io.choizzy.pay_lib.ChoizzyPayViewModel
import io.choizzy.pay_lib.PayWithNFTState
import io.choizzy.pay_lib.PayWithNftBottomSheetType
import io.choizzy.pay_lib.R
import io.choizzy.pay_lib.ui.payWithNFT.ripple.ChoizzyRipple

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PayWithNFTScreen(modifier: Modifier = Modifier, viewModel: ChoizzyPayViewModel) {
    val state = viewModel.state.collectAsState().value

    val str = if (!state.isWalletConnected) {
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFFF7109),
                    fontWeight = FontWeight.W800
                )
            ) {
                append("Connect")
            }
            append(" your Wallet\nto Pay with NFT")
        }
    } else {
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFFF7109),
                    fontWeight = FontWeight.W800
                )
            ) {
                append("Select")
            }
            append(" Token and\nNFT to Pay With")
        }
    }


    val bottomSheetState = safeRememberModalBottomSheetState {
        viewModel.hideDialog()
    }

    LaunchedEffect(state.shouldShowBsDialog) {
        if (state.shouldShowBsDialog) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    Crossfade(state.isSuccess) {
        if (it) {
            PayWithNFTSuccess(
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                onClick = viewModel::onNewExchangeClicked
            )
        }
    }

    Crossfade(state.isError) {
        if (it) {
            PayWithNFTError(
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                onClick = viewModel::onTryAgainClicked
            )
        }
    }

    if (!state.isSuccess && !state.isError) {
        PayWithNFTScreenContent(
            modifier = modifier
                .fillMaxSize(), str = str, state = state, viewModel = viewModel
        )
    }

    if (state.shouldShowBsDialog) {
        when (state.bsType) {
            PayWithNftBottomSheetType.NFT_SELECTION -> {
                PayWithNftSelectNftContent(
                    modifier = modifier,
                    collections = state.ownedCollections,
                    onNftSelected = viewModel::onNftSelected
                )

            }

            PayWithNftBottomSheetType.TOKEN_SELECTION -> {
                PayWithNftSelectTokenContent(
                    modifier = modifier,
                    tokens = state.outputTokens,
                    onTokenSelected = viewModel::onOutputTokenSelected
                )
            }
        }
    }
}

@Composable
private fun PayWithNFTScreenContent(
    modifier: Modifier = Modifier,
    str: AnnotatedString,
    state: PayWithNFTState,
    viewModel: ChoizzyPayViewModel,
) {
    Column(
        modifier = modifier
            .padding(top = Space.spaceS),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            text = str,
            textAlign = TextAlign.Center,
            style = ChoizzyTheme.Typography.title1,
            color = ChoizzyTheme.Colors.white,
            fontWeight = FontWeight.W700
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color(0x0DFFFFFF),
                    shape = RoundedCornerShape(size = 30.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0x0DFFFFFF), shape = RoundedCornerShape(size = 30.dp))
        ) {
            Box {
                Column {
                    PayWithNFTBlock(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        state = state,
                        onNftSelectionClick = viewModel::selectNftClicked
                    )

                    ReceiveTokenBlock(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        state = state,
                        onTokenSelectionClicked = viewModel::selectTokenClicked
                    )
                }

                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_exchange_triangle),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }

            val height = if (state.errorText.isEmpty()) 0.dp else 40.dp

            Text(
                modifier = Modifier
                    .height(height)
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp)
                    .animateContentSize()
                    .safeClick(ripple = ChoizzyRipple.WithoutRipple) { viewModel.load() },
                text = state.errorText,
                style = ChoizzyTheme.Typography.caption.copy(fontSize = 14.sp),
                color = ChoizzyTheme.Colors.errorContainer,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 39.dp, vertical = 16.dp),
                color = ChoizzyTheme.Colors.divider
            )

            ChoizzyRoundedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Space.space3XL)
                    .padding(bottom = 16.dp),
                text = "Pay",
                onClick = viewModel::onSwapClicked,
                buttonColor = Color(0xFF003AFF),
                textColor = ChoizzyTheme.Colors.white,
                shape = RoundedCornerShape(30.dp),
                contentPadding = PaddingValues(16.dp),
                enabled = state.isSwapButtonAvailable,
                isLoading = state.isSwapButtonLoading,
                progressColor = ChoizzyTheme.Colors.white
            )
        }
    }
}

@Composable
private fun PayWithNFTBlock(
    modifier: Modifier = Modifier,
    state: PayWithNFTState,
    onNftSelectionClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x0DFFFFFF),
                shape = RoundedCornerShape(size = 24.dp)

            )
            .fillMaxWidth()
            .background(color = Color(0xFF1A0125), shape = RoundedCornerShape(size = 24.dp))
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp),
            text = "Pay With",
            style = ChoizzyTheme.Typography.caption.copy(fontSize = 14.sp),
            color = Color(0xFFBAB3BE),
            fontWeight = FontWeight(500),
        )

        PayWithNftSelectNftBlock(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp, bottom = 34.dp),
            state = state,
            onNftSelectionClick = onNftSelectionClick
        )
    }
}

@Composable
private fun ReceiveTokenBlock(
    modifier: Modifier = Modifier,
    state: PayWithNFTState,
    onTokenSelectionClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0x0DFFFFFF),
                shape = RoundedCornerShape(size = 24.dp)

            )
            .fillMaxWidth()
            .background(color = Color(0xFF1A0125), shape = RoundedCornerShape(size = 24.dp))
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 16.dp),
            text = "You Receive",
            style = ChoizzyTheme.Typography.caption.copy(fontSize = 14.sp),
            color = Color(0xFFBAB3BE),
            fontWeight = FontWeight(500),
        )

        PayWithNftSelectTokenBlock(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp, bottom = 34.dp),
            state = state,
            onTokenSelectionClick = onTokenSelectionClicked
        )
    }
}