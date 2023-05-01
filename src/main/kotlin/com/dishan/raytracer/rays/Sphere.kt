package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import kotlin.math.sqrt

class Sphere private constructor(val id: Int) : Shape() {

    companion object {
        private var nextId = 1
    }

    constructor() : this(nextId++)

    override var material: Material = material()
    override var transform: Matrix4 = Matrix4.identity

    override fun localNormalAt(point: Tuple): Tuple {
        return point - point(0, 0, 0)
    }

    override fun localIntersect(localRay: Ray): Intersections {
        val sphereToRay = localRay.origin - point(0, 0, 0)

        val a = localRay.direction dot localRay.direction
        val b = 2 * (localRay.direction dot sphereToRay)
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

    override fun `~==`(other: Shape): Boolean {
        if (other !is Sphere) return false
        return transform `~==` other.transform && material `~==` other.material
    }
}
