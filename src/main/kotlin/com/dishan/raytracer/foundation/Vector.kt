package com.dishan.raytracer.foundation

import kotlin.math.sqrt

val Tuple.magnitude: Float
    get() = sqrt(x * x + y * y + z * z + w * w)

fun Tuple.normalized(): Tuple {
    val m = magnitude
    return Tuple(x / m, y / m, z / m, w / m)
}

infix fun Tuple.dot(other: Tuple): Float {
    return x * other.x + y * other.y + z * other.z + w * other.w
}

infix fun Tuple.cross(other: Tuple): Tuple {
    val x = this.y * other.z - this.z * other.y
    val y = this.z * other.x - this.x * other.z
    val z = this.x * other.y - this.y * other.x

    return vector(x, y, z)
}