package com.dishan.raytracer.util

import kotlin.math.abs

const val EPSILON: Float = 0.00001f

infix fun Float.`~==`(other: Float) : Boolean {
    return (abs(this-other) < EPSILON)
}