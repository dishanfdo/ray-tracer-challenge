package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class MatrixArithmeticTest {

    @Test
    fun `Constructing and inspecting a 4x4 matrix method 2`() {
        val a = Matrix4(
            1.0, 2.0, 3.0, 4.0,
            5.0, 6.0, 7.0, 8.0,
            9.0, 8.0, 7.0, 6.0,
            5.0, 4.0, 3.0, 2.0,
        )

        val b = Matrix4(
            -2.0, 1.0, 2.0, 3.0,
            3.0, 2.0, 1.0, -1.0,
            4.0, 3.0, 6.0, 5.0,
            1.0, 2.0, 7.0, 8.0,
        )

        val expected = Matrix4(
            20.0, 22.0, 50.0, 48.0,
            44.0, 54.0, 114.0, 108.0,
            40.0, 58.0, 110.0, 102.0,
            16.0, 26.0, 46.0, 42.0,
        )

        assert((a * b) `~==` expected)
    }

    @Test
    fun `A matrix multiplied by a tuple`() {
        val a = Matrix4(
            1.0, 2.0, 3.0, 4.0,
            2.0, 4.0, 4.0, 2.0,
            8.0, 6.0, 4.0, 1.0,
            0.0, 0.0, 0.0, 1.0,
        )

        val b = tuple(1, 2, 3, 1)
        assert((a * b) `~==` tuple(18, 24, 33, 1))
    }

    @Test
    fun `Multiplying a matrix by the identity matrix`() {
        val a = Matrix4(
            0.0, 1.0, 2.0, 4.0,
            1.0, 2.0, 4.0, 8.0,
            2.0, 4.0, 8.0, 16.0,
            4.0, 8.0, 16.0, 32.0,
        )

        val identity = Matrix4.identity
        assert((a * identity) `~==` a)
    }

    @Test
    fun `Multiplying the identity matrix by a tuple`() {
        val a = tuple(1, 2, 3, 4)
        val identity = Matrix4.identity
        assert((identity * a) `~==` a)
    }

    @Test
    fun `Transposing a matrix`() {
        val a = Matrix4(
            0.0, 9.0, 3.0, 0.0,
            9.0, 8.0, 0.0, 8.0,
            1.0, 8.0, 5.0, 3.0,
            0.0, 0.0, 5.0, 8.0,
        )

        val expected = Matrix4(
            0.0, 9.0, 1.0, 0.0,
            9.0, 8.0, 8.0, 0.0,
            3.0, 0.0, 5.0, 5.0,
            0.0, 8.0, 3.0, 8.0,
        )

        assert(a.transposed() `~==` expected)
    }

}