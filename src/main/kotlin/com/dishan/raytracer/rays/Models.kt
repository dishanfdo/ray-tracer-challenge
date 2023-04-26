package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.EPSILON
import com.dishan.raytracer.util.Num

interface Object {
    var material: Material
    fun normalAt(point: Tuple): Tuple
    fun intersect(ray: Ray): Intersections

    infix fun `~==`(other: Object): Boolean
}

class Intersection(val t: Num, val body: Object) {
    fun prepareComputation(ray: Ray): Computation {
        val point = ray.position(t)
        val normalv = body.normalAt(point)
        val eyev = -ray.direction
        val isInside = (normalv dot eyev) < 0
        val correctedNormal = if (isInside) -normalv else normalv
        val overPoint = point + correctedNormal * EPSILON

        return Computation(
            t = t,
            body = body,
            point = point,
            overPoint = overPoint,
            eyev = eyev,
            normalv = correctedNormal,
            inside = isInside
        )
    }
}

typealias Intersections = List<Intersection>

fun intersections(vararg intersection: Intersection): Intersections = listOf(*intersection)

fun Intersections.hit(): Intersection? = filter { it.t >= 0 }.minByOrNull { it.t }

class Computation(
    val t: Num,
    val body: Object,
    val point: Tuple,
    val overPoint: Tuple,
    val eyev: Tuple,
    val normalv: Tuple,
    val inside: Boolean,
) {
    override fun toString(): String {
        return "[\n p: $point \nop: $overPoint \n n: $normalv\n]"
    }
}