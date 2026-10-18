package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.GameProgressManager
import com.example.model.DifficultyLevel
import com.example.model.HistoryStage
import com.example.ui.screens.ArchiveScreen
import com.example.ui.screens.DailyQuestScreen
import com.example.ui.screens.GamePlayScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.Realistic3DGameScreen
import com.example.ui.screens.StageSelectScreen
import com.example.ui.screens.WorldMapScreen
import com.example.ui.theme.MyApplicationTheme

sealed interface Screen {
    data object Home : Screen
    data object Realistic3DGame : Screen
    data object WorldMap : Screen
    data class StageSelect(val difficulty: DifficultyLevel) : Screen
    data class GamePlay(val stage: HistoryStage) : Screen
    data object DailyQuest : Screen
    data object Archive : Screen
}

class MainActivity : ComponentActivity() {

    private lateinit var gameManager: GameProgressManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        gameManager = GameProgressManager(this)

        setContent {
            MyApplicationTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.safeDrawingPadding()) {
                        IslamicHistoryApp(gameManager = gameManager)
                    }
                }
            }
        }
    }
}

@Composable
fun IslamicHistoryApp(gameManager: GameProgressManager) {
    val profile by gameManager.userProfile.collectAsStateWithLifecycle()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            is Screen.Home -> {
                HomeScreen(
                    profile = profile,
                    gameManager = gameManager,
                    onOpenRealistic3DGame = {
                        currentScreen = Screen.Realistic3DGame
                    },
                    onOpenWorldMap = {
                        currentScreen = Screen.WorldMap
                    },
                    onSelectDifficulty = { diff ->
                        currentScreen = Screen.StageSelect(diff)
                    },
                    onOpenDailyQuest = {
                        currentScreen = Screen.DailyQuest
                    },
                    onOpenArchive = {
                        currentScreen = Screen.Archive
                    }
                )
            }

            is Screen.Realistic3DGame -> {
                Realistic3DGameScreen(
                    profile = profile,
                    gameManager = gameManager,
                    onBack = {
                        currentScreen = Screen.Home
                    }
                )
            }

            is Screen.WorldMap -> {
                WorldMapScreen(
                    profile = profile,
                    gameManager = gameManager,
                    onStartStageTask = { stage ->
                        currentScreen = Screen.GamePlay(stage)
                    },
                    onBack = {
                        currentScreen = Screen.Home
                    }
                )
            }

            is Screen.StageSelect -> {
                StageSelectScreen(
                    difficulty = screen.difficulty,
                    profile = profile,
                    gameManager = gameManager,
                    onStageSelected = { stage ->
                        currentScreen = Screen.GamePlay(stage)
                    },
                    onBack = {
                        currentScreen = Screen.Home
                    }
                )
            }

            is Screen.GamePlay -> {
                GamePlayScreen(
                    stage = screen.stage,
                    gameManager = gameManager,
                    onFinish = {
                        currentScreen = Screen.StageSelect(screen.stage.difficulty)
                    },
                    onBack = {
                        currentScreen = Screen.StageSelect(screen.stage.difficulty)
                    }
                )
            }

            is Screen.DailyQuest -> {
                DailyQuestScreen(
                    gameManager = gameManager,
                    onBack = {
                        currentScreen = Screen.Home
                    }
                )
            }

            is Screen.Archive -> {
                ArchiveScreen(
                    profile = profile,
                    onBack = {
                        currentScreen = Screen.Home
                    }
                )
            }
        }
    }
}
