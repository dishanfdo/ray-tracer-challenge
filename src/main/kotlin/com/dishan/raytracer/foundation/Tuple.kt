package com.dishan.raytracer.foundation

import com.dishan.raytracer.foundation.Type.*
import com.dishan.raytracer.util.closeEnough

enum class Type {
    Point, Vector, Other
}

class Tuple(val x: Float, val y: Float, val z: Float, val w: Float) {
    val type: Type = when (w) {
        0.0f -> Vector
        1.0f -> Point
        else -> Other
    }

    fun closeEnough(other: Tuple): Boolean {
        return x.closeEnough(other.x)
                && y.closeEnough(other.y)
                && z.closeEnough(other.z)
                && w == other.w
    }
}

fun point(x: Float, y: Float, z: Float): Tuple = Tuple(x, y, z, 1.0f)

fun vector(x: Float, y: Float, z: Float): Tuple = Tuple(x, y, z, 0.0f)

