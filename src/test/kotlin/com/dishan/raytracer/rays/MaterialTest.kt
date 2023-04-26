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

        assert(m.color `~==` Color(1.0, 1.0, 1.0))
        assert(m.ambient == 0.1)
        assert(m.diffuse == 0.9)
        assert(m.specular == 0.9)
        assert(m.shininess == 200.0)
    }

    @Test
    fun `Lighting with the eye between the light and the surface`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1.0, 1.0, 1.0))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.9, 1.9, 1.9))
    }

    @Test
    fun `Lighting with the eye between light and surface, eye offset 45 degrees`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0.0, sqrt(2.0) / 2, -sqrt(2.0) / 2)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1.0, 1.0, 1.0))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.0, 1.0, 1.0))
    }

    @Test
    fun `Lighting with eye opposite surface, light offset 45 degrees`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 10, -10), Color(1.0, 1.0, 1.0))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(0.7364, 0.7364, 0.7364))
    }

    @Test
    fun `Lighting with eye in the path of the reflection vector`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0.0, -sqrt(2.0) / 2, -sqrt(2.0) / 2)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 10, -10), Color(1.0, 1.0, 1.0))

        val result = m.lighting(light, position, eye, normal)

        assert(result `~==` Color(1.63639, 1.63639, 1.63639))
    }

    @Test
    fun `Lighting with the light behind the surface`() {
        val m = material()
        val position = point(0, 0, 0)

        val eye = vector(0, 0, -1)
        val normal = vector(0, 0, -1)
        val light = PointLight(point(0, 0, 10), Color(1.0, 1.0, 1.0))

        val result = m.lighting(light, position, eye, normal, false)

        assert(result `~==` Color(0.1, 0.1, 0.1))
    }

    @Test
    fun `Lighting with the surface in shadow`() {
        val m = material()
        val position = point(0, 0, 0)

        val eyev = vector(0, 0, -1)
        val normalv = vector(0, 0, -1)
        val light = PointLight(point(0, 0, -10), Color(1.0, 1.0, 1.0))
        val inShadow = true

        val result = m.lighting(light, position, eyev, normalv, inShadow)

        assert(result `~==` Color(0.1, 0.1, 0.1))
    }
}