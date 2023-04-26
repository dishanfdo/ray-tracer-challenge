package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.PI
import kotlin.math.sqrt

class SphereTest {

    @Test
    fun `A ray intersects a sphere at two points`() {
        val ray = Ray(point(0, 0, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].t `~==` 4.0)
        assert(xs[1].t `~==` 6.0)
    }

    @Test
    fun `A ray intersects a sphere at a tangent`() {
        val ray = Ray(point(0, 1, -5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].t `~==` 5.0)
        assert(xs[1].t `~==` 5.0)
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
        assert(xs[0].t `~==` -1.0)
        assert(xs[1].t `~==` 1.0)
    }

    @Test
    fun `A sphere is behind a ray`() {
        val ray = Ray(point(0, 0, 5), vector(0, 0, 1))
        val sphere = Sphere()
        val xs = sphere.intersect(ray)
        assert(xs.size == 2)
        assert(xs[0].t `~==` -6.0)
        assert(xs[1].t `~==` -4.0)
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
        assert(xs[0].t `~==` 3.0)
        assert(xs[1].t `~==` 7.0)
    }

    @Test
    fun `Intersecting a translated sphere with a ray`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val s = Sphere()
        s.transform = translation(5, 0, 0)

        val xs = s.intersect(r)
        assert(xs.isEmpty())
    }

    @Test
    fun `The normal on a sphere at a point on the x axis`() {
        val s = Sphere()
        val n = s.normalAt(point(1, 0, 0))

        assert(n `~==` vector(1, 0, 0))
    }

    @Test
    fun `The normal on a sphere at a point on the y axis`() {
        val s = Sphere()
        val n = s.normalAt(point(0, 1, 0))

        assert(n `~==` vector(0, 1, 0))
    }

    @Test
    fun `The normal on a sphere at a point on the z axis`() {
        val s = Sphere()
        val n = s.normalAt(point(0, 0, 1))

        assert(n `~==` vector(0, 0, 1))
    }

    @Test
    fun `The normal on a sphere at a non-axial point`() {
        val s = Sphere()
        val n = s.normalAt(point(sqrt(3.0) / 3, sqrt(3.0) / 3, sqrt(3.0) / 3))

        assert(n `~==` vector(sqrt(3.0) / 3, sqrt(3.0) / 3, sqrt(3.0) / 3))
    }

    @Test
    fun `The normal is a normalized vector`() {
        val s = Sphere()
        val n = s.normalAt(point(sqrt(3.0) / 3, sqrt(3.0) / 3, sqrt(3.0) / 3))

        assert(n `~==` n.normalized())
    }

    @Test
    fun `Computing the normal on a translated sphere`() {
        val s = Sphere()
        s.transform = translation(0, 1, 0)
        val n = s.normalAt(point(0.0, 1.70711, -0.70711))

        assert(n `~==` vector(0.0, 0.70711, -0.70711))
    }

    @Test
    fun `Computing the normal on a transformed sphere`() {
        val s = Sphere()
        val m = scaling(1.0, 0.5, 1.0) * rotationZ(PI / 5)
        s.transform = m

        val n = s.normalAt(point(0.0, sqrt(2.0)/2, -sqrt(2.0)/2))

        assert(n `~==` vector(0.0, 0.97014, -0.24254))
    }

    @Test
    fun `A sphere has a default material`() {
        val s = Sphere()
        val m = s.material

        assert(m `~==` material())
    }

    @Test
    fun `A sphere may be assigned a material`() {
        val s = Sphere()
        val m = material(ambient = 1.0)
        s.material = m

        assert(s.material `~==` m)
    }
}