package com.care.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.care.animationdemo.ui.DemoToolbar
import com.care.animationdemo.ui.theme.AnimationDemoTheme

@ExperimentalAnimationApi
class AnimateContentSizeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        DemoToolbar(
                            title = "Animate Content Size",
                            onBackPressed = {
                                onBackPressed()
                            }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                        NonAnimatedFAB()
                    }
                ) {

                }
            }
        }
    }

    @Preview
    @Composable
    fun NonAnimatedFAB() {
        var expanded by remember { mutableStateOf(false) }

        FloatingActionButton(
            shape = RoundedCornerShape(10.dp),
            onClick = {
                expanded = !expanded
            }
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_email_24),
                    contentDescription = null
                )
                if (expanded) {
                    Text(
                        text = "Compose email",
                        color = Color.Black
                    )
                }

            }
        }
    }

    @Preview
    @Composable
    fun ContentAnimatedFAB() {
        var expanded by remember { mutableStateOf(false) }

        FloatingActionButton(
            shape = RoundedCornerShape(10.dp),
            onClick = {
                expanded = !expanded
            }
        ) {

            AnimatedContent(
                targetState = expanded,
//                    transitionSpec = {
//                        fadeIn(animationSpec = tween(500, 500)) with
//                                fadeOut(animationSpec = tween(500)) using
//                                SizeTransform { initialSize, targetSize ->
//                                    tween(1000)
//                                }
//                    }
            ) { isExpanded ->
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_email_24),
                        contentDescription = null
                    )

                    if (isExpanded) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "Compose email",
                            color = Color.Black
                        )
                    }
                }
            }

        }
    }
}