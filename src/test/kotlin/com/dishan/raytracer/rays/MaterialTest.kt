package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.vector
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class MaterialTest {

    @Test
    fun `The default material`() {
        val m = material()

        assert(m.color `~==` Color(1f, 1f, 1f))
        assert(m.ambient == 0.1f)
        assert(m.diffuse == 0.9f)
        assert(m.specular == 0.9f)
        assert(m.shininess == 200.0f)
    }

    @Test
    fun `Lighting with the eye between the light and the surface`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1f, 1f, 1f))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.9f, 1.9f, 1.9f))
    }

    @Test
    fun `Lighting with the eye between light and surface, eye offset 45 degrees`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0f, sqrt(2f) / 2, -sqrt(2f) / 2)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1f, 1f, 1f))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.0f, 1.0f, 1.0f))
    }

    @Test
    fun `Lighting with eye opposite surface, light offset 45 degrees`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 10, -10), Color(1f, 1f, 1f))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(0.7364f, 0.7364f, 0.7364f))
    }

    @Test
    fun `Lighting with eye in the path of the reflection vector`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0f, -sqrt(2f) / 2, -sqrt(2f) / 2)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 10, -10), Color(1f, 1f, 1f))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.63638f, 1.63638f, 1.63638f))
    }

    @Test
    fun `Lighting with the light behind the surface`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, 10), Color(1f, 1f, 1f))

        val result = m.lighting(light, position, eye, normal, false)

        assert(result `~==` Color(0.1f, 0.1f, 0.1f))
    }

    @Test
    fun `Lighting with the surface in shadow`() {
        val m = material()
        val position = point(0, 0, 0)

        val eyev = vector(0, 0, -1)
        val normalv = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1f, 1f, 1f))
        val inShadow = true

        val result = m.lighting(light, position, eyev, normalv, inShadow)

        assert(result `~==` Color(0.1f, 0.1f, 0.1f))
    }
}