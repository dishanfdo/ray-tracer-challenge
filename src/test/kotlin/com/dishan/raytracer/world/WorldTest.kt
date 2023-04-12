package com.dishan.raytracer.world

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.identity
import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.scale
import com.dishan.raytracer.rays.PointLight
import com.dishan.raytracer.rays.Sphere
import com.dishan.raytracer.rays.material
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
}