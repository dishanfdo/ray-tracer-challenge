package com.dishan.raytracer.foundation

import com.dishan.raytracer.foundation.Type.*
import com.dishan.raytracer.util.closeEnough

enum class Type {
    Point, Vector, Other
}

class Tuple(val x: Float, val y: Float, val z: Float, val w: Float) {

    constructor(x: Int, y: Int, z: Int, w: Int): this(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())

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

    override fun toString(): String = "($x, $y, $z, $w)"

    operator fun plus(other: Tuple): Tuple = Tuple(x + other.x, y + other.y, z + other.z, w + other.w)

    operator fun minus(other: Tuple): Tuple = Tuple(x - other.x, y - other.y, z - other.z, w - other.w)

    operator fun unaryMinus(): Tuple = Tuple(-x, -y, -z, -w)

    operator fun times(t: Float): Tuple = Tuple(t * x, t * y, t * z, t * w)

    operator fun div(d: Float): Tuple = this * (1 / d)
}

fun tuple(x: Float, y: Float, z: Float, w: Float): Tuple = Tuple(x, y, z, w)
fun tuple(x: Int, y: Int, z: Int, w: Int): Tuple = Tuple(x, y, z, w)

fun point(x: Float, y: Float, z: Float): Tuple = Tuple(x, y, z, 1.0f)
fun point(x: Int, y: Int, z: Int): Tuple = Tuple(x, y, z, 1)

fun vector(x: Float, y: Float, z: Float): Tuple = Tuple(x, y, z, 0.0f)
fun vector(x: Int, y: Int, z: Int): Tuple = Tuple(x, y, z, 0)

