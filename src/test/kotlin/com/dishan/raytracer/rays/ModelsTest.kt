package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.EPSILON
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class ModelsTest {
    @Test
    fun `An intersection encapsulates t and object`() {
        val s = Sphere()
        val i = Intersection(3.5, s)
        assert(i.t == 3.5)
        assert(i.body == s)
    }

    @Test
    fun `Aggregating intersections`() {
        val s = Sphere()
        val i1 = Intersection(1.0, s)
        val i2 = Intersection(2.0, s)
        val xs = intersections(i1, i2)
        assert(xs.size == 2)
        assert(xs[0].t == 1.0)
        assert(xs[1].t == 2.0)
    }

    @Test
    fun `The hit, when all intersections have positive t`() {
        val s = Sphere()
        val i1 = Intersection(1.0, s)
        val i2 = Intersection(2.0, s)
        val xs = intersections(i2, i1)

        assert(xs.hit() == i1)
    }

    @Test
    fun `The hit, when some intersections have negative t`() {
        val s = Sphere()
        val i1 = Intersection(-1.0, s)
        val i2 = Intersection(1.0, s)
        val xs = intersections(i2, i1)

        assert(xs.hit() == i2)
    }

    @Test
    fun `The hit, when all intersections have negative t`() {
        val s = Sphere()
        val i1 = Intersection(-2.0, s)
        val i2 = Intersection(-1.0, s)
        val xs = intersections(i2, i1)

        assertNull(xs.hit())
    }

    @Test
    fun `The hit is always the lowest non-negative intersection`() {
        val s = Sphere()
        val i1 = Intersection(5.0, s)
        val i2 = Intersection(7.0, s)
        val i3 = Intersection(-3.0, s)
        val i4 = Intersection(2.0, s)
        val xs = intersections(i1, i2, i3, i4)

        assert(xs.hit() == i4)
    }

    @Test
    fun `Precomputing the state of an intersection`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val shape = Sphere()
        val i = Intersection(4.0, shape)

        val comps = i.prepareComputation(r)

        assert(comps.t `~==` i.t)
        assert(comps.point `~==` point(0, 0, -1))
        assert(comps.eyev `~==` vector(0, 0, -1))
        assert(comps.normalv `~==` vector(0, 0, -1))
    }

    @Test
    fun `The hit, when an intersection occurs on the outside`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val shape = Sphere()
        val i = Intersection(4.0, shape)

        val comps = i.prepareComputation(r)

        assert(!comps.inside)
    }

    @Test
    fun `The hit, when an intersection occurs on the inside`() {
        val r = Ray(point(0, 0, 0), vector(0, 0, 1))
        val shape = Sphere()
        val i = Intersection(1.0, shape)

        val comps = i.prepareComputation(r)

        assert(comps.point `~==` point(0, 0, 1))
        assert(comps.eyev `~==` vector(0, 0, -1))
        assert(comps.inside)
        // normal would have been (0, 0, 1), but is inverted!
        assert(comps.normalv `~==` vector(0, 0, -1))
    }

    @Test
    fun `The hit should offset the point`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val shape = Sphere()
        shape.transform = identity().translate(0, 0,1)

        val i = Intersection(5.0, shape)

        val comps = i.prepareComputation(r)

        assert(comps.overPoint.z < - EPSILON /2)
        assert(comps.point.z > comps.overPoint.z)
    }
}