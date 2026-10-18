package com.example.model

import com.example.R

enum class CharacterSpeaker(
    val characterNameEnglish: String,
    val characterNameUrdu: String,
    val roleTitleUrdu: String,
    val roleTitleEnglish: String,
    val drawableResId: Int
) {
    ZAYD(
        characterNameEnglish = "Zayd",
        characterNameUrdu = "زید",
        roleTitleUrdu = "نوجوان مہم جو (Young Explorer)",
        roleTitleEnglish = "Curious Explorer",
        drawableResId = R.drawable.hero_cartoon_3d
    ),
    SHEIKH_TARIQ(
        characterNameEnglish = "Sheikh Tariq",
        characterNameUrdu = "شیخ طارق",
        roleTitleUrdu = "عظیم مورخ و استاد (Wise Historian)",
        roleTitleEnglish = "Master Historian",
        drawableResId = R.drawable.scholar_cartoon_3d
    ),
    MARYAM(
        characterNameEnglish = "Maryam",
        characterNameUrdu = "مریم",
        roleTitleUrdu = "ماہرِ فلکیات و کتب (Scholar & Astronomer)",
        roleTitleEnglish = "Scholar & Polymath",
        drawableResId = R.drawable.companion_cartoon_3d
    )
}

data class DialogueLine(
    val speaker: CharacterSpeaker,
    val textUrdu: String,
    val textEnglish: String
)

data class MashwaraSession(
    val id: String,
    val stageId: String,
    val landmarkNameUrdu: String,
    val landmarkNameEnglish: String,
    val topicTitle: String,
    val lines: List<DialogueLine>,
    val targetStage: HistoryStage
)
