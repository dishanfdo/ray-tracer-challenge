package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class TupleArithmeticTest {
    @Test
    fun `adding two tuples`() {
        val a1 = Tuple(3, -2, 5, 1)
        val a2 = Tuple(-2, 3, 1, 0)
        val result = Tuple(1, 1, 6, 1)

        assert((a1 + a2).closeEnough(result))
    }

    @Test
    fun `subtracting two points`() {
        val p1 = point(3, -2, 5)
        val p2 = point(-2, 3, 1)

        assert((p1 - p2).closeEnough(vector(5, -5, 4)))
    }

    @Test
    fun `subtracting a vector from a point`() {
        val p = point(3, 2, 1)
        val v = vector(5, 6, 7)

        assert((p - v).closeEnough(point(-2, -4, -6)))
    }

    @Test
    fun `subtracting two vectors`() {
        val v1 = vector(3, 2, 1)
        val v2 = vector(5, 6, 7)

        assert((v1 - v2).closeEnough(vector(-2, -4, -6)))
    }

    @Test
    fun `subtracting a vector from the zero vector`() {
        val zero = vector(0, 0, 0)
        val v = vector(1, -2, 3)

        assert((zero - v).closeEnough(vector(-1, 2, -3)))
    }

    @Test
    fun `negating a tuple`() {
        val a = tuple(1, -2, 3, -4)
        assert((-a).closeEnough(tuple(-1, 2, -3, 4)))
    }

    @Test
    fun `multiplying a tuple by a scalar`() {
        val a = tuple(1, -2, 3, -4)
        assert((a * 3.5f).closeEnough(tuple(3.5f, -7f, 10.5f, -14f)))
    }

    @Test
    fun `multiplying a tuple by a fraction`() {
        val a = tuple(1, -2, 3, -4)
        assert((a * 0.5f).closeEnough(tuple(0.5f, -1f, 1.5f, -2f)))
    }

    @Test
    fun `dividing a tuple by a scalar`() {
        val a = tuple(1, -2, 3, -4)
        assert((a / 2f).closeEnough(tuple(0.5f, -1f, 1.5f, -2f)))
    }
}