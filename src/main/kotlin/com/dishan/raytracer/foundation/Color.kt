package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.Num
import com.dishan.raytracer.util.`~==`

class Color(val red: Num, val green: Num, val blue: Num) {
    companion object {
        val Black = Color(0.0, 0.0, 0.0)
        val White = Color(1.0, 1.0, 1.0)
    }

    infix fun `~==`(other: Color): Boolean {
        return red `~==` other.red
                && green `~==` other.green
                && blue `~==` other.blue
    }

    operator fun plus(other: Color): Color {
        return Color(red + other.red, green + other.green, blue + other.blue)
    }

    operator fun minus(other: Color): Color {
        return Color(red - other.red, green - other.green, blue - other.blue)
    }

    operator fun times(t: Num): Color {
        return Color(red * t, green * t, blue * t)
    }

    operator fun times(other: Color): Color {
        return Color(red * other.red, green * other.green, blue * other.blue)
    }
}