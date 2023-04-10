package com.dishan.raytracer.foundation

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