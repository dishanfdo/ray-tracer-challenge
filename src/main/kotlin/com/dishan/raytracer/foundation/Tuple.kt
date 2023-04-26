package com.dishan.raytracer.foundation

import com.dishan.raytracer.foundation.Type.*
import com.dishan.raytracer.util.Num
import com.dishan.raytracer.util.toNum
import com.dishan.raytracer.util.`~==`

enum class Type {
    Point, Vector, Other
}

class Tuple(val x: Num, val y: Num, val z: Num, val w: Num) {

    constructor(x: Int, y: Int, z: Int, w: Int) : this(x.toNum(), y.toNum(), z.toNum(), w.toNum())

    val type: Type = when (w) {
        0.0 -> Vector
        1.0 -> Point
        else -> Other
    }

    infix fun `~==`(other: Tuple): Boolean {
        return x `~==` other.x
                && y `~==` other.y
                && z `~==` other.z
                && w == other.w
    }

    override fun toString(): String = "($x, $y, $z, $w)"

    operator fun plus(other: Tuple): Tuple = Tuple(x + other.x, y + other.y, z + other.z, w + other.w)

    operator fun minus(other: Tuple): Tuple = Tuple(x - other.x, y - other.y, z - other.z, w - other.w)

    operator fun unaryMinus(): Tuple = Tuple(-x, -y, -z, -w)

    operator fun times(t: Num): Tuple = Tuple(t * x, t * y, t * z, t * w)

    operator fun div(d: Num): Tuple = this * (1 / d)
}

fun Tuple.copyWith(x: Num? = null, y: Num? = null, z: Num? = null, w: Num? = null): Tuple {
    return Tuple(
        x = x ?: this.x,
        y = y ?: this.y,
        z = z ?: this.z,
        w = w ?: this.w
    )
}

fun tuple(x: Num, y: Num, z: Num, w: Num): Tuple = Tuple(x, y, z, w)
fun tuple(x: Int, y: Int, z: Int, w: Int): Tuple = Tuple(x, y, z, w)

fun point(x: Num, y: Num, z: Num): Tuple = Tuple(x, y, z, 1.0)
fun point(x: Int, y: Int, z: Int): Tuple = Tuple(x, y, z, 1)

fun vector(x: Num, y: Num, z: Num): Tuple = Tuple(x, y, z, 0.0)
fun vector(x: Int, y: Int, z: Int): Tuple = Tuple(x, y, z, 0)

fun Tuple.reflect(normal: Tuple): Tuple {
    return this - normal * 2.0 * (this dot normal)
}