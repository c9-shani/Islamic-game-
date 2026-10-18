package com.example.data

import com.example.R
import com.example.model.IslamicMission
import com.example.model.MiniIslamicQuiz
import com.example.model.MissionDialogue
import com.example.model.NpcCitizen
import com.example.model.SpeakerType

object IslamicMissionRepository {

    val npcs: List<NpcCitizen> = listOf(
        NpcCitizen(
            id = "npc_ahmad",
            nameUrdu = "تاجر احمد (Shopkeeper Ahmad)",
            nameEnglish = "Merchant Ahmad",
            roleUrdu = "بازار کا تاجر (Spice Merchant)",
            roleEnglish = "Market Merchant",
            posX = 45f,
            posY = 55f,
            avatarDrawableRes = R.drawable.scholar_cartoon_3d,
            greetingSalamUrdu = "وعلیکم السلام و رحمۃ اللہ پیارے زید بھائی! ماشاءاللہ تم نے سلام میں پہل کر کے سنت ادا کی۔",
            salamReplyEnglish = "Wa Alaikum Assalam brother Zayd! MashaAllah you gained great reward by initiating the greeting.",
            namazAdviceResponseUrdu = "جزاک اللہ خیراً یاد دلانے کا! واقعی مؤذن اذان دینے والا ہے۔ میں فوراً دکان بند کر کے مسجد چلتا ہوں۔",
            namazAdviceResponseEnglish = "JazakAllahu Khairan for the reminder! The Adhan is approaching, I will close shop and walk to the mosque.",
            islamicWisdomUrdu = "رسول اللہ ﷺ نے فرمایا: جو سلام میں پہل کرے وہ تکبر سے بری ہے۔",
            islamicWisdomEnglish = "The Prophet ﷺ said: 'The one who initiates the Salam is free from pride.'",
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_salam",
                questionUrdu = "اسلام میں ملاقات کے وقت سنت طریقہ کیا ہے؟",
                questionEnglish = "What is the Sunnah practice when meeting a fellow Muslim?",
                options = listOf(
                    "السلام علیکم و رحمۃ اللہ کہنا اور مسکرانا",
                    "خاموشی سے گزر جانا",
                    "صرف ہاتھ ہلانا",
                    "نظریں چرا لینا"
                ),
                correctIndex = 0,
                explanationUrdu = "سلام محبت اور امن کی دعا ہے۔ نبی کریم ﷺ نے فرمایا: تم آپس میں سلام کو عام کرو تاکہ محبت بڑھے۔",
                rewardHasanat = 50,
                rewardXp = 30
            )
        ),

