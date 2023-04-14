package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*

interface Object {
    var material: Material
    fun normalAt(point: Tuple): Tuple
    fun intersect(ray: Ray): Intersections

    infix fun `~==`(other: Object): Boolean
}

class Intersection(val t: Float, val body: Object) {
    fun prepareComputation(ray: Ray): Computation {
        val point = ray.position(t)
        val normalv = body.normalAt(point)
        val eyev = -ray.direction
        val isInside = (normalv dot eyev) < 0

        return Computation(
            t = t,
            body = body,
            point = point,
            eyev = eyev,
            normalv = if (isInside) -normalv else normalv,
            inside = isInside
        )
    }
}

typealias Intersections = List<Intersection>

fun intersections(vararg intersection: Intersection): Intersections = listOf(*intersection)

fun Intersections.hit(): Intersection? = filter { it.t >= 0 }.minByOrNull { it.t }

class Computation(
    val t: Float,
    val body: Object,
    val point: Tuple,
    val eyev: Tuple,
    val normalv: Tuple,
    val inside: Boolean,
)