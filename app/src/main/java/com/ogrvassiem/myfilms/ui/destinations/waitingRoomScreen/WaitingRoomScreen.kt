package com.ogrvassiem.myfilms.ui.destinations.waitingRoomScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ogrvassiem.myfilms.R
import com.ogrvassiem.myfilms.aplication.rest.TopicsResponse
import com.ogrvassiem.myfilms.ui.theme.Theme

@Composable
fun WaitingRoomScreenUI(
    viewModel: WaitingRoomScreenViewModel,
    navigateToGameScreen: () -> Unit
) {
    val selectedCategory = viewModel.categoryInfo.collectAsState().value

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = {
            WaitingRoomTopAppBar(viewModel = viewModel)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(color = Theme.colors.bg)
        ) {
            if (selectedCategory != null) {
                Column() {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        items(selectedCategory) { card ->
                            CardsCategory(
                                card = card,
                                viewModel = viewModel
                            )
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Spacer(
                                modifier = Modifier.height(
                                    80.dp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(top = 16.dp)
                ) {
                    ButtonToStartGame(
                        navigateToGameScreen = navigateToGameScreen
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingRoomTopAppBar(viewModel: WaitingRoomScreenViewModel) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Theme.colors.bg
        ),
        title = {
            Text(
                text = "Ваш код сеанса - ${viewModel.password.collectAsState().value}",
                style = Theme.textStyles.title,
                color = Theme.colors.black,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun CardsCategory(
    card: TopicsResponse.Topic,
    viewModel: WaitingRoomScreenViewModel
) {
    val columnWidth = (LocalConfiguration.current.screenWidthDp.dp - (4 * 8.dp)) / 2

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .width(columnWidth)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(android.graphics.Color.parseColor(card.color)))
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = card.image)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier
                .height(230.dp)
                .align(CenterHorizontally),
            contentScale = ContentScale.FillBounds
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = card.nameCategory,
            style = Theme.textStyles.title,
            color = Theme.colors.white,
            modifier = Modifier.align(CenterHorizontally),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            color = Theme.colors.white.copy(alpha = 0.4f),
            thickness = 0.5f.dp,
        )
    }
}

@Composable
fun ButtonToStartGame(
    navigateToGameScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 30.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Theme.colors.gold)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navigateToGameScreen()
            },
        contentAlignment = Center
    ) {
        Text(
            text = stringResource(R.string.start_to_game),
            style = Theme.textStyles.buttonText,
            color = Theme.colors.white,
            minLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}