        NpcCitizen(
            id = "npc_farooq",
            nameUrdu = "بزرگ فاروق (Elder Farooq)",
            nameEnglish = "Elder Farooq",
            roleUrdu = "مسجد کا خادم و بزرگ (Elder at Fountain)",
            roleEnglish = "Mosque Elder",
            posX = 70f,
            posY = 68f,
            avatarDrawableRes = R.drawable.scholar_cartoon_3d,
            greetingSalamUrdu = "وعلیکم السلام بیٹا! اللہ تمہاری زندگی میں برکت دے۔",
            salamReplyEnglish = "Wa Alaikum Assalam my son! May Allah bless your life with righteousness.",
            namazAdviceResponseUrdu = "ماشاءاللہ! نماز دین کا ستون ہے۔ جو باجماعت نماز کی پابندی کرتا ہے، اللہ اس کے چہرے پر نور عطا فرماتا ہے۔",
            namazAdviceResponseEnglish = "MashaAllah! Prayer is the pillar of faith. He who guards congregational prayer receives divine illumination.",
            islamicWisdomUrdu = "نماز مومن کی معراج اور آنکھوں کی ٹھنڈک ہے۔ وقت پر نماز ادا کرنا اللہ کو سب سے زیادہ محبوب ہے۔",
            islamicWisdomEnglish = "Prayer is the ascension of the believer and comfort of the eyes. Offering prayer on time is most beloved to Allah.",
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_namaz",
                questionUrdu = "باجماعت نماز کا ثواب اکیلے نماز پڑھنے سے کتنا زیادہ ہے؟",
                questionEnglish = "How many times greater is the reward of praying in congregation (Jama'at) compared to praying alone?",
                options = listOf(
                    "27 درجے زیادہ (27 times more)",
                    "صرف 2 درجے زیادہ",
                    "10 درجے زیادہ",
                    "برابر ثواب ہے"
                ),
                correctIndex = 0,
                explanationUrdu = "صحیح بخاری کی حدیث ہے کہ باجماعت نماز تنہا نماز سے 27 درجے زیادہ فضیلت رکھتی ہے۔",
                rewardHasanat = 60,
                rewardXp = 40
            )
        ),

        NpcCitizen(
            id = "npc_haroon",
            nameUrdu = "مسافر ہارون (Traveler Haroon)",
            nameEnglish = "Traveler Haroon",
            roleUrdu = "صحرا کا مسافر (Desert Traveler)",
            roleEnglish = "Desert Traveler at Oasis",
            posX = 78f,
            posY = 74f,
            avatarDrawableRes = R.drawable.hero_cartoon_3d,
            greetingSalamUrdu = "وعلیکم السلام نوجوان! میں ایک طویل سفر سے پیاسا آ رہا ہوں۔",
            salamReplyEnglish = "Wa Alaikum Assalam young man! I have arrived from a long desert journey and feel very thirsty.",
            namazAdviceResponseUrdu = "اللہ کا شکر ہے! میں بھی وضو کر کے ظہر کی نماز جماعت کے ساتھ ادا کروں گا۔",
            namazAdviceResponseEnglish = "Praise be to Allah! I will perform Wudu and join the Dhuhr prayer in congregation.",
            islamicWisdomUrdu = "پانی پینے کی سنت: بیٹھ کر پینا، دائیں ہاتھ سے پینا، بسم اللہ پڑھنا، تین سانسوں میں پینا اور آخر میں الحمد للہ کہنا۔",
            islamicWisdomEnglish = "Sunnah of drinking water: Sit down, hold with right hand, say Bismillah, drink in 3 sips, and conclude with Alhamdulillah.",
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_water",
                questionUrdu = "سنت نبوی ﷺ کے مطابق پانی پینے کا درست طریقہ کیا ہے؟",
                questionEnglish = "According to the Sunnah, what is the proper manner of drinking water?",
                options = listOf(
                    "بیٹھ کر، دائیں ہاتھ سے اور تین سانسوں میں پینا",
                    "کھڑے ہو کر ایک ہی سانس میں پینا",
                    "بائیں ہاتھ سے جلدی جلدی پینا",
                    "بغیر بسم اللہ کے پینا"
                ),
                correctIndex = 0,
                explanationUrdu = "نبی کریم ﷺ نے کھڑے ہو کر پانی پینے سے منع فرمایا اور تین سانسوں میں ٹھہر ٹھہر کر پینے کا حکم دیا۔",
                rewardHasanat = 50,
                rewardXp = 30
            )
        ),

        NpcCitizen(
            id = "npc_maryam",
            nameUrdu = "عالمہ مریم (Scholar Maryam)",
            nameEnglish = "Scholar Maryam",
            roleUrdu = "دار الحکمت کی محقق (Library Scholar)",
            roleEnglish = "Astronomer & Scholar",
            posX = 26f,
            posY = 42f,
            avatarDrawableRes = R.drawable.companion_cartoon_3d,
            greetingSalamUrdu = "وعلیکم السلام و رحمۃ اللہ زید! دیکھو بیت الحکمہ میں علوم و حکمت کے خزانے کھلے ہیں۔",
            salamReplyEnglish = "Wa Alaikum Assalam Zayd! Look at the treasures of science and faith here in Bayt al-Hikmah.",
            namazAdviceResponseUrdu = "علم کی طلب بھی عبادت ہے، اور نماز تو تمام عبادات کی روح ہے۔ چلو اذان کے ساتھ ہی پہلی صف میں چلتے ہیں۔",
            namazAdviceResponseEnglish = "Seeking knowledge is worship, and prayer is the soul of all devotions. Let us join the first row.",
            islamicWisdomUrdu = "طلب العلم فريضة على كل مسلم - علم حاصل کرنا ہر مسلمان پر فرض ہے۔",
            islamicWisdomEnglish = "'Seeking knowledge is an obligation upon every Muslim' - Hadith of Prophet Muhammad ﷺ.",
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_ilm",
                questionUrdu = "قرآن مجید کی سب سے پہلی نازل ہونے والی آیت کا پہلا لفظ کیا ہے؟",
                questionEnglish = "What is the very first word revealed in the Holy Quran?",
                options = listOf(
                    "اِقْرَأْ (پڑھیے - Read)",
                    "اَلْحَمْدُ (تمام تعریفیں)",
                    "قُلْ (کہہ دیجیے)",
                    "یٰۤاَیُّهَا (اے لوگو)"
                ),
                correctIndex = 0,
                explanationUrdu = "غارِ حرا میں سب سے پہلی وحی سورۃ العلق کی ابتدائی آیات تھیں: 'اقْرَأْ بِاسْمِ رَبِّكَ الَّذِي خَلَقَ' (پڑھیے اپنے رب کے نام سے جس نے پیدا کیا)۔",
                rewardHasanat = 70,
                rewardXp = 50
            )
        )
    )

    val missions: List<IslamicMission> = listOf(
        // Mission 1: Salam & Call to Prayer in the Bazaar
        IslamicMission(
            id = "mission_1",
            missionNumber = 1,
            titleUrdu = "مشن 1: سلام اور نماز کی دعوت",
            titleEnglish = "Mission 1: Salam & Call to Prayer",
            objectiveUrdu = "بازار جائیں، تاجر احمد کو سلام کریں اور نمازِ ظہر کی دعوت دیں",
            objectiveEnglish = "Head to the Bazaar, greet Merchant Ahmad with Salam and invite him to Dhuhr Prayer",
            targetNpcId = "npc_ahmad",
            targetLocationName = "بازارِ تجارت (Old Market Bazaar)",
            targetX = 45f,
            targetY = 55f,
            dialogues = listOf(
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "السلام علیکم و رحمۃ اللہ وبرکاتہ احمد بھائی! اللہ آپ کے رزق میں برکت دے۔",
                    speechEnglish = "Assalamu Alaikum wa Rahmatullahi wa Barakatuh brother Ahmad! May Allah bless your business.",
                    voiceSpokenText = "Assalamu Alaikum wa Rahmatullahi wa Barakatuh brother Ahmad! May Allah bless your sustenance."
                ),
                MissionDialogue(
                    speakerName = "تاجر احمد (Merchant Ahmad)",
                    speakerType = SpeakerType.NPC,
                    speechUrdu = "وعلیکم السلام و رحمۃ اللہ پیارے زید! ماشاءاللہ کتنی پیاری سنت ادا کی تم نے۔",
                    speechEnglish = "Wa Alaikum Assalam dear Zayd! MashaAllah you practiced the blessed Sunnah of Salam.",
                    voiceSpokenText = "Wa Alaikum Assalam dear Zayd! MashaAllah what a beautiful greeting."
                ),
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "احمد بھائی! مسجد سے اذان کی آواز گونجنے والی ہے۔ رسول اللہ ﷺ نے فرمایا کہ باجماعت نماز کا ثواب اکیلے سے 27 درجے زیادہ ہے۔ چلیے ساتھ مسجد چلتے ہیں!",
                    speechEnglish = "Brother Ahmad! The Adhan will be called soon. The Prophet ﷺ said congregational prayer carries 27 times more reward. Let us walk together to the mosque!",
                    voiceSpokenText = "Brother Ahmad! The call to prayer is near. The reward of congregational prayer is 27 times greater. Let us go to the mosque!"
                ),
                MissionDialogue(
                    speakerName = "تاجر احمد (Merchant Ahmad)",
                    speakerType = SpeakerType.NPC,
                    speechUrdu = "اللہ تمہیں جزائے خیر دے زید! میں ابھی دکان بند کرتا ہوں اور وضو کر کے مسجد کی طرف روانہ ہوتا ہوں۔ تم نے مجھے بہترین نصیحت کی۔",
                    speechEnglish = "May Allah reward you Zayd! I will close my stall immediately, perform Wudu, and walk with you to the mosque.",
                    voiceSpokenText = "May Allah reward you Zayd! I will close my stall and walk to the mosque with you."
                )
            ),
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_m1",
                questionUrdu = "رسول اللہ ﷺ کے فرمان کے مطابق اسلام میں سب سے افضل عمل کیا ہے؟",
                questionEnglish = "According to the Hadith, what is one of the most beloved deeds to Allah?",
                options = listOf(
                    "وقت پر نماز ادا کرنا (Prayer offered at its proper time)",
                    "زیادہ دولت جمع کرنا",
                    "صرف باتیں کرنا",
                    "بغیر علم کے بحث کرنا"
                ),
                correctIndex = 0,
                explanationUrdu = "رسول اللہ ﷺ سے دریافت کیا گیا کہ کون سا عمل سب سے افضل ہے؟ آپ ﷺ نے فرمایا: 'الصَّلَاةُ عَلَى وَقْتِهَا' (اپنے وقت پر نماز ادا کرنا)۔",
                rewardHasanat = 100,
                rewardXp = 80
            ),
            rewardHasanat = 150,
            rewardXp = 200
        ),

        // Mission 2: Thirsty Traveler & Manners of Drinking
        IslamicMission(
            id = "mission_2",
            missionNumber = 2,
            titleUrdu = "مشن 2: مسافر کی پیاس اور پینے کے آداب",
            titleEnglish = "Mission 2: Thirsty Traveler & Manners of Drinking",
            objectiveUrdu = "واحۂ مدینہ کے کنویں پر جائیں اور تھکے ہوئے مسافر ہارون کو پانی پلا کر سنت سکھائیں",
            objectiveEnglish = "Walk to the Oasis Well, offer water to Traveler Haroon and teach Sunnah drinking manners",
            targetNpcId = "npc_haroon",
            targetLocationName = "کنواں و نخلستان (Oasis Well)",
            targetX = 78f,
            targetY = 74f,
            dialogues = listOf(
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "السلام علیکم محترم مسافر! آپ تھکے ہوئے لگتے ہیں۔ یہ ٹھنڈا پانی نوش فرمائیں۔",
                    speechEnglish = "Assalamu Alaikum honored traveler! You seem weary from the desert. Please drink this cool water.",
                    voiceSpokenText = "Assalamu Alaikum traveler! Please take this cool fresh water."
                ),
                MissionDialogue(
                    speakerName = "مسافر ہارون (Traveler Haroon)",
                    speakerType = SpeakerType.NPC,
                    speechUrdu = "وعلیکم السلام پیارے بیٹے! اللہ تیری پیاس کو حوضِ کوثر سے بجھائے۔ میں جلدی میں کھڑے ہو کر ہی پی لیتا ہوں۔",
                    speechEnglish = "Wa Alaikum Assalam noble boy! May Allah quench your thirst from Hawd al-Kawthar. I was about to drink standing in haste.",
                    voiceSpokenText = "Wa Alaikum Assalam! Thank you. I was about to drink standing up."
                ),
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "چچا جان! ہمارے پیارے نبی ﷺ کی پیاری سنت ہے کہ بیٹھ کر، دائیں ہاتھ سے، بسم اللہ کہہ کر اور تین سانسوں میں پانی پیا جائے۔ اس سے جسم کو راحت اور دل کو سکون ملتا ہے۔",
                    speechEnglish = "Uncle! The beautiful Sunnah of our Prophet ﷺ is to sit down, hold with the right hand, say Bismillah, and drink in three calming sips. It brings health and peace.",
                    voiceSpokenText = "Uncle! It is the Prophet's Sunnah to sit down, say Bismillah, and drink calmly in three sips."
                ),
                MissionDialogue(
                    speakerName = "مسافر ہارون (Traveler Haroon)",
                    speakerType = SpeakerType.NPC,
                    speechUrdu = "الحمد للہ! سبحان اللہ، کتنی پیاری سنت سکھائی تم نے۔ میں اب ہمیشہ اسی طریقے سے پانی پیوں گا۔",
                    speechEnglish = "Alhamdulillah! SubhanAllah, what an uplifting Sunnah you taught me. I will uphold this practice forever.",
                    voiceSpokenText = "Alhamdulillah! Thank you Zayd. I will always follow this Sunnah."
                )
            ),
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_m2",
                questionUrdu = "پانی پلانے والے کو پانی پیتے وقت سب سے آخر میں پینا چاہیے۔ یہ کیا ہے؟",
                questionEnglish = "The one serving water to others should drink last. What is this?",
                options = listOf(
                    "رسول اللہ ﷺ کی خوبصورت سنت (Sunnah of Prophet Muhammad ﷺ)",
                    "ایک پرانی رسم",
                    "کوئی خاص بات نہیں",
                    "صرف ایک قانون"
                ),
                correctIndex = 0,
                explanationUrdu = "حدیثِ مبارکہ میں آتا ہے: 'سَاقِي الْقَوْمِ آخِرُهُمْ شُرْبًا' (لوگوں کو پلانے والا سب سے آخر میں پیے)۔",
                rewardHasanat = 120,
                rewardXp = 90
            ),
            rewardHasanat = 180,
            rewardXp = 220
        ),

        // Mission 3: Purification and Wudu at the Fountain
        IslamicMission(
            id = "mission_3",
            missionNumber = 3,
            titleUrdu = "مشن 3: وضو اور طہارت کا نور",
            titleEnglish = "Mission 3: Purification & Wudu at the Fountain",
            objectiveUrdu = "جامع مسجد کے فوارے پر جائیں اور بزرگ فاروق سے وضو کے فضائل اور درست ترتیب سیکھیں",
            objectiveEnglish = "Walk to the Grand Mosque Fountain, meet Elder Farooq and review the virtues and steps of Wudu",
            targetNpcId = "npc_farooq",
            targetLocationName = "حوضِ مسجد (Mosque Courtyard Fountain)",
            targetX = 70f,
            targetY = 68f,
            dialogues = listOf(
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "السلام علیکم چچا فاروق! میں نماز کی تیاری کے لیے وضو کرنے آیا ہوں۔ کیا آپ مجھے وضو کی روحانی فضیلت بتائیں گے؟",
                    speechEnglish = "Assalamu Alaikum Uncle Farooq! I have come to make Wudu for prayer. Could you share the spiritual blessing of purification?",
                    voiceSpokenText = "Assalamu Alaikum Uncle Farooq! Please share the blessings of Wudu with me."
                ),
                MissionDialogue(
                    speakerName = "بزرگ فاروق (Elder Farooq)",
                    speakerType = SpeakerType.ELDER,
                    speechUrdu = "وعلیکم السلام میرے بچے! جب ایک مومن وضو کرتا ہے اور چہرہ دھوتا ہے تو اس کی آنکھوں کے گناہ پانی کے قطروں کے ساتھ جھڑ جاتے ہیں، ہاتھ دھوتا ہے تو ہاتھوں کے گناہ معاف ہو جاتے ہیں۔",
                    speechEnglish = "Wa Alaikum Assalam my child! When a believer performs Wudu, as water washes the face, hands, and feet, sins drop away with the falling droplets of water.",
                    voiceSpokenText = "Wa Alaikum Assalam! When you perform Wudu with sincerity, your sins wash away with the droplets of water."
                ),
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "سبحان اللہ! اور قیامت کے دن رسول اللہ ﷺ اپنی امت کو وضو کے چمکتے ہوئے اعضاء کے نور سے پہچانیں گے!",
                    speechEnglish = "SubhanAllah! And on the Day of Judgment, our Prophet ﷺ will recognize his Ummah by the radiant illumination of their Wudu limbs!",
                    voiceSpokenText = "SubhanAllah! On the Day of Judgment, the Prophet will recognize believers by the radiant light of their Wudu!"
                ),
                MissionDialogue(
                    speakerName = "بزرگ فاروق (Elder Farooq)",
                    speakerType = SpeakerType.ELDER,
                    speechUrdu = "ماشاءاللہ! تمہارا علم روشن ہے۔ اب بسم اللہ پڑھ کر وضو مکمل کرو اور مسجد میں داخل ہو جاؤ۔",
                    speechEnglish = "MashaAllah! Your knowledge shines brightly. Say Bismillah, complete your Wudu with excellence, and enter the sanctuary.",
                    voiceSpokenText = "MashaAllah! Say Bismillah, complete your Wudu, and enter the mosque in peace."
                )
            ),
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_m3",
                questionUrdu = "وضو کے بعد کلمۂ شہادت پڑھنے والے کے لیے کیا خوشخبری ہے؟",
                questionEnglish = "What glad tidings did the Prophet ﷺ give to the one who completes Wudu and recites the Shahada?",
                options = listOf(
                    "جنت کے آٹھوں دروازے اس کے لیے کھول دیے جاتے ہیں (All 8 gates of Paradise open)",
                    "اس کا قد لمبا ہو جاتا ہے",
                    "اسے خزانہ مل جاتا ہے",
                    "وہ بغیر امتحان پاس ہو جاتا ہے"
                ),
                correctIndex = 0,
                explanationUrdu = "صحیح مسلم کی حدیث ہے کہ جو شخص کامل وضو کرے اور پھر کہے 'أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللَّهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ' تو اس کے لیے جنت کے آٹھوں دروازے کھول دیے جاتے ہیں۔",
                rewardHasanat = 140,
                rewardXp = 100
            ),
            rewardHasanat = 200,
            rewardXp = 250
        ),

        // Mission 4: The Grand Library & Quranic Knowledge
        IslamicMission(
            id = "mission_4",
            missionNumber = 4,
            titleUrdu = "مشن 4: بیت الحکمہ اور قرآن کی عظمت",
            titleEnglish = "Mission 4: Bayt al-Hikmah & Grand Quranic Wisdom",
            objectiveUrdu = "دار الحکمت جائیں اور عالمہ مریم کے ساتھ قرآنی سائنس اور علم کا چیلنج مکمل کریں",
            objectiveEnglish = "Visit Grand Bayt al-Hikmah and complete the Quranic knowledge quest with Scholar Maryam",
            targetNpcId = "npc_maryam",
            targetLocationName = "بیت الحکمہ (House of Wisdom Library)",
            targetX = 26f,
            targetY = 42f,
            dialogues = listOf(
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "السلام علیکم مریم! کتب خانے میں کون سا تاریخی اور قرآنی تحقیق کا کام ہو رہا ہے؟",
                    speechEnglish = "Assalamu Alaikum Maryam! What monumental research is taking place in the library today?",
                    voiceSpokenText = "Assalamu Alaikum Maryam! What inspiring knowledge are you studying today?"
                ),
                MissionDialogue(
                    speakerName = "عالمہ مریم (Scholar Maryam)",
                    speakerType = SpeakerType.NPC,
                    speechUrdu = "وعلیکم السلام زید! قرآن مجید نے چودہ سو سال قبل کائنات کی تخلیق، ستاروں کے مدار اور سمندروں کے پردے کے بارے میں جو بتایا تھا، سائنس آج اس کی تصدیق کر رہی ہے۔",
                    speechEnglish = "Wa Alaikum Assalam Zayd! The Quran described celestial orbits, the expanding universe, and oceanic barriers 1400 years ago, which modern science validates.",
                    voiceSpokenText = "Wa Alaikum Assalam Zayd! The Holy Quran described celestial orbits and nature with miraculous precision."
                ),
                MissionDialogue(
                    speakerName = "زید (Zayd)",
                    speakerType = SpeakerType.ZAYD,
                    speechUrdu = "بے شک! قرآن مجید کائنات کی سب سے بڑی کتابِ ہدایت ہے۔ اس کا ہر لفظ حق اور سچ ہے۔",
                    speechEnglish = "Without doubt! The Holy Quran is the supreme book of guidance for humanity. Every word is eternal truth.",
                    voiceSpokenText = "Indeed! The Quran is the supreme book of guidance. Every letter brings 10 Hasanat."
                )
            ),
            miniQuiz = MiniIslamicQuiz(
                id = "quiz_m4",
                questionUrdu = "قرآن مجید کے ایک حرف کی تلاوت پر کتنی نیکیوں (Hasanat) کا وعدہ ہے؟",
                questionEnglish = "How many rewards (Hasanat) are recorded for reciting each single letter of the Holy Quran?",
                options = listOf(
                    "10 نیکیاں (10 Hasanat per letter)",
                    "صرف 1 نیکی",
                    "5 نیکیاں",
                    "100 نیکیاں"
                ),
                correctIndex = 0,
                explanationUrdu = "جامع ترمذی کی حدیث ہے: رسول اللہ ﷺ نے فرمایا کہ جو قرآن کا ایک حرف پڑھے اسے ایک نیکی ملے گی اور ایک نیکی کا ثواب دس گنا ہوتا ہے۔ میں نہیں کہتا کہ 'الـم' ایک حرف ہے بلکہ 'الف' ایک حرف، 'لام' ایک حرف اور 'میم' ایک حرف ہے۔",
                rewardHasanat = 150,
                rewardXp = 100
            ),
            rewardHasanat = 220,
            rewardXp = 280
        )
    )
}
