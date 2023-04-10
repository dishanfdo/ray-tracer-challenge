package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun `Constructing and inspecting a 4x4 matrix`() {
        val matrix = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1.0f 0 to 1 -> 2.0f 0 to 2 -> 3.0f 0 to 3 -> 4.0f
                1 to 0 -> 5.5f 1 to 1 -> 6.5f 1 to 2 -> 7.5f 1 to 3 -> 8.5f
                2 to 0 -> 9.0f 2 to 1 -> 10.0f 2 to 2 -> 11.0f 2 to 3 -> 12.0f
                3 to 0 -> 13.5f 3 to 1 -> 14.5f 3 to 2 -> 15.5f 3 to 3 -> 16.5f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(1f, matrix[0, 0])
        assertEquals(4f, matrix[0, 3])
        assertEquals(5.5f, matrix[1, 0])
        assertEquals(7.5f, matrix[1, 2])
        assertEquals(11f, matrix[2, 2])
        assertEquals(13.5f, matrix[3, 0])
        assertEquals(15.5f, matrix[3, 2])
    }

    @Test
    fun `A 2x2 matrix ought to be representable`() {
        val matrix = Matrix2.create { row, col ->
            when (row to col) {
                0 to 0 -> -3f 0 to 1 -> 5f
                1 to 0 -> 1f 1 to 1 -> -2f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(-3f, matrix[0, 0])
        assertEquals(5f, matrix[0, 1])
        assertEquals(1f, matrix[1, 0])
        assertEquals(-2f, matrix[1, 1])
    }

    @Test
    fun `A 3x3 matrix ought to be representable`() {
        val matrix = Matrix3.create { row, col ->
            when (row to col) {
                0 to 0 -> -3f 0 to 1 -> 5f 0 to 2 -> 0f
                1 to 0 -> 1f 1 to 1 -> -2f 1 to 2 -> -7f
                2 to 0 -> 0f 2 to 1 -> 1f 2 to 2 -> 1f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assertEquals(-3f, matrix[0, 0])
        assertEquals(-2f, matrix[1, 1])
        assertEquals(1f, matrix[1, 0])
    }

    @Test
    fun `Matrix equality with identical matrices`() {
        val a = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1f 0 to 1 -> 2f 0 to 2 -> 3f 0 to 3 -> 4f
                1 to 0 -> 5f 1 to 1 -> 6f 1 to 2 -> 7f 1 to 3 -> 8f
                2 to 0 -> 9f 2 to 1 -> 8f 2 to 2 -> 7f 2 to 3 -> 6f
                3 to 0 -> 5f 3 to 1 -> 4f 3 to 2 -> 3f 3 to 3 -> 2f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        val b = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1f 0 to 1 -> 2f 0 to 2 -> 3f 0 to 3 -> 4f
                1 to 0 -> 5f 1 to 1 -> 6f 1 to 2 -> 7f 1 to 3 -> 8f
                2 to 0 -> 9f 2 to 1 -> 8f 2 to 2 -> 7f 2 to 3 -> 6f
                3 to 0 -> 5f 3 to 1 -> 4f 3 to 2 -> 3f 3 to 3 -> 2f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assert(a `~==` b)
    }

    @Test
    fun `Matrix equality with different matrices`() {
        val a = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 1f 0 to 1 -> 2f 0 to 2 -> 3f 0 to 3 -> 4f
                1 to 0 -> 5f 1 to 1 -> 6f 1 to 2 -> 7f 1 to 3 -> 8f
                2 to 0 -> 9f 2 to 1 -> 8f 2 to 2 -> 7f 2 to 3 -> 6f
                3 to 0 -> 5f 3 to 1 -> 4f 3 to 2 -> 3f 3 to 3 -> 2f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        val b = Matrix4.create { row, col ->
            when (row to col) {
                0 to 0 -> 2f 0 to 1 -> 3f 0 to 2 -> 4f 0 to 3 -> 5f
                1 to 0 -> 6f 1 to 1 -> 7f 1 to 2 -> 8f 1 to 3 -> 9f
                2 to 0 -> 8f 2 to 1 -> 7f 2 to 2 -> 6f 2 to 3 -> 5f
                3 to 0 -> 4f 3 to 1 -> 3f 3 to 2 -> 2f 3 to 3 -> 1f
                else -> error("Invalid element index: ($row, $col)")
            }
        }

        assert(a `~!=` b)
    }
}