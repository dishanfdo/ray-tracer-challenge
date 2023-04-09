package com.dishan.raytracer.output

import com.dishan.raytracer.foundation.Canvas
import kotlin.math.roundToInt

fun Canvas.toPPM(colorDepth: Int = 255): String {
    val maxColor = colorDepth.toFloat()

    fun saveValue(value: Float) = (value * colorDepth).coerceIn(0f .. maxColor).roundToInt()

    fun buildLines(): List<String> {
        return buildList {
            add("P3")
            add("$width $height")
            add("$colorDepth")

            for (y in 0 until height) {
                val line = buildString {
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
                }
                add(line)
            }
        }
    }

    return buildLines().joinToString(separator = "\n")
}