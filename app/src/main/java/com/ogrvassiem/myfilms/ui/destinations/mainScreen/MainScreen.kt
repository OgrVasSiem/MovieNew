package com.ogrvassiem.myfilms.ui.destinations.mainScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.ogrvassiem.myfilms.R
import com.ogrvassiem.myfilms.ui.theme.Theme
import kotlin.math.absoluteValue

@Composable
fun MainScreenUI(
    viewModel: MainScreenViewModel,
    navigateToWaitingRoom: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = {
            MainTopAppBar()
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Theme.colors.bg)
        ) {
            ChoiceCategory(viewModel = viewModel)

            Spacer(modifier = Modifier.weight(1f))

            ButtonToStart(
                navigateToWaitingRoom = navigateToWaitingRoom,
                viewModel = viewModel
            )

            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChoiceCategory(
    viewModel: MainScreenViewModel,
) {
    val categoryInfo = viewModel.categoryInfo.collectAsState().value

    val pagerState = rememberPagerState(initialPage = 2) { 5 }

    if (categoryInfo != null) {
        Column(
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 60.dp),
                modifier = Modifier
                    .padding(10.dp)
            ) { page ->
                val card = categoryInfo[page]

                val isSelected = viewModel.selectedCategories.contains(card.nameCategory)

                val icCheckBox = if (isSelected) {
                    painterResource(R.drawable.ic_selected)
                } else {
                    painterResource(R.drawable.ic_not_selected)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset =
                                (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue
                            val scale = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                            scaleX = scale
                            scaleY = scale
                        }
                        .clip(RoundedCornerShape(18.dp))
                        .background(Theme.colors.textGray)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            viewModel.toggleCategory(card.nameCategory)
                        }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(card.image)
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null
                    )

                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = card.nameCategory,
                            style = Theme.textStyles.title,
                            color = Theme.colors.black,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = icCheckBox,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        thickness = 0.5.dp
                    )

                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = stringResource(R.string.quantity_films, card.quantity)
                        )
                    }

                }
            }
        }
    } else {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Theme.colors.bg
        ),
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        }
    )
}

@Composable
fun ButtonToStart(
    navigateToWaitingRoom: () -> Unit,
    viewModel: MainScreenViewModel,
) {
    val background = if (viewModel.selectedCategories.isEmpty()) {
        Theme.colors.gold.copy(alpha = 0.5f)
    } else {
        Theme.colors.gold
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Theme.colors.gold)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    println("А ты еще слишком тупой для этой кнопки")
                },
            contentAlignment = Alignment.Center

        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.join_to_session),
                color = Theme.colors.white,
                style = Theme.textStyles.buttonText,
                modifier = Modifier.padding(vertical = 20.dp),
                minLines = 2,
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(background)
                .weight(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (viewModel.selectedCategories.isEmpty()) {
                    } else {
                        navigateToWaitingRoom()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.start_to_session),
                color = Theme.colors.white,
                style = Theme.textStyles.buttonText,
                modifier = Modifier.padding(vertical = 20.dp),
                minLines = 2
            )
        }
    }
}