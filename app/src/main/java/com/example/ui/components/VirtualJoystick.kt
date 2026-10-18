package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun VirtualJoystick(
    onMove: (deltaX: Float, deltaY: Float) -> Unit,
    modifier: Modifier = Modifier,
    joystickRadiusDp: Float = 60f
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val maxDragPx = joystickRadiusDp * 1.5f

    Box(
        modifier = modifier
            .size((joystickRadiusDp * 2).dp)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    listOf(
                        Color.Black.copy(alpha = 0.55f),
                        Color(0xFF071F15).copy(alpha = 0.85f)
                    )
                )
            )
            .border(2.dp, GoldAccent.copy(alpha = 0.6f), CircleShape)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {},
                    onDragEnd = {
                        offsetX = 0f
                        offsetY = 0f
                        onMove(0f, 0f)
                    },
                    onDragCancel = {
                        offsetX = 0f
                        offsetY = 0f
                        onMove(0f, 0f)
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val newX = offsetX + dragAmount.x
                        val newY = offsetY + dragAmount.y
                        val dist = hypot(newX.toDouble(), newY.toDouble()).toFloat()

                        if (dist <= maxDragPx) {
                            offsetX = newX
                            offsetY = newY
                        } else {
                            val angle = Math.atan2(newY.toDouble(), newX.toDouble())
                            offsetX = (cos(angle) * maxDragPx).toFloat()
                            offsetY = (sin(angle) * maxDragPx).toFloat()
                        }

                        val normalizedX = (offsetX / maxDragPx).coerceIn(-1f, 1f)
                        val normalizedY = (offsetY / maxDragPx).coerceIn(-1f, 1f)
                        onMove(normalizedX, normalizedY)
                    }
                )
            }
            .testTag("virtual_joystick"),
        contentAlignment = Alignment.Center
    ) {
        // Inner Joystick Knob
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .size(44.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(GoldLight, GoldAccent, EmeraldPrimary)
                    )
                )
                .border(2.dp, Color.White, CircleShape)
        )
    }
}
