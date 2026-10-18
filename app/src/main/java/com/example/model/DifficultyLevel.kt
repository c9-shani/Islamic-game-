package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.EasyGreen
import com.example.ui.theme.HardAmber
import com.example.ui.theme.MediumBlue
import com.example.ui.theme.VeryHardCrimson

enum class DifficultyLevel(
    val id: String,
    val titleEnglish: String,
    val titleUrdu: String,
    val subtitle: String,
    val color: Color,
    val requiredStarsToUnlock: Int,
    val xpMultiplier: Int
) {
    EASY(
        id = "easy",
        titleEnglish = "Easy",
        titleUrdu = "آسان (Aasan)",
        subtitle = "Seerah, Hijrah & Khulafa-e-Rashideen Basics",
        color = EasyGreen,
        requiredStarsToUnlock = 0,
        xpMultiplier = 1
    ),
    MEDIUM(
        id = "medium",
        titleEnglish = "Medium",
        titleUrdu = "متوسط (Mutawassit)",
        subtitle = "Conquests of Yarmouk, Al-Quds & Andalusia",
        color = MediumBlue,
        requiredStarsToUnlock = 6,
        xpMultiplier = 2
    ),
    HARD(
        id = "hard",
        titleEnglish = "Hard",
        titleUrdu = "مشکل (Mushkil)",
        subtitle = "Battle of Hattin, Ain Jalut & Golden Age Science",
        color = HardAmber,
        requiredStarsToUnlock = 16,
        xpMultiplier = 3
    ),
    VERY_HARD(
        id = "very_hard",
        titleEnglish = "Very Hard",
        titleUrdu = "نہایت مشکل (Nihayat Mushkil)",
        subtitle = "Conquest of Constantinople & Imperial Chronology",
        color = VeryHardCrimson,
        requiredStarsToUnlock = 28,
        xpMultiplier = 4
    )
}
