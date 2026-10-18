package com.example.model

import com.example.R

data class NpcCitizen(
    val id: String,
    val nameUrdu: String,
    val nameEnglish: String,
    val roleUrdu: String,
    val roleEnglish: String,
    val posX: Float, // percentage 0..100
    val posY: Float, // percentage 0..100
    val avatarDrawableRes: Int = R.drawable.scholar_cartoon_3d,
    val greetingSalamUrdu: String,
    val salamReplyEnglish: String,
    val namazAdviceResponseUrdu: String,
    val namazAdviceResponseEnglish: String,
    val islamicWisdomUrdu: String,
    val islamicWisdomEnglish: String,
    val miniQuiz: MiniIslamicQuiz? = null
)
