package com.dishan.raytracer.output

import com.dishan.raytracer.foundation.Canvas
import kotlin.math.roundToInt

fun Canvas.toPPM(colorDepth: Int = 255): String {
    val maxColor = colorDepth.toFloat()

    fun saveValue(value: Float) = (value * colorDepth).coerceIn(0f .. maxColor).roundToInt()

    return buildString {
        appendLine("P3")
        appendLine("$width $height")
        appendLine("$colorDepth")

        for (y in 0 until height) {
            for (x in 0 until width) {
                val color = this@toPPM[x, y]
                val red = saveValue(color.red)
                val green = saveValue(color.green)
                val blue = saveValue(color.blue)
                append("$red $green $blue")
                if (x != width - 1) {
                    append(" ")
                }
            }
            appendLine()
        }
    }
}