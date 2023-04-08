package com.dishan.raytracer.foundation

import com.dishan.raytracer.util.closeEnough
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class VectorTest {
    @Test
    fun `computing the magnitude of vector(1, 0, 0)`() {
        val v = vector(1, 0, 0)

        assert(v.magnitude.closeEnough(1f))
    }

    @Test
    fun `computing the magnitude of vector(0, 1, 0)`() {
        val v = vector(0, 1, 0)

        assert(v.magnitude.closeEnough(1f))
    }

    @Test
    fun `computing the magnitude of vector(0, 0, 1)`() {
        val v = vector(0, 0, 1)

        assert(v.magnitude.closeEnough(1f))
    }

    @Test
    fun `computing the magnitude of vector(1, 2, 3)`() {
        val v = vector(1, 2, 3)

        assert(v.magnitude.closeEnough(sqrt(14f)))
    }

    @Test
    fun `computing the magnitude of vector(-1, -2, -3)`() {
        val v = vector(-1, -2, -3)

        assert(v.magnitude.closeEnough(sqrt(14f)))
    }

    @Test
    fun `normalizing vector(4, 0, 0) gives (1, 0, 0)`() {
        val v = vector(4, 0, 0)

        assert(v.normalized().closeEnough(vector(1, 0, 0)))
    }

    @Test
    fun `normalizing vector(1, 2, 3)`() {
        val v = vector(1, 2, 3)

        assert(v.normalized().closeEnough(vector(0.26726f, 0.53452f, 0.80178f)))
    }

    @Test
    fun `the magnitude of a normalized vector`() {
        val v = vector(1, 2, 3)
        val norm = v.normalized()

        assert(norm.magnitude.closeEnough(1f))
    }

    @Test
    fun `the dot product of two tuples`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)

        assert((a dot b).closeEnough(20f))
    }

    @Test
    fun `the cross product of two vectors`() {
        val a = vector(1, 2, 3)
        val b = vector(2, 3, 4)

        assert((a cross b).closeEnough(vector(-1, 2, -1)))
        assert((b cross a).closeEnough(vector(1, -2, 1)))
    }
}