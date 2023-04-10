package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MatrixInversionTest {
    @Test
    fun `Testing an invertible matrix for invertibility`() {
        val a = Matrix4(
            6f, 4f, 4f, 4f,
            5f, 5f, 7f, 6f,
            4f, -9f, 3f, -7f,
            9f, 1f, 7f, -6f
        )

        assert(a.determinant `~==` -2120f)
        assertTrue(a.isInvertible())
    }

    @Test
    fun `Testing a non-invertible matrix for invertibility`() {
        val a = Matrix4(
            -4f, 2f, -2f, -3f,
            9f, 6f, 2f, 6f,
            0f, -5f, 1f, -5f,
            0f, 0f, 0f, 0f,
        )

        assert(a.determinant `~==` 0f)
        assertFalse(a.isInvertible())
    }

    @Test
    fun `Calculating the inverse of a matrix`() {
        val a = Matrix4(
            -5f, 2f, 6f, -8f,
            1f, -5f, 1f, 8f,
            7f, 7f, -6f, -7f,
            1f, -3f, 7f, 4f,
        )

        val b = a.inversed()
        assert(a.determinant `~==` 532f)
        assert(a.cofactor(2, 3) `~==` -160f)
        assert(b[3, 2] `~==` -160 / 532f)
        assert(a.cofactor(3, 2) `~==` 105f)
        assert(b[2, 3] `~==` 105 / 532f)

        val expected = Matrix4(
            0.21805f, 0.45113f, 0.24060f, -0.04511f,
            -0.80827f, -1.45677f, -0.44361f, 0.52068f,
            -0.07895f, -0.22368f, -0.05263f, 0.19737f,
            -0.52256f, -0.81391f, -0.30075f, 0.30639f,
        )
        assert(b `~==` expected)
    }

    @Test
    fun `Calculating the inverse of another matrix`() {
        val a = Matrix4(
            8f, -5f, 9f, 2f,
            7f, 5f, 6f, 1f,
            -6f, 0f, 9f, 6f,
            -3f, 0f, -9f, -4f,
        )

        val expected = Matrix4(
            -0.15385f, -0.15385f, -0.28205f, -0.53846f,
            -0.07692f, 0.12308f, 0.02564f, 0.03077f,
            0.35897f, 0.35897f, 0.43590f, 0.92308f,
            -0.69231f, -0.69231f, -0.76923f, -1.92308f,
        )
        assert(a.inversed() `~==` expected)
    }

    @Test
    fun `Calculating the inverse of a third matrix`() {
        val a = Matrix4(
            9f, 3f, 0f, 9f,
            -5f, -2f, -6f, -3f,
            -4f, 9f, 6f, 4f,
            -7f, 6f, 6f, 2f,
        )

        println(a.inversed())

        val expected = Matrix4(
            -0.04074f, -0.07778f, 0.14444f, -0.22222f,
            -0.07778f, 0.03333f, 0.36667f, -0.33333f,
            -0.02901f, -0.14630f, -0.10926f, 0.12963f,
            0.17778f, 0.06667f, -0.26667f, 0.33333f,
        )
        assert(a.inversed() `~==` expected)
    }

    @Test
    fun `Multiplying a product by its inverse`() {
        val a = Matrix4(
            3f, -9f, 7f, 3f,
            3f, -8f, 2f, -9f,
            -4f, 4f, 4f, 1f,
            -6f, 5f, -1f, 1f,
        )

        val b = Matrix4(
            8f, 2f, 2f, 2f,
            3f, -1f, 7f, 0f,
            7f, 0f, 5f, 4f,
            6f, -2f, 0f, 5f,
        )

        assert(((a * b) * b.inversed()) `~==` a)
    }
}
