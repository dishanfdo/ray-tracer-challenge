package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.dot
import com.dishan.raytracer.foundation.point
import kotlin.math.sqrt

class Sphere private constructor(val id: Int) {
    companion object {
        private var nextId = 1
    }

    constructor() : this(nextId++)
}

fun Sphere.intersect(ray: Ray): List<Float> {
    val sphereToRay = ray.origin - point(0, 0, 0)

    val a = ray.direction dot ray.direction
    val b = 2 * (ray.direction dot sphereToRay)
    val c = (sphereToRay dot sphereToRay) - 1

    val discriminant = b * b - 4 * a * c
    if (discriminant < 0) {
        return emptyList()
    }

    val v = sqrt(discriminant)
    val t1 = (-b - v) / (2 * a)
    val t2 = (-b + v) / (2 * a)
    return listOf(t1, t2)
}
