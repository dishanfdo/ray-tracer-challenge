package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`

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
                && (this.elements zip other.elements).all { (a, b) -> a.`~==`(b) }
    }

    infix fun `~!=`(other: Matrix): Boolean = !(this `~==` other)

    operator fun get(row: Int, col: Int): Float = elements[row * cols + col]

    operator fun set(row: Int, col: Int, value: Float) {
        elements[row * cols + col] = value
    }
}

class Matrix4(vararg elements: Float) : Matrix(4, 4, *elements) {
    operator fun times(other: Matrix4): Matrix4 {
        val matrix = Matrix4()
        for (row in 0..3) {
            for (col in 0..3) {
                matrix[row, col] = this[row, 0] * other[0, col] +
                        this[row, 1] * other[1, col] +
                        this[row, 2] * other[2, col] +
                        this[row, 3] * other[3, col]

            }
        }
        return matrix
    }

    operator fun times(other: Tuple): Tuple {
        val x = this[0, 0] * other.x + this[0, 1] * other.y + this[0, 2] * other.z + this[0, 3] * other.w
        val y = this[1, 0] * other.x + this[1, 1] * other.y + this[1, 2] * other.z + this[1, 3] * other.w
        val z = this[2, 0] * other.x + this[2, 1] * other.y + this[2, 2] * other.z + this[2, 3] * other.w
        val w = this[3, 0] * other.x + this[3, 1] * other.y + this[3, 2] * other.z + this[3, 3] * other.w
        return tuple(x, y, z, w)
    }

    companion object {
        fun create(init: (Int, Int) -> Float) = create(4, 4, init)

        val identity: Matrix4
            get() = Matrix4(
                1f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f,
                0f, 0f, 1f, 0f,
                0f, 0f, 0f, 1f,
            )
    }
}

class Matrix2(vararg elements: Float) : Matrix(2, 2, *elements) {
    val determinant: Float = this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]

    companion object {
        fun create(init: (Int, Int) -> Float) = create(2, 2, init)
    }
}

class Matrix3(vararg elements: Float) : Matrix(3, 3, *elements) {
    companion object {
        fun create(init: (Int, Int) -> Float) = create(3, 3, init)
    }

}

fun Matrix3.submatrix(row: Int, col: Int): Matrix2 {
    val submatrix = Matrix2()

    var i = 0
    var j = 0
    for (x in 0 until rows) {
        if (x == row) continue
        for (y in 0 until cols) {
            if (y == col) continue
            submatrix[i, j] = this[x, y]
            j++
        }
        i++
        j = 0
    }

    return submatrix
}

fun Matrix4.submatrix(row: Int, col: Int): Matrix3 {
    val submatrix = Matrix3()

    var i = 0
    var j = 0
    for (x in 0 until rows) {
        if (x == row) continue
        for (y in 0 until cols) {
            if (y == col) continue
            submatrix[i, j] = this[x, y]
            j++
        }
        i++
        j = 0
    }

    return submatrix
}


fun Matrix4.transposed(): Matrix4 {
    val transposed = Matrix4()
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            transposed[row, col] = this[col, row]
        }
    }
    return transposed
}

