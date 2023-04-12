package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import kotlin.math.sqrt

class Sphere private constructor(val id: Int) : Object {

    companion object {
        private var nextId = 1
    }

    constructor() : this(nextId++)

    override var material: Material = material()
    var transform: Matrix4 = Matrix4.identity

    override fun normalAt(point: Tuple): Tuple {
        val objectPoint = transform.inversed() * point
        val objectNormal = objectPoint - point(0, 0, 0)
        val worldNormal = (transform.inversed().transposed() * objectNormal).copyWith(w = 0f)
        return worldNormal.normalized()
    }
}

fun Sphere.intersect(ray: Ray): Intersections {
    val transformedRay = ray.transformed(this.transform.inversed())

    val sphereToRay = transformedRay.origin - point(0, 0, 0)

    val a = transformedRay.direction dot transformedRay.direction
    val b = 2 * (transformedRay.direction dot sphereToRay)
    val c = (sphereToRay dot sphereToRay) - 1

    val discriminant = b * b - 4 * a * c
    if (discriminant < 0) {
        return emptyList()
    }

    val v = sqrt(discriminant)
    val t1 = (-b - v) / (2 * a)
    val t2 = (-b + v) / (2 * a)
    val i1 = Intersection(t1, this)
    val i2 = Intersection(t2, this)
    return listOf(i1, i2)
}
