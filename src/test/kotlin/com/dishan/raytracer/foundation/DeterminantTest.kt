package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

class DeterminantTest {
    @Test
    fun `Calculating the determinant of a 2x2 matrix`() {
        val a = Matrix2(
            1.0, 5.0,
            -3.0, 2.0,
        )

        assert(a.determinant `~==` 17.0)
    }

    @Test
    fun `A sub-matrix of a 3x3 matrix is a 2x2 matrix`() {
        val a = Matrix3(
            1.0, 5.0, 0.0,
            -3.0, 2.0, 7.0,
            0.0, 6.0, -3.0,
        )

        val expected = Matrix2(
            -3.0, 2.0,
            0.0, 6.0
        )

        assert(a.submatrix(0, 2) `~==` expected)
    }

    @Test
    fun `A submatrix of a 4x4 matrix is a 3x3 matrix`() {
        val a = Matrix4(
            -6.0, 1.0, 1.0, 6.0,
            -8.0, 5.0, 8.0, 6.0,
            -1.0, 0.0, 8.0, 2.0,
            -7.0, 1.0, -1.0, 1.0,
        )

        val expected = Matrix3(
            -6.0, 1.0, 6.0,
            -8.0, 8.0, 6.0,
            -7.0, -1.0, 1.0,
        )

        assert(a.submatrix(2, 1) `~==` expected)
    }

    @Test
    fun `Calculating a minor of a 3x3 matrix`() {
        val a = Matrix3(
            -3.0, 5.0, 0.0,
            2.0, -1.0, -7.0,
            6.0, -1.0, 5.0,
        )

        val b = a.submatrix(1, 0)
        assert(b.determinant `~==` 25.0)
        assert(a.minor(1, 0) `~==` 25.0)
    }

    @Test
    fun `Calculating a cofactor of a 3x3 matrix`() {
        val a = Matrix3(
            -3.0, 5.0, 0.0,
            2.0, -1.0, -7.0,
            6.0, -1.0, 5.0,
        )

        assert(a.minor(0, 0) `~==` -12.0)
        assert(a.cofactor(0, 0) `~==` -12.0)
        assert(a.minor(1, 0) `~==` 25.0)
        assert(a.cofactor(1, 0) `~==` -25.0)
    }

    @Test
    fun `Calculating the determinant of a 3x3 matrix`() {
        val a = Matrix3(
            1.0, 2.0, 6.0,
            -5.0, 8.0, -4.0,
            2.0, 6.0, 4.0,
        )

        assert(a.cofactor(0, 0) `~==` 56.0)
        assert(a.cofactor(0, 1) `~==` 12.0)
        assert(a.cofactor(0, 2) `~==` -46.0)
        assert(a.determinant `~==` -196.0)
    }

    @Test
    fun `Calculating the determinant of a 4x4 matrix`() {
        val a = Matrix4(
            -2.0, -8.0, 3.0, 5.0,
            -3.0, 1.0, 7.0, 3.0,
            1.0, 2.0, -9.0, 6.0,
            -6.0, 7.0, 7.0, -9.0,
        )

        assert(a.cofactor(0, 0) `~==` 690.0)
        assert(a.cofactor(0, 1) `~==` 447.0)
        assert(a.cofactor(0, 2) `~==` 210.0)
        assert(a.cofactor(0, 3) `~==` 51.0)
        assert(a.determinant `~==` -4071.0)
    }
}