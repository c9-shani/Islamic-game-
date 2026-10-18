package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.GameProgressManager
import com.example.data.HistoryRepository
import com.example.model.HistoryStage
import com.example.model.UserProfile
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import com.example.ui.theme.NoorCyan
import com.example.util.HapticHelper
import com.example.voice.GameVoiceEngine

data class HistoricalCityLocation(
    val id: String,
    val nameEn: String,
    val nameUr: String,
    val era: String,
    val highlights: String,
    val details: String,
    val stageId: String,
    val pinColor: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldMapScreen(
    profile: UserProfile,
    gameManager: GameProgressManager,
    onStartStageTask: (HistoryStage) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler { onBack() }

    val voiceEngine = remember { GameVoiceEngine(context) }
    DisposableEffect(Unit) {
        onDispose {
            voiceEngine.shutdown()
        }
    }

    val locations = remember {
        listOf(
            HistoricalCityLocation(
                id = "makkah",
                nameEn = "Makkah Al-Mukarramah",
                nameUr = "مکہ مکرمہ",
                era = "610 CE • نبوت و ہجرت",
                highlights = "کعبہ معظمہ، آغازِ وحی، صبر و استقامت",
                details = "مکہ مکرمہ اسلام کا مقدس ترین شہر ہے جہاں نبی اکرم ﷺ پر غارِ حرا میں پہلی وحی نازل ہوئی۔ یہاں سے 622 عیسوی میں مدینہ منورہ کی تاریخی ہجرت ہوئی۔",
                stageId = "easy_stage_1",
                pinColor = GoldAccent
            ),
            HistoricalCityLocation(
                id = "madinah",
                nameEn = "Madinah Al-Munawwarah",
                nameUr = "مدینہ منورہ",
                era = "622 CE • پہلی اسلامی ریاست",
                highlights = "مسجد نبوی، انصار و مہاجرین کی مواخات، ریاستِ مدینہ",
                details = "مدینہ منورہ میں تاریخِ اسلام کی پہلی ریاست قائم ہوئی، جہاں باہمی بھائی چارہ، عدل اور آئینِ مدینہ نے تاریخ انسانی کو بدل دیا۔",
                stageId = "easy_stage_2",
                pinColor = Color(0xFF66BB6A)
            ),
            HistoricalCityLocation(
                id = "alquds",
                nameEn = "Al-Quds / Jerusalem",
                nameUr = "القدس (بیت المقدس)",
                era = "637 CE • عہدِ فاروقی",
                highlights = "مسجد اقصی، حضرت عمر فاروق رضی اللہ عنہ کا معاہدہ امن",
                details = "خلیفہ دوم امیر المؤمنین حضرت عمر فاروق رضی اللہ عنہ کے دور میں بیت المقدس پرامن طریقے سے اسلامی قلمرو میں شامل ہوا اور اہل ایلیا کو تاریخی امان نامہ دیا گیا۔",
                stageId = "medium_stage_1",
                pinColor = NoorCyan
            ),
            HistoricalCityLocation(
                id = "damascus",
                nameEn = "Damascus",
                nameUr = "دمشق",
                era = "661 CE • اموی دور خلافت",
                highlights = "جامع اموی، حضرت خالد بن ولید کی تدابیر، فن تعمیر",
                details = "دمشق اسلامی فتوحات اور اموی خلافت کا مرکزی پایہ تخت بنا۔ حضرت خالد بن ولید نے شاندار حکمت عملی سے دمشق اور معرکہ یرموک میں قیادت فرمائی۔",
                stageId = "medium_stage_2",
                pinColor = Color(0xFF42A5F5)
            ),
            HistoricalCityLocation(
                id = "baghdad",
                nameEn = "Baghdad",
                nameUr = "دار السلام بغداد",
                era = "762 - 1258 CE • سنہری دور",
                highlights = "بیت الحکمہ (House of Wisdom)، ریاضی و فلکیات، الخوارزمی",
                details = "عباسی دور میں بغداد دنیا کا سب سے بڑا علمی و سائنسی مرکز بنا جہاں بیت الحکمہ میں دنیا بھر کے علوم کا عربی میں ترجمہ ہوا اور الجبرا ایجاد ہوا۔",
                stageId = "hard_stage_1",
                pinColor = Color(0xFFFFB74D)
            ),
            HistoricalCityLocation(
                id = "cordoba",
                nameEn = "Cordoba / Andalusia",
                nameUr = "قرطبہ (اندلس)",
                era = "711 - 1031 CE • علم و طب کا گہوارہ",
                highlights = "طارق بن زیاد، جامع قرطبہ، ابو القاسم الزہراوی کی جراحی",
                details = "اندلس میں مسلمانوں نے 800 سال تک ایسی تہذیب قائم کی جس نے یورپ کو تاریکی سے نکالا۔ الزہراوی نے 200 سے زائد جدید جراحی آلات ایجاد کیے۔",
                stageId = "very_hard_stage_2",
                pinColor = Color(0xFFAB47BC)
            ),
            HistoricalCityLocation(
                id = "constantinople",
                nameEn = "Constantinople / Istanbul",
                nameUr = "قسطنطنیہ (استنبول)",
                era = "1453 CE • فتحِ قسطنطنیہ",
                highlights = "سلطان محمد الفاتح، خشکی پر بحری جہاز چلانے کی منفرد حکمت",
                details = "سلطان محمد الفاتح نے محض 21 سال کی عمر میں بازنطینی قلعوں کے سامنے راتوں رات بحری جہازوں کو لکڑی کے تختوں پر خشکی سے گزار کر خلیج شاخِ زریں میں اتار دیا۔",
                stageId = "very_hard_stage_2",
                pinColor = Color(0xFFEF5350)
            )
        )
    }

    var selectedLocation by remember { mutableStateOf<HistoricalCityLocation?>(locations[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "تاریخی دنیا کا نقشہ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight
                        )
                        Text(
                            text = "3D Islamic World Map & Historical Capitals",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("world_map_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldDark
                )
            )
        },
        containerColor = Color(0xFF03140D),
        modifier = modifier.testTag("world_map_screen")
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // 3D Map Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            2.dp,
                            Brush.horizontalGradient(listOf(GoldAccent, EmeraldPrimary, NoorCyan)),
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.islamic_world_map_3d),
                        contentDescription = "3D Islamic World Map",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.Crop
                    )

                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth(),
                        color = Color.Black.copy(alpha = 0.65f)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = GoldLight,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "شہر یا تاریخی مرکز پر ٹیپ کریں اور تفصیلات و مہم سنیں",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            // Selected City Detail Card
            item {
                selectedLocation?.let { loc ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF07271B)),
                        shape = RoundedCornerShape(18.dp),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, loc.pinColor)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = loc.nameUr,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldLight
                                    )
                                    Text(
                                        text = "${loc.nameEn} • ${loc.era}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = loc.pinColor
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        HapticHelper.click(context)
                                        voiceEngine.speak(
                                            "${loc.nameUr}۔ ${loc.details}"
                                        )
                                    },
                                    modifier = Modifier
                                        .background(loc.pinColor.copy(alpha = 0.2f), CircleShape)
                                        .size(42.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VolumeUp,
                                        contentDescription = "Speak Details",
                                        tint = loc.pinColor
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = loc.details,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.95f),
                                lineHeight = 20.sp,
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Black.copy(alpha = 0.35f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = GoldAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = loc.highlights,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GoldLight,
                                        fontSize = 11.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            val stage = HistoryRepository.stages.find { it.id == loc.stageId }
                            if (stage != null) {
                                Button(
                                    onClick = {
                                        HapticHelper.click(context)
                                        onStartStageTask(stage)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("start_stage_from_map"),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GoldAccent,
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "اس تاریخی مہم کو کھیلیں (${stage.titleUrdu})",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // City Selection List
            item {
                Text(
                    text = "تمام اہم تاریخی مراکز و شہر",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GoldLight,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(locations) { loc ->
                val isSelected = selectedLocation?.id == loc.id
                val borderCol by animateColorAsState(
                    targetValue = if (isSelected) loc.pinColor else Color.White.copy(alpha = 0.1f),
                    label = "border_anim"
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            HapticHelper.click(context)
                            selectedLocation = loc
                            voiceEngine.speak(loc.nameUr)
                        }
                        .border(
                            if (isSelected) 2.dp else 1.dp,
                            borderCol,
                            RoundedCornerShape(14.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0xFF0F3927) else Color(0xFF081F15)
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(loc.pinColor.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = loc.pinColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = loc.nameUr,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = loc.nameEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }

                        Text(
                            text = loc.era,
                            style = MaterialTheme.typography.labelSmall,
                            color = loc.pinColor,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}
