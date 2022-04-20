package com.care.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.care.animationdemo.ui.DemoToolbar
import com.care.animationdemo.ui.theme.AnimationDemoTheme

class AnimateAsStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DemoToolbar(
                            title = "Animate*AsState",
                            onBackPressed = {
                                onBackPressed()
                            }
                        )
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            DemoColor()
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        item {
                            DemoSize()
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        item {
                            DemoCombinedOne()
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        item {
                            DemoCombinedTwo()
                            Spacer(modifier = Modifier.height(32.dp))
                        }

                        item {
                            Spacer(modifier = Modifier.height(500.dp))
                        }
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun DemoColor() {
        var active by remember { mutableStateOf(false) }
        val color by animateColorAsState(
            targetValue = if (active) Color.Blue else Color.Green,
//            animationSpec = tween(durationMillis = 10000, easing = LinearOutSlowInEasing)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color)
                .clickable {
                    active = !active
                }
        )
    }

    @Preview
    @Composable
    fun DemoSize() {
        var active by remember { mutableStateOf(false) }
        val size by animateDpAsState(targetValue = if (active) 30.dp else 200.dp)
        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Blue)
                .clickable {
                    active = !active
                }
        )
    }

    @Preview
    @Composable
    fun DemoCombinedOne() {
        val DURATION = 1000

        var active by remember { mutableStateOf(false) }

        val size by animateDpAsState(
            targetValue = if (active) 100.dp else 200.dp,
            animationSpec = tween(DURATION)
        )

        val color by animateColorAsState(
            targetValue = if (active) Color.Blue else Color.Green,
            animationSpec = tween(DURATION)
        )

        Box(
            modifier = Modifier
                .size(size)
                .background(color)
                .clickable {
                    active = !active
                }
        )
    }

    @Preview
    @Composable
    fun DemoCombinedTwo() {
        var active by remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = active, label = "transition")

        val size by transition.animateDp(
            transitionSpec = {
                if (this.targetState) {
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                    )
                } else {
                    tween(1500)
                }
            },
            label = "Box Size"
        ) {
            if (it) 100.dp else 200.dp
        }

        val color by transition.animateColor(
            transitionSpec = {
                if (this.targetState) {
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessMedium,
                    )
                } else {
                    tween(1500)
                }
            }, label = "color"
        ) {
            if (it) Color.Blue else Color.Green
        }

        Box(
            modifier = Modifier
                .size(size)
                .background(color)
                .clickable {
                    active = !active
                }
        )
    }
}