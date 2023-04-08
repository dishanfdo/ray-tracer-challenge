package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class ColorTest {
    @Test
    fun `Colors are (red, green, blue) tuples`() {
        val c = Color(-0.5f, 0.4f, 1.7f)
        assert(c.red == -0.5f)
        assert(c.green == 0.4f)
        assert(c.blue == 1.7f)
    }

    @Test
    fun `Adding colors`() {
        val c1 = Color(0.9f, 0.6f, 0.75f)
        val c2 = Color(0.7f, 0.1f, 0.25f)

        assert((c1 + c2).closeEnough(Color(1.6f, 0.7f, 1.0f)))
    }

    @Test
    fun `Subtracting colors`() {
        val c1 = Color(0.9f, 0.6f, 0.75f)
        val c2 = Color(0.7f, 0.1f, 0.25f)

        assert((c1 - c2).closeEnough(Color(0.2f, 0.5f, 0.5f)))
    }

    @Test
    fun `Multiplying a color by a scalar`() {
        val c = Color(0.2f, 0.3f, 0.4f)

        assert((c * 2f).closeEnough(Color(0.4f, 0.6f, 0.8f)))
    }

    @Test
    fun `Multiplying colors`() {
        val c1 = Color(1f, 0.2f, 0.4f)
        val c2 = Color(0.9f, 1f, 0.1f)

        assert((c1 * c2).closeEnough(Color(0.9f, 0.2f, 0.04f)))
    }
}