package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MatrixInversionTest {
    @Test
    fun `Testing an invertible matrix for invertibility`() {
        val a = Matrix4(
            6.0, 4.0, 4.0, 4.0,
            5.0, 5.0, 7.0, 6.0,
            4.0, -9.0, 3.0, -7.0,
            9.0, 1.0, 7.0, -6.0
        )

        assert(a.determinant `~==` -2120.0)
        assertTrue(a.isInvertible())
    }

    @Test
    fun `Testing a non-invertible matrix for invertibility`() {
        val a = Matrix4(
            -4.0, 2.0, -2.0, -3.0,
            9.0, 6.0, 2.0, 6.0,
            0.0, -5.0, 1.0, -5.0,
            0.0, 0.0, 0.0, 0.0,
        )

        assert(a.determinant `~==` 0.0)
        assertFalse(a.isInvertible())
    }

    @Test
    fun `Calculating the inverse of a matrix`() {
        val a = Matrix4(
            -5.0, 2.0, 6.0, -8.0,
            1.0, -5.0, 1.0, 8.0,
            7.0, 7.0, -6.0, -7.0,
            1.0, -3.0, 7.0, 4.0,
        )

        val b = a.inversed()
        assert(a.determinant `~==` 532.0)
        assert(a.cofactor(2, 3) `~==` -160.0)
        assert(b[3, 2] `~==` -160 / 532.0)
        assert(a.cofactor(3, 2) `~==` 105.0)
        assert(b[2, 3] `~==` 105 / 532.0)

        val expected = Matrix4(
            0.21805, 0.45113, 0.24060, -0.04511,
            -0.80827, -1.45677, -0.44361, 0.52068,
            -0.07895, -0.22368, -0.05263, 0.19737,
            -0.52256, -0.81391, -0.30075, 0.30639,
        )
        assert(b `~==` expected)
    }

    @Test
    fun `Calculating the inverse of another matrix`() {
        val a = Matrix4(
            8.0, -5.0, 9.0, 2.0,
            7.0, 5.0, 6.0, 1.0,
            -6.0, 0.0, 9.0, 6.0,
            -3.0, 0.0, -9.0, -4.0,
        )

        val expected = Matrix4(
            -0.15385, -0.15385, -0.28205, -0.53846,
            -0.07692, 0.12308, 0.02564, 0.03077,
            0.35897, 0.35897, 0.43590, 0.92308,
            -0.69231, -0.69231, -0.76923, -1.92308,
        )
        assert(a.inversed() `~==` expected)
    }

    @Test
    fun `Calculating the inverse of a third matrix`() {
        val a = Matrix4(
            9.0, 3.0, 0.0, 9.0,
            -5.0, -2.0, -6.0, -3.0,
            -4.0, 9.0, 6.0, 4.0,
            -7.0, 6.0, 6.0, 2.0,
        )

        println(a.inversed())

        val expected = Matrix4(
            -0.04074, -0.07778, 0.14444, -0.22222,
            -0.07778, 0.03333, 0.36667, -0.33333,
            -0.02901, -0.14630, -0.10926, 0.12963,
            0.17778, 0.06667, -0.26667, 0.33333,
        )
        assert(a.inversed() `~==` expected)
    }

    @Test
    fun `Multiplying a product by its inverse`() {
        val a = Matrix4(
            3.0, -9.0, 7.0, 3.0,
            3.0, -8.0, 2.0, -9.0,
            -4.0, 4.0, 4.0, 1.0,
            -6.0, 5.0, -1.0, 1.0,
        )

        val b = Matrix4(
            8.0, 2.0, 2.0, 2.0,
            3.0, -1.0, 7.0, 0.0,
            7.0, 0.0, 5.0, 4.0,
            6.0, -2.0, 0.0, 5.0,
        )

        assert(((a * b) * b.inversed()) `~==` a)
    }
}
