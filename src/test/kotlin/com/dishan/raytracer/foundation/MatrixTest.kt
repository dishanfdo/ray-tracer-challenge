package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun `Constructing and inspecting a 4x4 matrix`() {
        val matrix = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1.0
                0 to 1 -> 2.0
                0 to 2 -> 3.0
                0 to 3 -> 4.0
                1 to 0 -> 5.5
                1 to 1 -> 6.5
                1 to 2 -> 7.5
                1 to 3 -> 8.5
                2 to 0 -> 9.0
                2 to 1 -> 10.0
                2 to 2 -> 11.0
                2 to 3 -> 12.0
                3 to 0 -> 13.5
                3 to 1 -> 14.5
                3 to 2 -> 15.5
                3 to 3 -> 16.5
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(1.0, matrix[0, 0])
        assertEquals(4.0, matrix[0, 3])
        assertEquals(5.5, matrix[1, 0])
        assertEquals(7.5, matrix[1, 2])
        assertEquals(11.0, matrix[2, 2])
        assertEquals(13.5, matrix[3, 0])
        assertEquals(15.5, matrix[3, 2])
    }

    @Test
    fun `Constructing and inspecting a 4x4 matrix method 2`() {
        val matrix = Matrix4(
            1.0, 2.0, 3.0, 4.0,
            5.5, 6.5, 7.5, 8.5,
            9.0, 10.0, 11.0, 12.0,
            13.5, 14.5, 15.5, 16.5,
        )

        assertEquals(1.0, matrix[0, 0])
        assertEquals(4.0, matrix[0, 3])
        assertEquals(5.5, matrix[1, 0])
        assertEquals(7.5, matrix[1, 2])
        assertEquals(11.0, matrix[2, 2])
        assertEquals(13.5, matrix[3, 0])
        assertEquals(15.5, matrix[3, 2])
    }

    @Test
    fun `A 2x2 matrix ought to be representable`() {
        val matrix = Matrix2.create { row, col ->
            when (row to col) {
                0 to 0 -> -3.0
                0 to 1 -> 5.0
                1 to 0 -> 1.0
                1 to 1 -> -2.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(-3.0, matrix[0, 0])
        assertEquals(5.0, matrix[0, 1])
        assertEquals(1.0, matrix[1, 0])
        assertEquals(-2.0, matrix[1, 1])
    }

    @Test
    fun `A 3x3 matrix ought to be representable`() {
        val matrix = Matrix3.create { row, col ->
            when (row to col) {
                0 to 0 -> -3.0
                0 to 1 -> 5.0
                0 to 2 -> 0.0
                1 to 0 -> 1.0
                1 to 1 -> -2.0
                1 to 2 -> -7.0
                2 to 0 -> 0.0
                2 to 1 -> 1.0
                2 to 2 -> 1.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(-3.0, matrix[0, 0])
        assertEquals(-2.0, matrix[1, 1])
        assertEquals(1.0, matrix[1, 0])
    }

    @Test
    fun `Matrix equality with identical matrices`() {
        val a = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1.0
                0 to 1 -> 2.0
                0 to 2 -> 3.0
                0 to 3 -> 4.0
                1 to 0 -> 5.0
                1 to 1 -> 6.0
                1 to 2 -> 7.0
                1 to 3 -> 8.0
                2 to 0 -> 9.0
                2 to 1 -> 8.0
                2 to 2 -> 7.0
                2 to 3 -> 6.0
                3 to 0 -> 5.0
                3 to 1 -> 4.0
                3 to 2 -> 3.0
                3 to 3 -> 2.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        val b = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1.0
                0 to 1 -> 2.0
                0 to 2 -> 3.0
                0 to 3 -> 4.0
                1 to 0 -> 5.0
                1 to 1 -> 6.0
                1 to 2 -> 7.0
                1 to 3 -> 8.0
                2 to 0 -> 9.0
                2 to 1 -> 8.0
                2 to 2 -> 7.0
                2 to 3 -> 6.0
                3 to 0 -> 5.0
                3 to 1 -> 4.0
                3 to 2 -> 3.0
                3 to 3 -> 2.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assert(a `~==` b)
    }

    @Test
    fun `Matrix equality with different matrices`() {
        val a = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1.0
                0 to 1 -> 2.0
                0 to 2 -> 3.0
                0 to 3 -> 4.0
                1 to 0 -> 5.0
                1 to 1 -> 6.0
                1 to 2 -> 7.0
                1 to 3 -> 8.0
                2 to 0 -> 9.0
                2 to 1 -> 8.0
                2 to 2 -> 7.0
                2 to 3 -> 6.0
                3 to 0 -> 5.0
                3 to 1 -> 4.0
                3 to 2 -> 3.0
                3 to 3 -> 2.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        val b = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 2.0
                0 to 1 -> 3.0
                0 to 2 -> 4.0
                0 to 3 -> 5.0
                1 to 0 -> 6.0
                1 to 1 -> 7.0
                1 to 2 -> 8.0
                1 to 3 -> 9.0
                2 to 0 -> 8.0
                2 to 1 -> 7.0
                2 to 2 -> 6.0
                2 to 3 -> 5.0
                3 to 0 -> 4.0
                3 to 1 -> 3.0
                3 to 2 -> 2.0
                3 to 3 -> 1.0
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assert(a `~!=` b)
    }
}