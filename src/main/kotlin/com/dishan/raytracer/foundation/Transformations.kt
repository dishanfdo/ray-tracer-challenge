@file:Suppress("unused")

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

fun scaling(x: Int = 1, y: Int = 1, z: Int = 1): Matrix4 = scaling(x.toFloat(), y.toFloat(), z.toFloat())

fun scaling(x: Float = 1f, y: Float = 1f, z: Float = 1f): Matrix4 {
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

fun shearing(xy: Int = 0, xz: Int = 0, yx: Int = 0, yz: Int = 0, zx: Int = 0, zy: Int = 0): Matrix4 =
    shearing(xy.toFloat(), xz.toFloat(), yx.toFloat(), yz.toFloat(), zx.toFloat(), zy.toFloat())

fun shearing(xy: Float = 0f, xz: Float = 0f, yx: Float = 0f, yz: Float = 0f, zx: Float = 0f, zy: Float = 0f): Matrix4 {
    return Matrix4.identity.apply {
        this[0, 1] = xy
        this[0, 2] = xz
        this[1, 0] = yx
        this[1, 2] = yz
        this[2, 0] = zx
        this[2, 1] = zy
    }
}

fun identity() = Matrix4.identity
fun Matrix4.translate(x: Int = 0, y: Int = 0, z: Int = 0): Matrix4 = translation(x, y, z) * this
fun Matrix4.translate(x: Float = 0f, y: Float = 0f, z: Float = 0f): Matrix4 = translation(x, y, z) * this

fun Matrix4.scale(x: Int = 1, y: Int = 1, z: Int = 1): Matrix4 = scaling(x, y, z) * this
fun Matrix4.scale(x: Float = 1f, y: Float = 1f, z: Float = 1f): Matrix4 = scaling(x, y, z) * this

fun Matrix4.rotateX(angle: Double): Matrix4 = rotationX(angle) * this
fun Matrix4.rotateY(angle: Double): Matrix4 = rotationY(angle) * this
fun Matrix4.rotateZ(angle: Double): Matrix4 = rotationZ(angle) * this

fun Matrix4.shear(xy: Int = 0, xz: Int = 0, yx: Int = 0, yz: Int = 0, zx: Int = 0, zy: Int = 0): Matrix4 =
    shearing(xy, xz, yx, yz, zx, zy) * this

fun Matrix4.shear(
    xy: Float = 0f,
    xz: Float = 0f,
    yx: Float = 0f,
    yz: Float = 0f,
    zx: Float = 0f,
    zy: Float = 0f
): Matrix4 = shearing(xy, xz, yx, yz, zx, zy) * this