package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*

interface Object {
    val material: Material
    fun normalAt(point: Tuple): Tuple
    fun intersect(ray: Ray): Intersections

    infix fun `~==`(other: Object): Boolean
}

class Intersection(val t: Float, val body: Object)

typealias Intersections = List<Intersection>

fun intersections(vararg intersection: Intersection): Intersections = listOf(*intersection)

fun Intersections.hit(): Intersection? = filter { it.t >= 0 }.minByOrNull { it.t }