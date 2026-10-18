package com.example.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GameProgressManager
import com.example.model.HistoryStage
import com.example.model.HistoryTask
import com.example.ui.components.StageCompletedDialog
import com.example.ui.components.TaskResultDialog
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import com.example.util.HapticHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePlayScreen(
    stage: HistoryStage,
    gameManager: GameProgressManager,
    onFinish: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler { onBack() }

    var currentTaskIndex by remember { mutableIntStateOf(0) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var showResultDialog by remember { mutableStateOf(false) }
    var isCurrentTaskCorrect by remember { mutableStateOf(false) }
    var showStageCompletedDialog by remember { mutableStateOf(false) }
    var totalEarnedXp by remember { mutableIntStateOf(0) }
    var totalEarnedCoins by remember { mutableIntStateOf(0) }

    val currentTask = stage.tasks.getOrNull(currentTaskIndex) ?: stage.tasks.first()
    val isLastTask = currentTaskIndex == stage.tasks.size - 1

    fun handleAnswer(isCorrect: Boolean, xp: Int, coins: Int) {
        if (isCorrect) {
            HapticHelper.performSuccess(context)
            correctAnswersCount++
            totalEarnedXp += xp
            totalEarnedCoins += coins
        } else {
            HapticHelper.performError(context)
        }
        isCurrentTaskCorrect = isCorrect
        showResultDialog = true
    }

    fun proceedToNextTask() {
        showResultDialog = false
        selectedOptionIndex = null
        if (isLastTask) {
            val stars = when {
                correctAnswersCount == stage.tasks.size -> 3
                correctAnswersCount >= (stage.tasks.size * 0.6).toInt() -> 2
                else -> 1
            }
            gameManager.recordStageCompleted(
                stageId = stage.id,
                stars = stars,
                earnedXp = totalEarnedXp,
                earnedCoins = totalEarnedCoins
            )
            showStageCompletedDialog = true
        } else {
            currentTaskIndex++
        }
    }

    Scaffold(
        modifier = modifier.testTag("game_play_screen"),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stage.titleUrdu,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Task ${currentTaskIndex + 1} of ${stage.tasks.size}",
                            style = MaterialTheme.typography.bodySmall,
                            color = stage.difficulty.color,
                            fontSize = 11.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = GoldLight
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                val progress = ((currentTaskIndex + 1).toFloat() / stage.tasks.size.toFloat())
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = stage.difficulty.color,
                    trackColor = Color.White.copy(alpha = 0.1f)
                )
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            Brush.verticalGradient(listOf(GoldAccent.copy(alpha = 0.6f), EmeraldDark)),
                            RoundedCornerShape(18.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = GoldAccent.copy(alpha = 0.2f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldAccent)
                        ) {
                            Text(
                                text = currentTask.historicalYear,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = currentTask.titleUrdu,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            fontSize = 15.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = currentTask.contextStory,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = Color.Black.copy(alpha = 0.35f)
                        ) {
                            Text(
                                text = currentTask.question,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(12.dp),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }

            // Options
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    currentTask.options.forEachIndexed { index, optionText ->
                        val isSelected = selectedOptionIndex == index
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    if (isSelected) GoldAccent else Color.White.copy(alpha = 0.12f),
                                    RoundedCornerShape(14.dp)
                                )
                                .clickable {
                                    if (selectedOptionIndex == null) {
                                        selectedOptionIndex = index
                                        HapticHelper.performClick(context)
                                        val isCorrect = index == currentTask.correctIndex
                                        handleAnswer(isCorrect, currentTask.rewardXp, currentTask.rewardCoins)
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) GoldAccent.copy(alpha = 0.18f) else MaterialTheme.colorScheme.surfaceVariant
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(GoldAccent.copy(alpha = 0.2f))
                                    )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showResultDialog) {
        TaskResultDialog(
            isCorrect = isCurrentTaskCorrect,
            task = currentTask,
            earnedXp = if (isCurrentTaskCorrect) currentTask.rewardXp else 0,
            earnedCoins = if (isCurrentTaskCorrect) currentTask.rewardCoins else 0,
            isLastTask = isLastTask,
            onContinue = { proceedToNextTask() }
        )
    }

    if (showStageCompletedDialog) {
        val finalStars = when {
            correctAnswersCount == stage.tasks.size -> 3
            correctAnswersCount >= (stage.tasks.size * 0.6).toInt() -> 2
            else -> 1
        }
        StageCompletedDialog(
            stageTitle = stage.title,
            starsEarned = finalStars,
            totalXp = totalEarnedXp,
            totalCoins = totalEarnedCoins,
            onReturnToMenu = onFinish,
            onReplay = {
                currentTaskIndex = 0
                correctAnswersCount = 0
                totalEarnedXp = 0
                totalEarnedCoins = 0
                selectedOptionIndex = null
                showStageCompletedDialog = false
            }
        )
    }
}
