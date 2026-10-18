package com.example.model

enum class TaskType {
    MULTIPLE_CHOICE,
    CHRONOLOGY,
    TACTICAL_DECISION,
    FIGURE_MATCH
}

data class HistoryTask(
    val id: String,
    val stageId: String,
    val difficulty: DifficultyLevel,
    val taskType: TaskType,
    val title: String,
    val titleUrdu: String,
    val historicalYear: String,
    val contextStory: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int = 0,
    val chronologyItems: List<String> = emptyList(),
    val correctChronologyOrder: List<String> = emptyList(),
    val explanation: String,
    val historicalFactTitle: String,
    val historicalFactDetail: String,
    val rewardXp: Int = 50,
    val rewardCoins: Int = 10
)

data class HistoryStage(
    val id: String,
    val stageNumber: Int,
    val difficulty: DifficultyLevel,
    val title: String,
    val titleUrdu: String,
    val era: String,
    val description: String,
    val tasks: List<HistoryTask>,
    val requiredStars: Int = 0
)

data class HistoryArchiveFact(
    val id: String,
    val title: String,
    val titleUrdu: String,
    val era: String,
    val year: String,
    val keyFigure: String,
    val summary: String,
    val historicalSignificance: String,
    val isUnlocked: Boolean = false
)
