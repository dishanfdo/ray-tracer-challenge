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
        val light = PointLight(point(-10, 10, -10), Color(1f, 1f, 1f))
        val s1 = Sphere().apply {
            material = material(
                color = Color(0.8f, 1.0f, 0.6f),
                diffuse = 0.7f,
                specular = 0.2f,
            )
        }
        val s2 = Sphere().apply {
            transform = identity().scale(0.5f, 0.5f, 0.5f)
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
        assert(xs[0].t `~==` 4.0f)
        assert(xs[1].t `~==` 4.5f)
        assert(xs[2].t `~==` 5.5f)
        assert(xs[3].t `~==` 6.0f)
    }

    @Test
    fun `Shading an intersection`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val shape = w.first()
        val i = Intersection(4.0f, shape)

        val comps = i.prepareComputation(r)
        val c = w.shadeHit(comps)

        assert(c `~==` Color(0.38066f, 0.47583f, 0.2855f))
    }

    @Test
    fun `Shading an intersection from the inside`() {
        val w = defaultWorld()
        w.light = PointLight(point(0f, 0.25f, 0f), Color(1f, 1f, 1f))
        val r = Ray(point(0, 0, 0), vector(0, 0, 1))
        val shape = w[1]
        val i = Intersection(0.5f, shape)

        val comps = i.prepareComputation(r)
        val c = w.shadeHit(comps)

        assert(c `~==` Color(0.90498f, 0.90498f, 0.90498f))
    }

    @Test
    fun `The color when a ray misses`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 1, 0))

        val c = w.colorAt(r)
        assert(c `~==` Color(0f, 0f, 0f))
    }

    @Test
    fun `The color when a ray hits`() {
        val w = defaultWorld()
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))

        val c = w.colorAt(r)
        assert(c `~==` Color(0.38066f, 0.47583f, 0.2855f))
    }

    @Test
    fun `The color with an intersection behind the ray`() {
        val w = defaultWorld()
        val outer = w[0]
        outer.material = outer.material.copyWith(ambient = 1.0f)

        val inner = w[1]
        inner.material = inner.material.copyWith(ambient = 1.0f)

        val r = Ray(point(0f, 0f, 0.75f), vector(0, 0, -1))

        val c = w.colorAt(r)
        assert(c `~==` inner.material.color)
    }
}