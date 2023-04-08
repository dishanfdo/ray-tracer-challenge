package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.closeEnough

class Color(val red: Float, val green: Float, val blue: Float) {
    companion object {
        val Black = Color(0f, 0f, 0f)
        val White = Color(1f, 1f, 1f)
    }

    fun closeEnough(other: Color): Boolean {
        return red.closeEnough(other.red)
                && green.closeEnough(other.green)
                && blue.closeEnough(other.blue)
    }

    operator fun plus(other: Color): Color {
        return Color(red + other.red, green + other.green, blue + other.blue)
    }

    operator fun minus(other: Color): Color {
        return Color(red - other.red, green - other.green, blue - other.blue)
    }

    operator fun times(t: Float): Color {
        return Color(red * t, green * t, blue * t)
    }

    operator fun times(other: Color): Color {
        return Color(red * other.red, green * other.green, blue * other.blue)
    }
}