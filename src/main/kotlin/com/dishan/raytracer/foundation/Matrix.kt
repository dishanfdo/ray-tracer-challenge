package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.Num
import com.dishan.raytracer.util.`~!=`
import com.dishan.raytracer.util.`~==`

open class Matrix(val rows: Int, val cols: Int) {

    constructor(rows: Int, cols: Int, vararg elements: Num) : this(rows, cols) {
        for (i in elements.indices) {
            this.elements[i] = elements[i]
        }
    }

    companion object {

        fun create(rows: Int, cols: Int, init: (Int, Int) -> Num): Matrix {
            val matrix = Matrix(rows, cols)
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    matrix[row, col] = init(row, col)
                }
            }
            return matrix
        }
    }

    private val elements = DoubleArray(rows * cols)

    infix fun `~==`(other: Matrix): Boolean {
        return this.rows == other.rows
                && this.cols == other.cols
                && (this.elements zip other.elements).all { (a, b) -> a.`~==`(b) }
    }

    infix fun `~!=`(other: Matrix): Boolean = !(this `~==` other)

    override fun toString(): String = buildString {
        for (row in 0 until rows) {
            append("|")
            for (col in 0 until cols) {
                val text = String.format("%.5f", this@Matrix[row, col]).padStart(10)
                append(text)
                if(col != cols - 1) append(" ")
            }
            append("|")
            appendLine()
        }
    }

    operator fun get(row: Int, col: Int): Num = elements[row * cols + col]

    operator fun set(row: Int, col: Int, value: Num) {
        elements[row * cols + col] = value
    }
}

class Matrix4(vararg elements: Num) : Matrix(4, 4, *elements) {
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

    val determinant: Num
        get() = this[0, 0] * cofactor(0, 0) +
                this[0, 1] * cofactor(0, 1) +
                this[0, 2] * cofactor(0, 2) +
                this[0, 3] * cofactor(0, 3)

    companion object {
        fun create(init: (Int, Int) -> Num) = create(4, 4, init)

        val identity: Matrix4
            get() = Matrix4(
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0,
            )
    }
}

class Matrix2(vararg elements: Num) : Matrix(2, 2, *elements) {
    val determinant: Num
        get() = this[0, 0] * this[1, 1] - this[0, 1] * this[1, 0]

    companion object {
        fun create(init: (Int, Int) -> Num) = create(2, 2, init)
    }
}

class Matrix3(vararg elements: Num) : Matrix(3, 3, *elements) {
    val determinant: Num
        get() = this[0, 0] * cofactor(0, 0) +
                this[0, 1] * cofactor(0, 1) +
                this[0, 2] * cofactor(0, 2)


    companion object {
        fun create(init: (Int, Int) -> Num) = create(3, 3, init)
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


fun Matrix3.minor(row: Int, col: Int): Num = submatrix(row, col).determinant

fun Matrix3.cofactor(row: Int, col: Int): Num {
    val sign = if ((row + col) % 2 == 0) 1 else -1
    return minor(row, col) * sign
}

fun Matrix4.minor(row: Int, col: Int): Num = submatrix(row, col).determinant

fun Matrix4.cofactor(row: Int, col: Int): Num {
    val sign = if ((row + col) % 2 == 0) 1 else -1
    return minor(row, col) * sign
}

fun Matrix4.isInvertible(): Boolean = determinant `~!=` 0.0

fun Matrix4.inversed(): Matrix4 {
    val determinant = determinant
    if (determinant `~==` 0.0) error("Matrix $this is not invertible")

    val inversed = Matrix4()
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            val c = cofactor(row, col)
            inversed[col, row] = c / determinant
        }
    }

    return inversed
}