package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.point
import org.junit.jupiter.api.Test

class PointLightTest {

    @Test
    fun `A point light has a position and intensity`() {
        val intensity = Color(1.0, 1.0, 1.0)
        val position = point(0, 0, 0)
        val light = PointLight(position, intensity)

        assert(light.position `~==` position)
        assert(light.intensity `~==` intensity)
    }
}