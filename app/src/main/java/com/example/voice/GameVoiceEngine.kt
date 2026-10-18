package com.example.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class GameVoiceEngine(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isInitialized = false
    private var isVoiceEnabled = true

    init {
        try {
            tts = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            Log.e("GameVoiceEngine", "Error initializing TTS", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isInitialized = true
            // Prefer Urdu or Hindi or Arabic or English for clear speech
            val urduLocale = Locale("ur", "PK")
            val hindiLocale = Locale("hi", "IN")
            val englishLocale = Locale.US

            val availableLocale = when {
                tts?.isLanguageAvailable(urduLocale) == TextToSpeech.LANG_AVAILABLE -> urduLocale
                tts?.isLanguageAvailable(hindiLocale) == TextToSpeech.LANG_AVAILABLE -> hindiLocale
                else -> englishLocale
            }
            tts?.language = availableLocale
            tts?.setSpeechRate(0.92f)
        }
    }

    fun speak(text: String, pitch: Float = 1.0f, speechRate: Float = 0.95f) {
        if (!isVoiceEnabled || !isInitialized || tts == null) return
        try {
            tts?.setPitch(pitch)
            tts?.setSpeechRate(speechRate)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "GAME_VOICE_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.e("GameVoiceEngine", "Speak error", e)
        }
    }

    fun speakZayd(text: String) {
        // Zayd: Youthful, friendly pitch
        speak(text, pitch = 1.15f, speechRate = 0.96f)
    }

    fun speakElder(text: String) {
        // Elder / Scholar: Deep, calm, dignified pitch
        speak(text, pitch = 0.85f, speechRate = 0.88f)
    }

    fun speakCitizen(text: String) {
        // Citizen / Shopkeeper: Natural conversational pitch
        speak(text, pitch = 1.0f, speechRate = 0.95f)
    }

    fun toggleVoice(enabled: Boolean) {
        isVoiceEnabled = enabled
        if (!enabled) {
            stop()
        }
    }

    fun isVoiceOn(): Boolean = isVoiceEnabled

    fun stop() {
        try {
            tts?.stop()
        } catch (_: Exception) {}
    }

    fun shutdown() {
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (_: Exception) {}
    }
}
