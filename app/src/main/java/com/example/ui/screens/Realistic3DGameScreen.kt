package com.example.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CompassCalibration
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.GameProgressManager
import com.example.data.IslamicMissionRepository
import com.example.model.IslamicMission
import com.example.model.MiniIslamicQuiz
import com.example.model.NpcCitizen
import com.example.model.UserProfile
import com.example.ui.components.MiniQuizDialog
import com.example.ui.components.NpcInteractionDialog
import com.example.ui.components.VirtualJoystick
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import com.example.ui.theme.NoorCyan
import com.example.util.HapticHelper
import com.example.voice.GameVoiceEngine
import kotlin.math.atan2
import kotlin.math.hypot

@Composable
fun Realistic3DGameScreen(
    profile: UserProfile,
    gameManager: GameProgressManager,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler { onBack() }

    // Initialize Voice Engine
    val voiceEngine = remember { GameVoiceEngine(context) }
    DisposableEffect(Unit) {
        onDispose {
            voiceEngine.shutdown()
        }
    }

    // 3D Player coordinates (0..100% on map)
    var playerX by remember { mutableFloatStateOf(50f) }
    var playerY by remember { mutableFloatStateOf(65f) }
    var playerAngle by remember { mutableFloatStateOf(0f) }
    var isMoving by remember { mutableStateOf(false) }

    // Active Mission
    val missions = IslamicMissionRepository.missions
    var activeMissionIndex by remember {
        mutableIntStateOf(
            missions.indexOfFirst { !profile.completedMissionIds.contains(it.id) }.let { if (it >= 0) it else 0 }
        )
    }
    val currentMission = missions.getOrNull(activeMissionIndex) ?: missions.first()

    // Dialogs state
    var interactingNpc by remember { mutableStateOf<NpcCitizen?>(null) }
    var activeMiniQuiz by remember { mutableStateOf<MiniIslamicQuiz?>(null) }
    var showMissionCompletedBanner by remember { mutableStateOf(false) }

    // Distance to current mission target
    val distToMission = hypot((currentMission.targetX - playerX).toDouble(), (currentMission.targetY - playerY).toDouble()).toFloat()
    val isAtMissionLocation = distToMission < 14f

    // Find nearest NPC citizen
    val npcs = IslamicMissionRepository.npcs
    val nearestNpc = remember(playerX, playerY) {
        npcs.minByOrNull { npc ->
            hypot((npc.posX - playerX).toDouble(), (npc.posY - playerY).toDouble())
        }
    }
    val distToNpc = nearestNpc?.let {
        hypot((it.posX - playerX).toDouble(), (it.posY - playerY).toDouble()).toFloat()
    } ?: 100f
    val isNearNpc = distToNpc < 14f

    fun movePlayer(deltaX: Float, deltaY: Float) {
        if (deltaX != 0f || deltaY != 0f) {
            val speed = 2.4f
            playerX = (playerX + deltaX * speed).coerceIn(12f, 88f)
            playerY = (playerY + deltaY * speed).coerceIn(18f, 88f)
            playerAngle = Math.toDegrees(atan2(deltaY.toDouble(), deltaX.toDouble())).toFloat()
            isMoving = true
            HapticHelper.performClick(context)
        } else {
            isMoving = false
        }
    }

    // Gentle breathing / walking bob
    val infiniteTransition = rememberInfiniteTransition(label = "player_bob")
    val walkBob by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isMoving) -8f else -2f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (isMoving) 250 else 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "walk_bob"
    )

    Scaffold(
        modifier = modifier.testTag("realistic_3d_game_screen")
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            // 3D Perspective World Viewport
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val targetX = (offset.x / size.width) * 100f
                            val targetY = (offset.y / size.height) * 100f
                            val dx = targetX - playerX
                            val dy = targetY - playerY
                            val dist = hypot(dx.toDouble(), dy.toDouble()).toFloat()
                            if (dist > 1f) {
                                movePlayer(dx / dist, dy / dist)
                            }
                        }
                    }
            ) {
                val viewportWidth = maxWidth
                val viewportHeight = maxHeight

                // Background 3D stylized Islamic city
                Image(
                    painter = painterResource(id = R.drawable.islamic_world_map_3d),
                    contentDescription = "Realistic 3D Islamic City View",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Render NPCs in 3D World
                npcs.forEach { npc ->
                    val npcOffsetX = (viewportWidth * (npc.posX / 100f)) - 22.dp
                    val npcOffsetY = (viewportHeight * (npc.posY / 100f)) - 32.dp

                    Box(
                        modifier = Modifier
                            .offset(x = npcOffsetX, y = npcOffsetY)
                            .clickable {
                                interactingNpc = npc
                                HapticHelper.performClick(context)
                            }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Black.copy(alpha = 0.75f),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GoldLight)
                            ) {
                                Text(
                                    text = npc.nameUrdu.substringBefore(" ("),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = GoldLight,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(GoldAccent.copy(alpha = 0.3f))
                                    .border(1.5.dp, GoldAccent, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = npc.avatarDrawableRes),
                                    contentDescription = npc.nameEnglish,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }

                // Mission Destination Beacon (Glowing Golden Arch/Star Pillar)
                val beaconX = (viewportWidth * (currentMission.targetX / 100f)) - 24.dp
                val beaconY = (viewportHeight * (currentMission.targetY / 100f)) - 36.dp

                Box(
                    modifier = Modifier
                        .offset(x = beaconX, y = beaconY)
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(GoldAccent.copy(alpha = 0.8f), GoldAccent.copy(alpha = 0.2f), Color.Transparent)
                                )
                            )
                            .border(2.dp, GoldLight, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Navigation,
                            contentDescription = "Mission Objective",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // 3D Player Character (Zayd in 3rd Person View)
                val playerOffsetX = (viewportWidth * (playerX / 100f)) - 32.dp
                val playerOffsetY = (viewportHeight * (playerY / 100f)) - 54.dp

                Box(
                    modifier = Modifier
                        .offset(x = playerOffsetX, y = playerOffsetY + walkBob.dp)
                        .size(64.dp)
                ) {
                    // Realistic Ground Contact Shadow
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(width = 44.dp, height = 14.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.6f))
                    )

                    // 3D Cartoon Hero Avatar
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(EmeraldPrimary)
                            .border(2.dp, GoldAccent, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hero_cartoon_3d),
                            contentDescription = "Zayd Explorer Character",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // ================= TOP HUD =================
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Top Status Bar: Exit, Player Level, Hasanat, Voice Toggle, Compass
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Exit to Main Menu
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.6f))
                            .border(1.dp, GoldAccent.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Exit to Menu",
                            tint = GoldLight
                        )
                    }

                    // Hasanat (نیکیاں) Good Deeds Badge
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.Black.copy(alpha = 0.7f),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, GoldAccent)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Hasanat",
                                tint = Color(0xFFFF5252),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${profile.hasanat} نیکیاں (Hasanat)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        }
                    }

                    // Voice Narration Toggle
                    IconButton(
                        onClick = {
                            val newVoiceState = !profile.voiceNarrationEnabled
                            gameManager.toggleVoiceNarration(newVoiceState)
                            voiceEngine.toggleVoice(newVoiceState)
                            HapticHelper.performClick(context)
                        },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.6f))
                            .border(1.dp, GoldAccent.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(
                            imageVector = if (profile.voiceNarrationEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                            contentDescription = "Voice Narration",
                            tint = if (profile.voiceNarrationEnabled) GoldLight else Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Active Mission Objective Tracker Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.5.dp,
                            Brush.horizontalGradient(listOf(GoldAccent, EmeraldPrimary)),
                            RoundedCornerShape(14.dp)
                        )
                        .testTag("mission_tracker_card"),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.8f)),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(GoldAccent.copy(alpha = 0.2f))
                                .border(1.dp, GoldAccent, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Navigation,
                                contentDescription = "Objective",
                                tint = GoldLight,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentMission.titleUrdu,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight,
                                fontSize = 12.5.sp
                            )
                            Text(
                                text = "${currentMission.objectiveUrdu} (${distToMission.toInt()}m دور)",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 11.sp
                            )
                        }

                        if (isAtMissionLocation) {
                            Button(
                                onClick = {
                                    val targetNpc = npcs.firstOrNull { it.id == currentMission.targetNpcId } ?: npcs.first()
                                    interactingNpc = targetNpc
                                    HapticHelper.performSuccess(context)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF1B8A4C),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "مشن کریں",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }
                }
            }

            // Floating Interaction Prompt above Player when near an NPC
            if (isNearNpc && nearestNpc != null) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-64).dp)
                        .clickable {
                            interactingNpc = nearestNpc
                            HapticHelper.performClick(context)
                        }
                        .border(1.5.dp, GoldAccent, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.88f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.WavingHand,
                            contentDescription = null,
                            tint = GoldLight,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${nearestNpc.nameUrdu.substringBefore(" (")} سے بات و سلام کریں",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            // ================= BOTTOM CONTROLS =================
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Analog Virtual Joystick
                VirtualJoystick(
                    onMove = { dx, dy -> movePlayer(dx, dy) },
                    modifier = Modifier.size(110.dp)
                )

                // Quick Action Buttons Panel
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    // Adhan & Namaz Call Action Button
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = Color(0xFF0F3827).copy(alpha = 0.9f),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, EmeraldPrimary),
                        modifier = Modifier.clickable {
                            HapticHelper.performSuccess(context)
                            voiceEngine.speakZayd("Allahu Akbar, Allahu Akbar! Time for Congregational Salah! Let us walk to the Mosque together!")
                            gameManager.addHasanat(50)
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mosque,
                                contentDescription = "Call to Namaz",
                                tint = GoldLight,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "اذان و نماز کی دعوت (+50)",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 11.sp
                            )
                        }
                    }

                    // Roadside Mini Quiz Action Button
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = Color(0xFF14273E).copy(alpha = 0.9f),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF1976D2)),
                        modifier = Modifier.clickable {
                            HapticHelper.performClick(context)
                            activeMiniQuiz = nearestNpc?.miniQuiz ?: currentMission.miniQuiz
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = "Mini Quiz",
                                tint = Color(0xFF90CAF9),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "چھوٹا اسلامی کوئز",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }

    // NPC Interaction Dialogue
    interactingNpc?.let { npc ->
        NpcInteractionDialog(
            npc = npc,
            voiceEngine = voiceEngine,
            onRewardHasanat = { amount ->
                gameManager.addHasanat(amount)
                if (currentMission.targetNpcId == npc.id && !currentMission.isCompleted) {
                    gameManager.recordMissionCompleted(currentMission.id, currentMission.rewardHasanat, currentMission.rewardXp)
                    showMissionCompletedBanner = true
                    if (activeMissionIndex < missions.size - 1) {
                        activeMissionIndex++
                    }
                }
            },
            onOpenMiniQuiz = {
                val q = npc.miniQuiz ?: currentMission.miniQuiz
                interactingNpc = null
                activeMiniQuiz = q
            },
            onDismiss = {
                interactingNpc = null
            }
        )
    }

    // Mini Quiz Dialog
    activeMiniQuiz?.let { quiz ->
        MiniQuizDialog(
            quiz = quiz,
            voiceEngine = voiceEngine,
            onAnswerEvaluated = { isCorrect, hasanat, xp ->
                gameManager.addHasanat(hasanat)
            },
            onDismiss = {
                activeMiniQuiz = null
            }
        )
    }
}
