package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class VectorTest {
    @Test
    fun `computing the magnitude of vector(1, 0, 0)`() {
        val v = vector(1, 0, 0)

        assert(v.magnitude `~==` 1.0)
    }

    @Test
    fun `computing the magnitude of vector(0, 1, 0)`() {
        val v = vector(0, 1, 0)

        assert(v.magnitude `~==` 1.0)
    }

    @Test
    fun `computing the magnitude of vector(0, 0, 1)`() {
        val v = vector(0, 0, 1)

        assert(v.magnitude `~==` 1.0)
    }

    @Test
    fun `computing the magnitude of vector(1, 2, 3)`() {
        val v = vector(1, 2, 3)

        assert(v.magnitude `~==` sqrt(14.0))
    }

    @Test
    fun `computing the magnitude of vector(-1, -2, -3)`() {
        val v = vector(-1, -2, -3)

        assert(v.magnitude `~==` sqrt(14.0))
    }

    @Test
    fun `normalizing vector(4, 0, 0) gives (1, 0, 0)`() {
        val v = vector(4, 0, 0)

        assert(v.normalized() `~==` vector(1, 0, 0))
    }

    @Test
    fun `normalizing vector(1, 2, 3)`() {
        val v = vector(1, 2, 3)

        assert(v.normalized() `~==` vector(0.26726, 0.53452, 0.80178))
    }

    @Test
    fun `the magnitude of a normalized vector`() {
        val v = vector(1, 2, 3)
        val norm = v.normalized()

        assert(norm.magnitude `~==` 1.0)
    }

    @Test
    fun `the dot product of two tuples`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)

        assert((a dot b) `~==` 20.0)
    }

    @Test
    fun `the cross product of two vectors`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)

        assert((a cross b) `~==` vector(-1, 2, -1))
        assert((b cross a) `~==` vector(1, -2, 1))
    }
}