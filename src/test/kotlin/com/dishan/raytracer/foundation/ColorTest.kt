package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class ColorTest {
    @Test
    fun `Colors are (red, green, blue) tuples`() {
        val c = Color(-0.5, 0.4, 1.7)
        assert(c.red == -0.5)
        assert(c.green == 0.4)
        assert(c.blue == 1.7)
    }

    @Test
    fun `Adding colors`() {
        val c1 = Color(0.9, 0.6, 0.75)
        val c2 = Color(0.7, 0.1, 0.25)

        assert((c1 + c2).`~==`(Color(1.6, 0.7, 1.0)))
    }

    @Test
    fun `Subtracting colors`() {
        val c1 = Color(0.9, 0.6, 0.75)
        val c2 = Color(0.7, 0.1, 0.25)

        assert((c1 - c2).`~==`(Color(0.2, 0.5, 0.5)))
    }

    @Test
    fun `Multiplying a color by a scalar`() {
        val c = Color(0.2, 0.3, 0.4)

        assert((c * 2.0).`~==`(Color(0.4, 0.6, 0.8)))
    }

    @Test
    fun `Multiplying colors`() {
        val c1 = Color(1.0, 0.2, 0.4)
        val c2 = Color(0.9, 1.0, 0.1)

        assert((c1 * c2).`~==`(Color(0.9, 0.2, 0.04)))
    }
}