package com.example.appanime.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Определение типографики
val AppTypography = Typography(
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

// Определение форм (shapes)
val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),   // Инициализация small
    medium = RoundedCornerShape(4.dp),  // Инициализация medium
    large = RoundedCornerShape(0.dp)     // Инициализация large
)

// Основная функция темы приложения
@Composable
fun AnimeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = AppTypography,
        shapes = AppShapes, // Используйте инициализированный объект здесь
        content = content
    )
}