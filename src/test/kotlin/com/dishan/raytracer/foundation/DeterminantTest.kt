package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

class DeterminantTest {
    @Test
    fun `Calculating the determinant of a 2x2 matrix`() {
        val a = Matrix2(
            1f, 5f,
            -3f, 2f,
        )

        assert(a.determinant `~==` 17f)
    }

    @Test
    fun `A sub-matrix of a 3x3 matrix is a 2x2 matrix`() {
        val a = Matrix3(
            1f, 5f, 0f,
            -3f, 2f, 7f,
            0f, 6f, -3f,
        )

        val expected = Matrix2(
            -3f, 2f,
            0f, 6f
        )

        assert(a.submatrix(0, 2) `~==` expected)
    }

    @Test
    fun `A submatrix of a 4x4 matrix is a 3x3 matrix`() {
        val a = Matrix4(
            -6f, 1f, 1f, 6f,
            -8f, 5f, 8f, 6f,
            -1f, 0f, 8f, 2f,
            -7f, 1f, -1f, 1f,
        )

        val expected = Matrix3(
            -6f, 1f, 6f,
            -8f, 8f, 6f,
            -7f, -1f, 1f,
        )

        assert(a.submatrix(2, 1) `~==` expected)
    }

    @Test
    fun `Calculating a minor of a 3x3 matrix`() {
        val a = Matrix3(
            -3f, 5f, 0f,
            2f, -1f, -7f,
            6f, -1f, 5f,
        )

        val b = a.submatrix(1, 0)
        assert(b.determinant `~==` 25f)
        assert(a.minor(1, 0) `~==` 25f)
    }

    @Test
    fun `Calculating a cofactor of a 3x3 matrix`() {
        val a = Matrix3(
            -3f, 5f, 0f,
            2f, -1f, -7f,
            6f, -1f, 5f,
        )

        assert(a.minor(0, 0) `~==` -12f)
        assert(a.cofactor(0, 0) `~==` -12f)
        assert(a.minor(1, 0) `~==` 25f)
        assert(a.cofactor(1, 0) `~==` -25f)
    }

    @Test
    fun `Calculating the determinant of a 3x3 matrix`() {
        val a = Matrix3(
            1f, 2f, 6f,
            -5f, 8f, -4f,
            2f, 6f, 4f,
        )

        assert(a.cofactor(0, 0) `~==` 56f)
        assert(a.cofactor(0, 1) `~==` 12f)
        assert(a.cofactor(0, 2) `~==` -46f)
        assert(a.determinant `~==` -196f)
    }

    @Test
    fun `Calculating the determinant of a 4x4 matrix`() {
        val a = Matrix4(
            -2f, -8f, 3f, 5f,
            -3f, 1f, 7f, 3f,
            1f, 2f, -9f, 6f,
            -6f, 7f, 7f, -9f,
        )

        assert(a.cofactor(0, 0) `~==` 690f)
        assert(a.cofactor(0, 1) `~==` 447f)
        assert(a.cofactor(0, 2) `~==` 210f)
        assert(a.cofactor(0, 3) `~==` 51f)
        assert(a.determinant `~==` -4071f)
    }
}