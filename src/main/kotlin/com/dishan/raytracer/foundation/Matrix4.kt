package com.dishan.raytracer.foundation

class Matrix4 {

    companion object {
        const val rows = 4
        const val cols = 4

        fun create(init: (Int, Int) -> Float): Matrix4 {
            val matrix = Matrix4()
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    matrix[row, col] = init(row, col)
                }
            }
            return matrix
        }
    }

    private val elements = FloatArray(16)

    operator fun get(row: Int, col: Int): Float = elements[row * cols + col]

    operator fun set(row: Int, col: Int, value: Float) {
        elements[row * cols + col] = value
    }
}