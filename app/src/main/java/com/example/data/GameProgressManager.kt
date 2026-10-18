package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.DifficultyLevel
import com.example.model.HistoryStage
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GameProgressManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("islamic_history_quest_prefs", Context.MODE_PRIVATE)

    private val _userProfile = MutableStateFlow(loadProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private fun loadProfile(): UserProfile {
        val xp = prefs.getInt("user_xp", 0)
        val dinars = prefs.getInt("user_dinars", 50)
        val hasanat = prefs.getInt("user_hasanat", 100)
        val streak = prefs.getInt("user_streak", 1)
        val dailyDate = prefs.getString("user_daily_date", "") ?: ""
        val sound = prefs.getBoolean("user_sound", true)
        val voice = prefs.getBoolean("user_voice", true)

        val allKeys = prefs.all
        val stageStars = mutableMapOf<String, Int>()
        for ((key, value) in allKeys) {
            if (key.startsWith("stage_stars_") && value is Int) {
                val stageId = key.removePrefix("stage_stars_")
                stageStars[stageId] = value
            }
        }

        val factsSet = prefs.getStringSet("unlocked_facts", emptySet()) ?: emptySet()
        val missionsSet = prefs.getStringSet("completed_missions", emptySet()) ?: emptySet()

        return UserProfile(
            xp = xp,
            dinars = dinars,
            hasanat = hasanat,
            streakDays = streak,
            stageStars = stageStars,
            unlockedFactIds = factsSet,
            completedMissionIds = missionsSet,
            completedDailyQuestDate = dailyDate,
            soundEffectsEnabled = sound,
            voiceNarrationEnabled = voice
        )
    }

    fun isStageUnlocked(stage: HistoryStage): Boolean {
        val totalStars = _userProfile.value.totalStars
        if (totalStars < stage.requiredStars) return false
        if (stage.id == "easy_stage_1") return true

        val allStages = HistoryRepository.stages
        val index = allStages.indexOfFirst { it.id == stage.id }
        if (index <= 0) return true

        val prevStage = allStages[index - 1]
        val prevStars = _userProfile.value.stageStars[prevStage.id] ?: 0
        return prevStars > 0 || totalStars >= stage.requiredStars
    }

    fun isDifficultyUnlocked(difficulty: DifficultyLevel): Boolean {
        return _userProfile.value.totalStars >= difficulty.requiredStarsToUnlock
    }

    fun recordStageCompleted(stageId: String, stars: Int, earnedXp: Int, earnedCoins: Int) {
        val currentProfile = _userProfile.value
        val existingStars = currentProfile.stageStars[stageId] ?: 0
        val bestStars = maxOf(existingStars, stars)

        val isFirstTime = existingStars == 0
        val additionalXp = if (isFirstTime) earnedXp else (earnedXp / 3)
        val additionalCoins = if (isFirstTime) earnedCoins else (earnedCoins / 2)
        val addedHasanat = 50 * stars

        val updatedMap = currentProfile.stageStars.toMutableMap()
        updatedMap[stageId] = bestStars

        val newXp = currentProfile.xp + additionalXp
        val newCoins = currentProfile.dinars + additionalCoins
        val newHasanat = currentProfile.hasanat + addedHasanat

        val updatedFacts = currentProfile.unlockedFactIds.toMutableSet()
        when (stageId) {
            "easy_stage_1" -> updatedFacts.add("fact_hijrah")
            "medium_stage_1" -> updatedFacts.add("fact_yarmouk")
        }

        prefs.edit()
            .putInt("stage_stars_$stageId", bestStars)
            .putInt("user_xp", newXp)
            .putInt("user_dinars", newCoins)
            .putInt("user_hasanat", newHasanat)
            .putStringSet("unlocked_facts", updatedFacts)
            .apply()

        _userProfile.value = currentProfile.copy(
            xp = newXp,
            dinars = newCoins,
            hasanat = newHasanat,
            stageStars = updatedMap,
            unlockedFactIds = updatedFacts
        )
    }

    fun recordMissionCompleted(missionId: String, earnedHasanat: Int, earnedXp: Int) {
        val currentProfile = _userProfile.value
        val updatedMissions = currentProfile.completedMissionIds.toMutableSet()
        updatedMissions.add(missionId)

        val newHasanat = currentProfile.hasanat + earnedHasanat
        val newXp = currentProfile.xp + earnedXp

        prefs.edit()
            .putStringSet("completed_missions", updatedMissions)
            .putInt("user_hasanat", newHasanat)
            .putInt("user_xp", newXp)
            .apply()

        _userProfile.value = currentProfile.copy(
            completedMissionIds = updatedMissions,
            hasanat = newHasanat,
            xp = newXp
        )
    }

    fun addHasanat(amount: Int) {
        val currentProfile = _userProfile.value
        val newHasanat = currentProfile.hasanat + amount
        prefs.edit().putInt("user_hasanat", newHasanat).apply()
        _userProfile.value = currentProfile.copy(hasanat = newHasanat)
    }

    fun recordDailyQuestCompleted(earnedXp: Int, earnedCoins: Int) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentProfile = _userProfile.value

        val newXp = currentProfile.xp + earnedXp
        val newCoins = currentProfile.dinars + earnedCoins
        val newStreak = currentProfile.streakDays + 1

        prefs.edit()
            .putString("user_daily_date", today)
            .putInt("user_xp", newXp)
            .putInt("user_dinars", newCoins)
            .putInt("user_streak", newStreak)
            .apply()

        _userProfile.value = currentProfile.copy(
            xp = newXp,
            dinars = newCoins,
            streakDays = newStreak,
            completedDailyQuestDate = today
        )
    }

    fun isDailyQuestCompletedToday(): Boolean {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return _userProfile.value.completedDailyQuestDate == today
    }

    fun toggleVoiceNarration(enabled: Boolean) {
        prefs.edit().putBoolean("user_voice", enabled).apply()
        _userProfile.value = _userProfile.value.copy(voiceNarrationEnabled = enabled)
    }
}
