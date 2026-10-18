package com.example.data

import com.example.model.DifficultyLevel
import com.example.model.HistoryArchiveFact
import com.example.model.HistoryStage
import com.example.model.HistoryTask
import com.example.model.TaskType

object HistoryRepository {

    val stages: List<HistoryStage> = listOf(
        // ================= EASY STAGES =================
        HistoryStage(
            id = "easy_stage_1",
            stageNumber = 1,
            difficulty = DifficultyLevel.EASY,
            title = "The Dawn & Migration (Hijrah)",
            titleUrdu = "فجرِ اسلام اور ہجرتِ مدینہ",
            era = "Early Islamic Era (1 AH / 622 CE)",
            description = "Explore the beginnings of Islamic history, the migration to Madinah, and the foundation of the first Islamic state.",
            requiredStars = 0,
            tasks = listOf(
                HistoryTask(
                    id = "easy_t1_1",
                    stageId = "easy_stage_1",
                    difficulty = DifficultyLevel.EASY,
                    taskType = TaskType.MULTIPLE_CHOICE,
                    title = "The Islamic Calendar Epoch",
                    titleUrdu = "اسلامی تقویم کی ابتدا",
                    historicalYear = "622 CE / 1 AH",
                    contextStory = "The calendar of Islam begins with a pivotal event that changed world history.",
                    question = "Which monumental historical event marks the beginning (Year 1) of the Hijri Islamic calendar?",
                    options = listOf(
                        "The Hijrah (Migration) from Makkah to Madinah",
                        "The Conquest of Makkah",
                        "The First Revelation in Mount Hira",
                        "The Treaty of Hudaybiyyah"
                    ),
                    correctIndex = 0,
                    explanation = "Under Caliph Umar ibn al-Khattab (RA) in 16 AH, the Hijrah (622 CE) was established as Year 1 of the Islamic calendar.",
                    historicalFactTitle = "Establishment of the Hijri Era",
                    historicalFactDetail = "Caliph Umar (RA) established the Hijri calendar in 16 AH. Hijrah signifies the migration from hardship towards righteousness.",
                    rewardXp = 50,
                    rewardCoins = 15
                ),
                HistoryTask(
                    id = "easy_t1_2",
                    stageId = "easy_stage_1",
                    difficulty = DifficultyLevel.EASY,
                    taskType = TaskType.TACTICAL_DECISION,
                    title = "The First Teacher of Madinah",
                    titleUrdu = "مدینہ منورہ کا پہلا معلم",
                    historicalYear = "621 CE",
                    contextStory = "Before general migration, a wise ambassador was dispatched to teach Quran in Yathrib.",
                    question = "Which companion was dispatched to Yathrib as the very first ambassador and teacher of Islam?",
                    options = listOf(
                        "Hazrat Mus'ab ibn Umayr (RA)",
                        "Hazrat Khalid ibn al-Walid (RA)",
                        "Hazrat Salman al-Farsi (RA)",
                        "Hazrat Abu Dharr (RA)"
                    ),
                    correctIndex = 0,
                    explanation = "Mus'ab ibn Umayr (RA) guided many leaders of Yathrib to Islam prior to the general migration.",
                    historicalFactTitle = "Mus'ab ibn Umayr's Diplomacy",
                    historicalFactDetail = "Hazrat Mus'ab (RA) successfully guided tribal leaders, uniting Aus and Khazraj.",
                    rewardXp = 60,
                    rewardCoins = 20
                )
            )
        ),
        HistoryStage(
            id = "easy_stage_2",
            stageNumber = 2,
            difficulty = DifficultyLevel.EASY,
            title = "Defense of Madinah & Khilafat",
            titleUrdu = "دفاعِ مدینہ اور خلافتِ راشدہ",
            era = "Prophetic & Early Rashidun Era (2 - 13 AH)",
            description = "Experience the legendary military strategies of Ghazwa-e-Khandaq and the first Caliph.",
            requiredStars = 2,
            tasks = listOf(
                HistoryTask(
                    id = "easy_t2_1",
                    stageId = "easy_stage_2",
                    difficulty = DifficultyLevel.EASY,
                    taskType = TaskType.TACTICAL_DECISION,
                    title = "The Strategy of the Trench",
                    titleUrdu = "غزوہ خندق کی حکمتِ عملی",
                    historicalYear = "5 AH / 627 CE",
                    contextStory = "An overwhelming confederate army marched towards Madinah.",
                    question = "Whose Persian military advice did the Prophet ﷺ adopt to protect Madinah from cavalry?",
                    options = listOf(
                        "Hazrat Salman al-Farsi (RA) - Digging a wide trench",
                        "Hazrat Ali (RA) - Direct chariot warfare",
                        "Hazrat Ammar ibn Yasir (RA) - Stone fortresses",
                        "Hazrat Hamza (RA) - Mountain ambush"
                    ),
                    correctIndex = 0,
                    explanation = "Salman al-Farsi (RA) advised digging a deep trench, neutralizing enemy cavalry.",
                    historicalFactTitle = "Ghazwa-e-Khandaq",
                    historicalFactDetail = "The trench technique protected the oasis from superior numbers.",
                    rewardXp = 60,
                    rewardCoins = 20
                )
            )
        ),

        // ================= MEDIUM STAGES =================
        HistoryStage(
            id = "medium_stage_1",
            stageNumber = 3,
            difficulty = DifficultyLevel.MEDIUM,
            title = "Yarmouk & Liberation of Al-Quds",
            titleUrdu = "یرموک اور فتحِ بیت المقدس",
            era = "Rashidun Caliphate (15 - 16 AH / 636 - 637 CE)",
            description = "The historic battle that ended Byzantine rule in the Levant and Caliph Umar's peaceful entry into Jerusalem.",
            requiredStars = 4,
            tasks = listOf(
                HistoryTask(
                    id = "med_t1_1",
                    stageId = "medium_stage_1",
                    difficulty = DifficultyLevel.MEDIUM,
                    taskType = TaskType.TACTICAL_DECISION,
                    title = "The Tactical Genius at Yarmouk",
                    titleUrdu = "معرکہ یرموک میں خالد بن ولید کا تدبر",
                    historicalYear = "636 CE / 15 AH",
                    contextStory = "At the Yarmouk gorge, 30,000 Muslims faced a large Byzantine imperial army.",
                    question = "Which military commander utilized mobile cavalry reserves to deliver the decisive flanking blow?",
                    options = listOf(
                        "Hazrat Khalid ibn al-Walid (RA)",
                        "Hazrat Amr ibn al-Aas (RA)",
                        "Hazrat Yazid ibn Abi Sufyan (RA)",
                        "Hazrat Shurahbil (RA)"
                    ),
                    correctIndex = 0,
                    explanation = "Khalid ibn al-Walid (RA) used mobile cavalry to encircle enemy flanks.",
                    historicalFactTitle = "Battle of Yarmouk",
                    historicalFactDetail = "A masterclass in cavalry tactics and strategic discipline.",
                    rewardXp = 90,
                    rewardCoins = 30
                )
            )
        ),
        HistoryStage(
            id = "medium_stage_2",
            stageNumber = 4,
            difficulty = DifficultyLevel.MEDIUM,
            title = "Conquest of Andalusia & Umayyads",
            titleUrdu = "فتحِ اندلس اور بنو امیہ کا دور",
            era = "Umayyad Caliphate (92 AH / 711 CE)",
            description = "Tariq ibn Ziyad's landing in Spain and eight centuries of civilization.",
            requiredStars = 8,
            tasks = listOf(
                HistoryTask(
                    id = "med_t2_1",
                    stageId = "medium_stage_2",
                    difficulty = DifficultyLevel.MEDIUM,
                    taskType = TaskType.MULTIPLE_CHOICE,
                    title = "The Rock of Gibraltar",
                    titleUrdu = "جبلِ طارق کا نام اور فاتح",
                    historicalYear = "711 CE / 92 AH",
                    contextStory = "A young general crossed the straits between North Africa and Spain.",
                    question = "Who was this general whose landing site Jabal Tariq was named after him?",
                    options = listOf(
                        "Tariq ibn Ziyad",
                        "Abd al-Rahman I",
                        "Uqba ibn Nafi",
                        "Muhammad ibn al-Qasim"
                    ),
                    correctIndex = 0,
                    explanation = "Tariq ibn Ziyad landed at Jabal Tariq (Gibraltar) and defeated King Roderic.",
                    historicalFactTitle = "Tariq ibn Ziyad in Andalusia",
                    historicalFactDetail = "Sparked centuries of Islamic science, medicine, and architecture in Spain.",
                    rewardXp = 90,
                    rewardCoins = 30
                )
            )
        ),

        // ================= HARD STAGES =================
        HistoryStage(
            id = "hard_stage_1",
            stageNumber = 5,
            difficulty = DifficultyLevel.HARD,
            title = "House of Wisdom & Golden Age Science",
            titleUrdu = "بیت الحکمہ اور سنہری دور کی دریافتیں",
            era = "Abbasid Caliphate (8th - 11th Century CE)",
            description = "Discover the pioneers of Algebra, optics, and experimental sciences in Baghdad.",
            requiredStars = 14,
            tasks = listOf(
                HistoryTask(
                    id = "hard_t1_1",
                    stageId = "hard_stage_1",
                    difficulty = DifficultyLevel.HARD,
                    taskType = TaskType.MULTIPLE_CHOICE,
                    title = "The Father of Modern Algebra",
                    titleUrdu = "الجبر کا بانی اور موجد",
                    historicalYear = "c. 820 CE",
                    contextStory = "In Baghdad's Bayt al-Hikmah, an astronomer published Kitab al-Jabr wa-l-Muqabala.",
                    question = "Whose name gives us the term 'Algorithm' and whose treatise invented Algebra?",
                    options = listOf(
                        "Muhammad ibn Musa al-Khwarizmi",
                        "Al-Kindi",
                        "Al-Farabi",
                        "Omar Khayyam"
                    ),
                    correctIndex = 0,
                    explanation = "Al-Khwarizmi formulated algebra as an independent mathematical discipline.",
                    historicalFactTitle = "Muhammad ibn Musa al-Khwarizmi",
                    historicalFactDetail = "Introduced decimal numeral systems and zero to world mathematics.",
                    rewardXp = 120,
                    rewardCoins = 40
                )
            )
        ),

        // ================= VERY HARD STAGES =================
        HistoryStage(
            id = "very_hard_stage_2",
            stageNumber = 8,
            difficulty = DifficultyLevel.VERY_HARD,
            title = "Conquest of Constantinople (1453 CE)",
            titleUrdu = "فتحِ قسطنطنیہ اور سلطنتِ عثمانیہ",
            era = "Ottoman Imperial Era (857 AH / 1453 CE)",
            description = "The fulfillment of prophetic glad tidings: the 53-day siege and ships hauled overland.",
            requiredStars = 24,
            tasks = listOf(
                HistoryTask(
                    id = "vhard_t2_1",
                    stageId = "very_hard_stage_2",
                    difficulty = DifficultyLevel.VERY_HARD,
                    taskType = TaskType.TACTICAL_DECISION,
                    title = "Overland Fleet Maneuver",
                    titleUrdu = "خشکی پر بحری جہاز دوڑانے کا تاریخی کارنامہ",
                    historicalYear = "1453 CE / 857 AH",
                    contextStory = "The Byzantine chain across the Golden Horn blocked the Ottoman navy.",
                    question = "How did 21-year-old Sultan Mehmed II overcome the impenetrable Golden Horn chain barrier?",
                    options = listOf(
                        "Transported over 70 galleys overland across greased wooden logs over the hills overnight",
                        "Blew up the harbor chain with torpedoes",
                        "Dug a canal connecting Black Sea to Marmara",
                        "Disguised naval ships as Venetian merchants"
                    ),
                    correctIndex = 0,
                    explanation = "On April 22, 1453, Ottoman ships were hauled over a mile of land on greased timber roads under cover of night.",
                    historicalFactTitle = "The Galata Overland Fleet",
                    historicalFactDetail = "Forced defenders to divide forces, leading to the conquest of Constantinople.",
                    rewardXp = 220,
                    rewardCoins = 75
                )
            )
        )
    )

