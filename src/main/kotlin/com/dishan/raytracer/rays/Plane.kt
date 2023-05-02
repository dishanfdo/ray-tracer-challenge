package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Tuple
import com.dishan.raytracer.foundation.vector
import com.dishan.raytracer.util.EPSILON
import kotlin.math.abs

class Plane private constructor(val id: Int) : Shape() {

    companion object {
        private var nextId = 1
    }

    constructor() : this(nextId++)

    override fun localNormalAt(point: Tuple): Tuple = vector(0, 1, 0)

    override fun localIntersect(localRay: Ray): Intersections {
        if (abs(localRay.direction.y) < EPSILON) {
            return emptyList()
        }

        val t = -localRay.origin.y / localRay.direction.y
        return listOf(Intersection(t, this))
    }

    override fun `~==`(other: Shape): Boolean {
        if (other !is Plane) return false
        return transform `~==` other.transform && material `~==` other.material
    }
}