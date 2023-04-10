package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.closeEnough

open class Matrix(val rows: Int, val cols: Int) {

    constructor(rows: Int, cols: Int, vararg elements: Float) : this(rows, cols) {
        for (i in elements.indices) {
            this.elements[i] = elements[i]
        }
    }

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

    private val elements = FloatArray(rows * cols)

    infix fun `~==`(other: Matrix): Boolean {
        return this.rows == other.rows
                && this.cols == other.cols
                && (this.elements zip other.elements).all { (a, b) -> a.closeEnough(b) }
    }

    infix fun `~!=`(other: Matrix): Boolean = !(this `~==` other)

    operator fun get(row: Int, col: Int): Float = elements[row * cols + col]

    operator fun set(row: Int, col: Int, value: Float) {
        elements[row * cols + col] = value
    }
}

class Matrix4(vararg elements: Float) : Matrix(4, 4, *elements) {

    companion object {
        fun create(init: (Int, Int) -> Float) = create(4, 4, init)
    }
}

class Matrix2(vararg elements: Float) : Matrix(2, 2, *elements) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(2, 2, init)
    }
}

class Matrix3(vararg elements: Float) : Matrix(3, 3, *elements) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(3, 3, init)
    }
}