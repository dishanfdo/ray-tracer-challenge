package com.dishan.raytracer.rays

import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class ModelsTest {
    @Test
    fun `An intersection encapsulates t and object`() {
        val s = Sphere()
        val i = Intersection(3.5f, s)
        assert(i.t == 3.5f)
        assert(i.body == s)
    }

    @Test
    fun `Aggregating intersections`() {
        val s = Sphere()
        val i1 = Intersection(1f, s)
        val i2 = Intersection(2f, s)
        val xs = intersections(i1, i2)
        assert(xs.size == 2)
        assert(xs[0].t == 1f)
        assert(xs[1].t == 2f)
    }

    @Test
    fun `The hit, when all intersections have positive t`() {
        val s = Sphere()
        val i1 = Intersection(1f, s)
        val i2 = Intersection(2f, s)
        val xs = intersections(i2, i1)

        assert(xs.hit() == i1)
    }

    @Test
    fun `The hit, when some intersections have negative t`() {
        val s = Sphere()
        val i1 = Intersection(-1f, s)
        val i2 = Intersection(1f, s)
        val xs = intersections(i2, i1)

        assert(xs.hit() == i2)
    }

    @Test
    fun `The hit, when all intersections have negative t`() {
        val s = Sphere()
        val i1 = Intersection(-2f, s)
        val i2 = Intersection(-1f, s)
        val xs = intersections(i2, i1)

        assertNull(xs.hit())
    }

    @Test
    fun `The hit is always the lowest non-negative intersection`() {
        val s = Sphere()
        val i1 = Intersection(5f, s)
        val i2 = Intersection(7f, s)
        val i3 = Intersection(-3f, s)
        val i4 = Intersection(2f, s)
        val xs = intersections(i1, i2, i3, i4)

        assert(xs.hit() == i4)
    }
}