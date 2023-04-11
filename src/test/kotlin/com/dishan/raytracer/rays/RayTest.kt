package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.point
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
        assert(ray.position(0f) `~==` point(2, 3, 4))
        assert(ray.position(1f) `~==` point(3, 3, 4))
        assert(ray.position(-1f) `~==` point(1, 3, 4))
        assert(ray.position(2.5f) `~==` point(4.5f, 3f, 4f))
    }
}