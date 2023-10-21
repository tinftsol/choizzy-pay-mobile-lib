package io.choizzy.pay_lib.ui.payWithNFT

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.transform.Transformation

@Composable
fun ChoizzyAvatar(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    transformation: Transformation? = null,
    data: Any?,
    fallbackUrl: String? = null,
    id: String,
    placeholder: ChoizzyImagePlaceholder = ChoizzyImagePlaceholder.ResourcePlaceholder(),
    onLoaded: (Drawable) -> Unit = {}
) {
    ChoizzyImage(
        modifier = modifier,
        data = data,
        fallbackUrl = fallbackUrl,
        transformation = transformation,
        contentScale = contentScale,
        placeholder = placeholder,
        onLoaded = onLoaded
    )
}
