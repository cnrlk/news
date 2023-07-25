package com.caneru.news.ui.views

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.caneru.news.ui.noRippleClickable

enum class SwitchButtonState(private val bias: Float = -1f) {
    ENABLED(1f), DISABLED(-1f);

    fun not(): SwitchButtonState {
        return when (this) {
            ENABLED -> {
                DISABLED
            }

            DISABLED -> {
                ENABLED
            }
        }
    }

    fun bias(): Float {
        return bias
    }

    fun enabled(): Boolean {
        return bias == 1f
    }
}

@Composable
fun SwitchButton(
    modifier: Modifier = Modifier,
    state: SwitchButtonState = SwitchButtonState.DISABLED,
    onSwitchStateChanged: (Boolean) -> Unit = {},
    enabled: Boolean = true
) {

    val transition = updateTransition(targetState = state, label = "")
    val colorChange by transition.animateColor(label = "") {
        if (it.enabled()) Color.Green else Color.White
    }

    val alignment by animateHorizontalAlignmentAsState(state.bias())
    Column(
        horizontalAlignment = alignment,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .size(
                width = 52.dp,
                height = 32.dp
            )
            .background(
                color = colorChange,
                shape = RoundedCornerShape(
                    CornerSize(16.dp)
                )
            )
            .noRippleClickable(enabled = enabled,
                onClick = {
                    onSwitchStateChanged(
                        state
                            .not()
                            .enabled()
                    )
                })
            .border(
                BorderStroke(Dp.Hairline, Color.Gray), RoundedCornerShape(
                    CornerSize(16.dp)
                )
            )
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(2.dp)
                .size(28.dp),
            elevation = 8.dp
        ) {

        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun animateHorizontalAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment.Horizontal> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment.Horizontal(bias) }
}

@Preview
@Composable
fun PreviewSwitchButton() {
    SwitchButton()
}