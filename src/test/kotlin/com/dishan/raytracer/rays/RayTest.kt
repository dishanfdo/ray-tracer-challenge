package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.scaling
import com.dishan.raytracer.foundation.translation
import com.dishan.raytracer.foundation.vector
import org.junit.jupiter.api.Test

class RayTest {

    @Test
    fun `Creating and querying a ray`() {
        val origin = point(1, 2, 3)
        val direction = vector(4, 5, 6)
        val ray = Ray(origin, direction)
        assert(ray.origin `~==` origin)
        assert(ray.direction `~==` direction)
    }

    @Test
    fun `Computing a point from a distance`() {
        val ray = Ray(point(2, 3, 4), vector(1, 0, 0))
        assert(ray.position(0.0) `~==` point(2, 3, 4))
        assert(ray.position(1.0) `~==` point(3, 3, 4))
        assert(ray.position(-1.0) `~==` point(1, 3, 4))
        assert(ray.position(2.5) `~==` point(4.5, 3.0, 4.0))
    }

    @Test
    fun `Translating a ray`() {
        val r = Ray(point(1, 2, 3), vector(0, 1, 0))
        val m = translation(3, 4, 5)
        val r2 = r.transformed(m)
        assert(r2.origin `~==` point(4, 6, 8))
        assert(r2.direction `~==` vector(0, 1, 0))
    }

    @Test
    fun `Scaling a ray`() {
        val r = Ray(point(1, 2, 3), vector(0, 1, 0))
        val m = scaling(2, 3, 4)
        val r2 = r.transformed(m)
        assert(r2.origin `~==` point(2, 6, 12))
        assert(r2.direction `~==` vector(0, 3, 0))
    }
}