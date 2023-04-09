package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Matrix4Test {

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
}