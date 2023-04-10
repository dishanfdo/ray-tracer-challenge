package com.dishan.raytracer.foundation

open class Matrix(val rows: Int, val cols: Int) {

    companion object {

        fun create(rows: Int, cols: Int, init: (Int, Int) -> Float): Matrix {
            val matrix = Matrix(rows, cols)
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

class Matrix4 : Matrix(4, 4) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(4, 4, init)
    }
}

class Matrix2 : Matrix(2, 2) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(2, 2, init)
    }
}

class Matrix3 : Matrix(3, 3) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(3, 3, init)
    }
}