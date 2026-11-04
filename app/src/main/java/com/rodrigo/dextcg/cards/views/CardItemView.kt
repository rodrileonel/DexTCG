package com.rodrigo.dextcg.cards.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.rodrigo.dextcg.R
import com.rodrigo.domain.model.PokemonCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun CardItemView (
    card: PokemonCard
){
    var showDialogAnimation by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                showDialog = true
            },
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentDescription = card.name,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            model = card.imageUrl,
        )
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            LaunchedEffect(Unit) {
                showDialogAnimation = false
                // delay(16) // ~1 frame (60fps)
                yield()
                showDialogAnimation = true
            }

            CardDetailView(
                imageUrl = card.imageUrl?:"",
                isVisible = showDialogAnimation,
                dialogTime = 500,
                onDismiss = { showDialogAnimation = false }
            )

            LaunchedEffect(showDialogAnimation) {
                if (!showDialogAnimation) {
                    delay(500)
                    showDialog = false
                }
            }
        }
    }
}