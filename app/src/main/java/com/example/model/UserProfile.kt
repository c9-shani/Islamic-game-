package com.example.model

data class UserProfile(
    val xp: Int = 0,
    val dinars: Int = 50,
    val hasanat: Int = 100, // Good deeds / Hasanat points
    val streakDays: Int = 1,
    val stageStars: Map<String, Int> = emptyMap(),
    val unlockedFactIds: Set<String> = emptySet(),
    val completedMissionIds: Set<String> = emptySet(),
    val completedDailyQuestDate: String = "",
    val soundEffectsEnabled: Boolean = true,
    val voiceNarrationEnabled: Boolean = true
) {
    val totalStars: Int
        get() = stageStars.values.sum()

    val rankTitle: String
        get() = when {
            xp < 250 -> "Talib-e-Ilm (طالبِ علم)"
            xp < 650 -> "Raviyan (راویِ تاریخ)"
            xp < 1300 -> "Muarrikh (مورخِ تاریخ)"
            xp < 2400 -> "Sahib-e-Hikmat (صاحبِ حکمت)"
            else -> "Fateh-e-Tareekh (فاتحِ تاریخ)"
        }

    val rankSubtitle: String
        get() = when {
            xp < 250 -> "Student of Faith & History"
            xp < 650 -> "Messenger of Good Deeds & Prayer"
            xp < 1300 -> "Distinguished Islamic Historian"
            xp < 2400 -> "Grand Scholar of the Golden Age"
            else -> "Supreme Master of Islamic History"
        }

    val currentRankLevel: Int
        get() = when {
            xp < 250 -> 1
            xp < 650 -> 2
            xp < 1300 -> 3
            xp < 2400 -> 4
            else -> 5
        }

    val nextRankXpThreshold: Int
        get() = when {
            xp < 250 -> 250
            xp < 650 -> 650
            xp < 1300 -> 1300
            xp < 2400 -> 2400
            else -> 3500
        }
}
