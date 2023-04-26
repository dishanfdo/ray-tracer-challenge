@file:Suppress("unused")

package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.Num
import com.dishan.raytracer.util.toNum
import kotlin.math.cos
import kotlin.math.sin

fun translation(x: Int = 0, y: Int = 0, z: Int = 0): Matrix4 = translation(x.toNum(), y.toNum(), z.toNum())

fun translation(x: Num = 0.0, y: Num = 0.0, z: Num = 0.0): Matrix4 {
    return Matrix4.identity.apply {
        this[0, 3] = x
        this[1, 3] = y
        this[2, 3] = z
    }
}

fun scaling(x: Int = 1, y: Int = 1, z: Int = 1): Matrix4 = scaling(x.toNum(), y.toNum(), z.toNum())

fun scaling(x: Num = 1.0, y: Num = 1.0, z: Num = 1.0): Matrix4 {
    return Matrix4.identity.apply {
        this[0, 0] = x
        this[1, 1] = y
        this[2, 2] = z
    }
}

fun rotationX(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle)
        val sin = sin(angle)
        this[1, 1] = cos
        this[2, 2] = cos
        this[2, 1] = sin
        this[1, 2] = -sin
    }
}

fun rotationY(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle)
        val sin = sin(angle)
        this[0, 0] = cos
        this[2, 2] = cos
        this[2, 0] = -sin
        this[0, 2] = sin
    }
}

fun rotationZ(angle: Double): Matrix4 {
    return Matrix4.identity.apply {
        val cos = cos(angle)
        val sin = sin(angle)
        this[0, 0] = cos
        this[1, 1] = cos
        this[1, 0] = sin
        this[0, 1] = -sin
    }
}

fun shearing(xy: Int = 0, xz: Int = 0, yx: Int = 0, yz: Int = 0, zx: Int = 0, zy: Int = 0): Matrix4 =
    shearing(xy.toNum(), xz.toNum(), yx.toNum(), yz.toNum(), zx.toNum(), zy.toNum())

fun shearing(xy: Num = 0.0, xz: Num = 0.0, yx: Num = 0.0, yz: Num = 0.0, zx: Num = 0.0, zy: Num = 0.0): Matrix4 {
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
fun Matrix4.translate(x: Num = 0.0, y: Num = 0.0, z: Num = 0.0): Matrix4 = translation(x, y, z) * this

fun Matrix4.scale(x: Int = 1, y: Int = 1, z: Int = 1): Matrix4 = scaling(x, y, z) * this
fun Matrix4.scale(x: Num = 1.0, y: Num = 1.0, z: Num = 1.0): Matrix4 = scaling(x, y, z) * this

fun Matrix4.rotateX(angle: Double): Matrix4 = rotationX(angle) * this
fun Matrix4.rotateY(angle: Double): Matrix4 = rotationY(angle) * this
fun Matrix4.rotateZ(angle: Double): Matrix4 = rotationZ(angle) * this

fun Matrix4.shear(xy: Int = 0, xz: Int = 0, yx: Int = 0, yz: Int = 0, zx: Int = 0, zy: Int = 0): Matrix4 =
    shearing(xy, xz, yx, yz, zx, zy) * this

fun Matrix4.shear(
    xy: Num = 0.0,
    xz: Num = 0.0,
    yx: Num = 0.0,
    yz: Num = 0.0,
    zx: Num = 0.0,
    zy: Num = 0.0
): Matrix4 = shearing(xy, xz, yx, yz, zx, zy) * this

fun viewTransform(from: Tuple, to: Tuple, up: Tuple): Matrix4 {
    val forward = (to - from).normalized()
    val upn = up.normalized()
    val left = forward cross upn
    val trueUp = left cross forward
    val orientation = Matrix4(
        left.x, left.y, left.z, 0.0,
        trueUp.x, trueUp.y, trueUp.z, 0.0,
        -forward.x, -forward.y, -forward.z, 0.0,
        0.0, 0.0, 0.0, 1.0,
    )

    return orientation * translation(-from.x, -from.y, -from.z)
}