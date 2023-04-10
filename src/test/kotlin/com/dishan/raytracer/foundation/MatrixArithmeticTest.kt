package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class MatrixArithmeticTest {

    @Test
    fun `Constructing and inspecting a 4x4 matrix method 2`() {
        val a = Matrix4(
            1f, 2f, 3f, 4f,
            5f, 6f, 7f, 8f,
            9f, 8f, 7f, 6f,
            5f, 4f, 3f, 2f,
        )

        val b = Matrix4(
            -2f, 1f, 2f, 3f,
            3f, 2f, 1f, -1f,
            4f, 3f, 6f, 5f,
            1f, 2f, 7f, 8f,
        )

        val expected = Matrix4(
            20f, 22f, 50f, 48f,
            44f, 54f, 114f, 108f,
            40f, 58f, 110f, 102f,
            16f, 26f, 46f, 42f,
        )

        assert((a * b) `~==` expected)
    }

    @Test
    fun `A matrix multiplied by a tuple`() {
        val a = Matrix4(
            1f, 2f, 3f, 4f,
            2f, 4f, 4f, 2f,
            8f, 6f, 4f, 1f,
            0f, 0f, 0f, 1f,
        )

        val b = tuple(1, 2, 3, 1)
        assert((a * b).`~==`(tuple(18, 24, 33, 1)))
    }
}