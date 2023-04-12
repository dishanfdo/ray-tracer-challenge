package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import org.junit.jupiter.api.Test

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
}