package com.example.model

enum class SpeakerType {
    ZAYD,
    NPC,
    ELDER,
    SYSTEM
}

data class MissionDialogue(
    val speakerName: String,
    val speakerType: SpeakerType,
    val speechUrdu: String,
    val speechEnglish: String,
    val voiceSpokenText: String
)

data class MiniIslamicQuiz(
    val id: String,
    val questionUrdu: String,
    val questionEnglish: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanationUrdu: String,
    val rewardHasanat: Int = 50,
    val rewardXp: Int = 30
)

data class IslamicMission(
    val id: String,
    val missionNumber: Int,
    val titleUrdu: String,
    val titleEnglish: String,
    val objectiveUrdu: String,
    val objectiveEnglish: String,
    val targetNpcId: String,
    val targetLocationName: String,
    val targetX: Float, // 0..100% on map
    val targetY: Float, // 0..100% on map
    val dialogues: List<MissionDialogue>,
    val miniQuiz: MiniIslamicQuiz? = null,
    val rewardHasanat: Int = 100,
    val rewardXp: Int = 150,
    val isCompleted: Boolean = false
)