    val dailyQuestPool: List<HistoryTask> = listOf(
        HistoryTask(
            id = "daily_1",
            stageId = "daily",
            difficulty = DifficultyLevel.MEDIUM,
            taskType = TaskType.MULTIPLE_CHOICE,
            title = "The Compiler of the Standard Mushaf",
            titleUrdu = "جامع القرآن: حضرت عثمان غنی رضی اللہ عنہ",
            historicalYear = "25 AH / 646 CE",
            contextStory = "As Islam spread across continents, unified copies of the Quran were sent to regional capitals.",
            question = "Which Caliph ordered scribes under Zayd ibn Thabit to produce master copies of the Holy Quran?",
            options = listOf(
                "Hazrat Uthman ibn Affan (RA)",
                "Hazrat Ali ibn Abi Talib (RA)",
                "Hazrat Abu Bakr As-Siddiq (RA)",
                "Hazrat Umar ibn al-Khattab (RA)"
            ),
            correctIndex = 0,
            explanation = "Caliph Uthman (RA) unified the Muslim world under the standardized Uthmanic codex.",
            historicalFactTitle = "Compilation of the Uthmanic Mushaf",
            historicalFactDetail = "Preserved the exact orthography and recitation of the Holy Quran.",
            rewardXp = 150,
            rewardCoins = 50
        )
    )

    val archiveFacts: List<HistoryArchiveFact> = listOf(
        HistoryArchiveFact(
            id = "fact_hijrah",
            title = "The Hijrah to Madinah",
            titleUrdu = "ہجرتِ مدینہ منورہ (622ء)",
            era = "Prophetic Era",
            year = "622 CE / 1 AH",
            keyFigure = "Prophet Muhammad ﷺ & Abu Bakr (RA)",
            summary = "The migration from Makkah to Yathrib established the first Islamic state governed by divine justice.",
            historicalSignificance = "Instituted Year 1 of the Islamic calendar and the Constitution of Madinah."
        ),
        HistoryArchiveFact(
            id = "fact_yarmouk",
            title = "The Battle of Yarmouk",
            titleUrdu = "معرکہ یرموک (15ھ)",
            era = "Khilafat-e-Rashidah",
            year = "636 CE / 15 AH",
            keyFigure = "Khalid ibn al-Walid (RA)",
            summary = "A masterclass in mobile cavalry tactics where Muslims decisively defeated the Byzantine army.",
            historicalSignificance = "Opened the Levant and paved the way for the peaceful liberation of Jerusalem."
        )
    )
}
