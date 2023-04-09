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
            add("\n")
        }
    }

    fun List<String>.wrapLines(width: Int = 70): List<String> = map { line ->
        if (line.length <= 70) listOf(line) else line.splitBefore(width - 1)
    }.flatten()

    return buildLines().wrapLines().joinToString(separator = "\n")
}

fun String.splitBefore(index: Int): List<String> {
    for (i in index downTo 0) {
        if (this[i] == ' ') {
            val a = substring(0, i)
            val b = substring(i + 1)
            return listOf(a, b)
        }
    }
    return listOf(this)
}