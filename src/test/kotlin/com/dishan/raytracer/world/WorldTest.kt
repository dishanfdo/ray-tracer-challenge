package com.dishan.raytracer.world

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.rays.PointLight
import com.dishan.raytracer.rays.Ray
import com.dishan.raytracer.rays.Sphere
import com.dishan.raytracer.rays.material
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
}