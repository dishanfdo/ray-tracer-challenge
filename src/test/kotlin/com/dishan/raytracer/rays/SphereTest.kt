package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.vector
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SphereTest {

    @Test
    fun `A ray intersects a sphere at two points`() {
        val ray = Ray(point(0, 0, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0] `~==` 4.0f)
        assert(xs[1] `~==` 6.0f)
    }

    @Test
    fun `A ray intersects a sphere at a tangent`() {
        val ray = Ray(point(0, 1, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0] `~==` 5.0f)
        assert(xs[1] `~==` 5.0f)
    }

    @Test
    fun `A ray misses a sphere`() {
        val ray = Ray(point(0, 2, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assertTrue(xs.isEmpty())
    }

    @Test
    fun `A ray originates inside a sphere`() {
        val ray = Ray(point(0, 0, 0), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0] `~==` -1.0f)
        assert(xs[1] `~==` 1.0f)
    }

    @Test
    fun `A sphere is behind a ray`() {
        val ray = Ray(point(0, 0, 5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0] `~==` -6.0f)
        assert(xs[1] `~==` -4.0f)
    }
}