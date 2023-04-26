package com.dishan.raytracer.util

import kotlin.math.abs

typealias Num = Double

const val EPSILON: Num = 0.00001

infix fun Float.`~==`(other: Float): Boolean {
    return (abs(this - other) < EPSILON)
}

infix fun Double.`~==`(other: Double): Boolean {
    return (abs(this - other) < EPSILON)
}

infix fun Float.`~!=`(other: Float): Boolean = !(this `~==` other)

infix fun Double.`~!=`(other: Double): Boolean = !(this `~==` other)

fun Int.toNum(): Num = this.toDouble()