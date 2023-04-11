package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
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
        assert(xs[0].t `~==` 4.0f)
        assert(xs[1].t `~==` 6.0f)
    }

    @Test
    fun `A ray intersects a sphere at a tangent`() {
        val ray = Ray(point(0, 1, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].t `~==` 5.0f)
        assert(xs[1].t `~==` 5.0f)
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
        assert(xs[0].t `~==` -1.0f)
        assert(xs[1].t `~==` 1.0f)
    }

    @Test
    fun `A sphere is behind a ray`() {
        val ray = Ray(point(0, 0, 5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].t `~==` -6.0f)
        assert(xs[1].t `~==` -4.0f)
    }

    @Test
    fun `Intersect sets the object on the intersection`() {
        val ray = Ray(point(0, 0, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].body == sphere)
        assert(xs[1].body == sphere)
    }

    @Test
    fun `A sphere's default transformation`() {
        val sphere = Sphere()
        assert(sphere.transform `~==` Matrix4.identity)
    }

    @Test
    fun `Changing a sphere's transformation`() {
        val s = Sphere()
        val t = translation(2, 3, 4)
        s.transform = t

        assert(s.transform `~==` t)
    }

    @Test
    fun `Intersecting a scaled sphere with a ray`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val s = Sphere()
        s.transform = scaling(2, 2, 2)

        val xs = s.intersect(r)
        assert(xs.size == 2)
        assert(xs[0].t `~==` 3.0f)
        assert(xs[1].t `~==` 7.0f)
    }

    @Test
    fun `Intersecting a translated sphere with a ray`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val s = Sphere()
        s.transform = translation(5, 0, 0)

        val xs = s.intersect(r)
        assert(xs.isEmpty())
    }
}