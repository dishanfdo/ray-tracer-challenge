package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*

abstract class Shape {
    open var material: Material = material()
    open var transform: Matrix4 = identity()
    fun normalAt(point: Tuple): Tuple {
        val localPoint = transform.inversed() * point
        val localNormal = localNormalAt(localPoint)
        val worldNormal = (transform.inversed().transposed() * localNormal).copyWith(w = 0.0)
        return worldNormal.normalized()
    }

    abstract fun localNormalAt(point: Tuple): Tuple

    fun intersect(ray: Ray): Intersections {
        val localRay = ray.transformed(this.transform.inversed())
        return localIntersect(localRay)
    }
    abstract fun localIntersect(localRay: Ray): Intersections
    abstract infix fun `~==`(other: Shape): Boolean
}