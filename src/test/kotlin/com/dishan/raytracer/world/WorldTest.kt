package com.dishan.raytracer.world

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.rays.*
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class WorldTest {

    @Test
    fun `Creating a world`() {
        val w = world()

        assert(w.isEmpty())
        assertNull(w.light)
    }

    @Test
    fun `The default world`() {
        val light = PointLight(point(-10, 10, -10), Color(1.0, 1.0, 1.0))
        val s1 = Sphere().apply {
            material = material(
                color = Color(0.8, 1.0, 0.6),
                diffuse = 0.7,
                specular = 0.2,
            )
        }
        val s2 = Sphere().apply {
            transform = identity().scale(0.5, 0.5, 0.5)
        }

        val w = defaultWorld()

        assert(w.light!! `~==` light)
        assert(s1 in w)
        assert(s2 in w)
    }

    @Test
    fun `Intersect a world with a ray`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))

        val xs = w.intersect(r)

        assert(xs.size == 4)
        assert(xs[0].t `~==` 4.0)
        assert(xs[1].t `~==` 4.5)
        assert(xs[2].t `~==` 5.5)
        assert(xs[3].t `~==` 6.0)
    }

    @Test
    fun `Shading an intersection`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val shape = w.first()
        val i = Intersection(4.0, shape)

        val comps = i.prepareComputation(r)
        val c = w.shadeHit(comps)

        assert(c `~==` Color(0.38066, 0.47583, 0.2855))
    }

    @Test
    fun `Shading an intersection from the inside`() {
        val w = defaultWorld()
        w.light = PointLight(point(0.0, 0.25, 0.0), Color(1.0, 1.0, 1.0))
        val r = Ray(point(0, 0, 0), vector(0, 0, 1))
        val shape = w[1]
        val i = Intersection(0.5, shape)

        val comps = i.prepareComputation(r)
        val c = w.shadeHit(comps)

        assert(c `~==` Color(0.90498, 0.90498, 0.90498))
    }

    @Test
    fun `The color when a ray misses`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 1, 0))

        val c = w.colorAt(r)
        assert(c `~==` Color(0.0, 0.0, 0.0))
    }

    @Test
    fun `The color when a ray hits`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))

        val c = w.colorAt(r)
        assert(c `~==` Color(0.38066, 0.47583, 0.2855))
    }

    @Test
    fun `The color with an intersection behind the ray`() {
        val w = defaultWorld()
        val outer = w[0]
        outer.material = outer.material.copyWith(ambient = 1.0)

        val inner = w[1]
        inner.material = inner.material.copyWith(ambient = 1.0)

        val r = Ray(point(0.0, 0.0, 0.75), vector(0, 0, -1))

        val c = w.colorAt(r)
        assert(c `~==` inner.material.color)
    }

    @Test
    fun `There is no shadow when nothing is collinear with point and light`() {
        val w = defaultWorld()
        val p = point(0, 10, 0)

        assertFalse(w.isShadowed(p))
    }

    @Test
    fun `The shadow when an object is between the point and the light`() {
        val w = defaultWorld()
        val p = point(10, -10, 10)

        assertTrue(w.isShadowed(p))
    }

    @Test
    fun `There is no shadow when an object is behind the light`() {
        val w = defaultWorld()
        val p = point(-20, 20, -20)

        assertFalse(w.isShadowed(p))
    }

    @Test
    fun `There is no shadow when an object is behind the point`() {
        val w = defaultWorld()
        val p = point(-2, 2, -2)

        assertFalse(w.isShadowed(p))
    }

    @Test
    fun `shadeHit() is given an intersection in shadow`() {
        val w = world()
        w.light = PointLight(point(0, 0, -10), Color(1.0, 1.0, 1.0))
        val s1 = Sphere()
        w.add(s1)

        val s2 = Sphere()
        s2.transform = identity().translate(0, 0, 10)
        w.add(s2)

        val r = Ray(point(0, 0, 5), vector(0, 0, 1))
        val i = Intersection(4.0, s2)
        val comps = i.prepareComputation(r)

        val c = w.shadeHit(comps)

        assert(c `~==` Color(0.1, 0.1, 0.1))
    }
}