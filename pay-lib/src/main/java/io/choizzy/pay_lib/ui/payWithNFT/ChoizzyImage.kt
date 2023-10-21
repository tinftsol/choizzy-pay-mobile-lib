package io.choizzy.pay_lib.ui.payWithNFT

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.ImagePainter.State.Empty
import coil.compose.ImagePainter.State.Loading
import coil.compose.ImagePainter.State.Success
import coil.compose.rememberImagePainter
import coil.transform.Transformation
import com.valentinilk.shimmer.shimmer
import io.choizzy.core.compose.theme.ChoizzyTheme
import io.choizzy.pay_lib.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ChoizzyImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    data: Any?,
    transformation: Transformation? = null,
    fallbackUrl: String? = null,
    placeholder: ChoizzyImagePlaceholder = ChoizzyImagePlaceholder.DefaultPlaceholder(),
    onLoaded: (Drawable) -> Unit = {}
) {
    val painter = rememberImagePainter(data, builder = {
        crossfade(500)
        transformation?.let {
            transformations(it)
        }
    })

    when (painter.state) {
        is Empty, is Success -> {
            val state = painter.state as? Success

            if (state != null) {
                onLoaded(state.result.drawable)
            }

            Image(
                modifier = modifier,
                painter = painter,
                contentScale = contentScale,
                contentDescription = null
            )
        }
        is Loading -> {
            Box(modifier = modifier.shimmer().background(ChoizzyTheme.Colors.labelSecondary))
        }
        is ImagePainter.State.Error -> {
            ErrorPlaceholder(modifier, placeholder)
        }
    }
}

@Composable
private fun ErrorPlaceholder(modifier: Modifier, placeholder: ChoizzyImagePlaceholder) {
    when (placeholder) {
        is ChoizzyImagePlaceholder.ResourcePlaceholder -> {
            Image(
                modifier = modifier,
                painter = rememberImagePainter(placeholder.resource),
                contentDescription = null
            )
        }
        is ChoizzyImagePlaceholder.DefaultPlaceholder -> {
            Box(
                modifier = modifier
                    .background(placeholder.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(placeholder.size),
                    painter = painterResource(R.drawable.ic_placeholder),
                    contentDescription = null,
                    tint = ChoizzyTheme.Colors.labelTertiary
                )
            }
        }
        is ChoizzyImagePlaceholder.CustomPlaceholder -> {
            placeholder.render()
        }
    }
}

sealed class ChoizzyImagePlaceholder {
    data class ResourcePlaceholder(val resource: Any? = R.drawable.ic_placeholder) : ChoizzyImagePlaceholder()

    data class DefaultPlaceholder(
        val backgroundColor: Color = Color.Transparent,
        val size: Dp = 36.dp
    ) : ChoizzyImagePlaceholder()

    data class CustomPlaceholder(
        val render: @Composable () -> Unit
    ) : ChoizzyImagePlaceholder()
}
