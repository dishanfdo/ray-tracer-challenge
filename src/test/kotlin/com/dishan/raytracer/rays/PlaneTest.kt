package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.vector
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

class PlaneTest {

    @Test
    fun `The normal of a plane is constant everywhere`() {
        val p = Plane()
        val n1 = p.localNormalAt(point(0, 0, 0))
        val n2 = p.localNormalAt(point(10, 0, -10))
        val n3 = p.localNormalAt(point(-5, 0, 150))

        assert(n1 `~==` vector(0, 1, 0))
        assert(n2 `~==` vector(0, 1, 0))
        assert(n3 `~==` vector(0, 1, 0))
    }

    @Test
    fun `Intersect with a ray parallel to the plane`() {
        val p = Plane()
        val r = Ray(point(0, 10, 0), vector(0, 0, 1))
        val xs = p.localIntersect(r)

        assert(xs.isEmpty())
    }

    @Test
    fun `Intersect with a coplanar ray`() {
        val p = Plane()
        val r = Ray(point(0, 0, 0), vector(0, 0, 1))
        val xs = p.localIntersect(r)

        assert(xs.isEmpty())
    }

    @Test
    fun `A ray intersecting a plane from above`() {
        val p = Plane()
        val r = Ray(point(0, 1, 0), vector(0, -1, 0))
        val xs = p.localIntersect(r)

        assert(xs.size == 1)
        assert(xs[0].t `~==` 1.0)
        assert(xs[0].body == p)
    }

    @Test
    fun `A ray intersecting a plane from below`() {
        val p = Plane()
        val r = Ray(point(0, -1, 0), vector(0, 1, 0))
        val xs = p.localIntersect(r)

        assert(xs.size == 1)
        assert(xs[0].t `~==` 1.0)
        assert(xs[0].body == p)
    }
}