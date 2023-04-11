package com.dishan.raytracer.rays

import org.junit.jupiter.api.Test

class IntersectionTest {
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
}