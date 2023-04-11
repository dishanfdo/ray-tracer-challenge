package com.dishan.raytracer.foundation

import kotlin.math.cos
import kotlin.math.sin

fun translation(x: Int = 0, y: Int = 0, z: Int = 0): Matrix4 = translation(x.toFloat(), y.toFloat(), z.toFloat())

fun translation(x: Float = 0f, y: Float = 0f, z: Float = 0f): Matrix4 {
    return Matrix4.identity.apply {
        this[0, 3] = x
        this[1, 3] = y
        this[2, 3] = z
    }
}

fun scaling(x: Int = 0, y: Int = 0, z: Int = 0): Matrix4 = scaling(x.toFloat(), y.toFloat(), z.toFloat())

fun scaling(x: Float, y: Float, z: Float): Matrix4 {
    return Matrix4.identity.apply {
        this[0, 0] = x
        this[1, 1] = y
        this[2, 2] = z
    }
}

fun rotationX(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle).toFloat()
        val sin = sin(angle).toFloat()
        this[1, 1] = cos
        this[2, 2] = cos
        this[2, 1] = sin
        this[1, 2] = -sin
    }
}

fun rotationY(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle).toFloat()
        val sin = sin(angle).toFloat()
        this[0, 0] = cos
        this[2, 2] = cos
        this[2, 0] = -sin
        this[0, 2] = sin
    }
}

fun rotationZ(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle).toFloat()
        val sin = sin(angle).toFloat()
        this[0, 0] = cos
        this[1, 1] = cos
        this[1, 0] = sin
        this[0, 1] = -sin
    }
}