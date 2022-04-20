package com.care.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.care.animationdemo.ui.FunCirclePlaceholder
import com.care.animationdemo.ui.theme.AnimationDemoTheme

@ExperimentalAnimationApi
class AnimateVisibilityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var show by remember { mutableStateOf(true) }

            AnimationDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Animate Visibility")
                            },
                            navigationIcon = {
                                IconButton(onClick = { onBackPressed() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        show = !show
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = if (show) R.drawable.ic_baseline_visibility_off_24 else R.drawable.ic_baseline_visibility_24),
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        )
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        val boxModifier = Modifier.height(100.dp)
                        item {
                            Box(modifier = boxModifier) {
                                NormalAnimation(isVisible = show)
                            }
                            Spacer(modifier = Modifier.size(32.dp))
                        }

                        item {
                            Box(modifier = boxModifier) {
                                FadeInFadeOutAnimation(isVisible = show)
                            }

                            Spacer(modifier = Modifier.size(32.dp))
                        }

                        item {
                            Box(modifier = boxModifier) {
                                ShrinkHorizontallyAnimation(isVisible = show)
                            }

                            Spacer(modifier = Modifier.size(32.dp))
                        }

                        item {
                            Box(modifier = boxModifier) {
                                CombinedAnimation(isVisible = show)
                            }

                            Spacer(modifier = Modifier.size(32.dp))
                        }
                    }
                }
            }
        }
    }



    @Composable
    fun BoxScope.NormalAnimation(isVisible: Boolean) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isVisible
        ) {
            FunCirclePlaceholder()
        }
    }

    @Composable
    fun BoxScope.FadeInFadeOutAnimation(isVisible: Boolean) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            FunCirclePlaceholder()
        }
    }

    @Composable
    fun BoxScope.ShrinkHorizontallyAnimation(isVisible: Boolean) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isVisible,
            enter = expandHorizontally(expandFrom = Alignment.CenterHorizontally),
            exit = shrinkHorizontally(shrinkTowards = Alignment.CenterHorizontally)
        ) {
            FunCirclePlaceholder()
        }
    }

    @Composable
    fun BoxScope.CombinedAnimation(isVisible: Boolean) {
        val DURATION = 2000
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = isVisible,
            enter = scaleIn() + fadeIn(),
//            enter = scaleIn(animationSpec = tween(DURATION)) + fadeIn(animationSpec = tween(DURATION)),
            exit = scaleOut() + fadeOut()
//            exit = scaleOut(animationSpec = tween(DURATION)) + fadeOut(animationSpec = tween(DURATION))
        ) {
            FunCirclePlaceholder()
        }
    }
}