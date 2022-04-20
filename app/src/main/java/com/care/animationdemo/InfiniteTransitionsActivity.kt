package com.care.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.care.animationdemo.ui.DemoToolbar
import com.care.animationdemo.ui.theme.AnimationDemoTheme

class InfiniteTransitionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DemoToolbar(
                            title = "Infinite Transitions",
                            onBackPressed = {
                                onBackPressed()
                            }
                        )
                    }
                ) {

                    Box(modifier = Modifier.fillMaxSize()) {
                        StarAnimation()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun BoxScope.StarAnimation() {
        val infiniteTransition = rememberInfiniteTransition()
        val size by infiniteTransition.animateFloat(
            initialValue = 50f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Icon(
            modifier = Modifier
                .size(size.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
        )
    }

    @Preview
    @Composable
    fun BoxScope.StarAnimationTwo() {
        val infiniteTransition = rememberInfiniteTransition()
        val size by infiniteTransition.animateFloat(
            initialValue = 50f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val color by infiniteTransition.animateColor(
            initialValue = Color.Gray,
            targetValue = Color.Yellow,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500),
                repeatMode = RepeatMode.Reverse
            )
        )

        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -30f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500),
                repeatMode = RepeatMode.Reverse
            )
        )

        Icon(
            modifier = Modifier
                .size(size.dp)
                .rotate(rotation)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            tint = color
        )
    }
}