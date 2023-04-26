package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Matrix4
import com.dishan.raytracer.foundation.Tuple
import com.dishan.raytracer.util.Num

class Ray(val origin: Tuple, val direction: Tuple)

fun Ray.position(t: Num): Tuple = origin + direction * t

fun Ray.transformed(transformation: Matrix4): Ray {
    val origin = transformation * origin
    val direction = transformation * direction
    return Ray(origin, direction)
}