package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

class TransformationsKtTest {

    @Test
    fun `Multiplying by a translation matrix`() {
        val transform = translation(5, -3, 2)
        val p = point(-3, 4, 5)
        assert(transform * p `~==` point(2, 1, 7))
    }

    @Test
    fun `Multiplying by the inverse of a translation matrix`() {
        val transform = translation(5, -3, 2)
        val inv = transform.inversed()
        val p = point(-3, 4, 5)
        assert(inv * p `~==` point(-8, 7, 3))
    }

    @Test
    fun `Translation does not affect vectors`() {
        val transform = translation(5, -3, 2)
        val v = vector(-3, 4, 5)
        assert(transform * v `~==` v)
    }

    @Test
    fun `A scaling matrix applied to a point`() {
        val transform = scaling(2, 3, 4)
        val p = point(-4, 6, 8)
        assert(transform * p `~==` point(-8, 18, 32))
    }

    @Test
    fun `A scaling matrix applied to a vector`() {
        val transform = scaling(2, 3, 4)
        val v = vector(-4, 6, 8)
        assert(transform * v `~==` vector(-8, 18, 32))
    }

    @Test
    fun `Multiplying by the inverse of a scaling matrix`() {
        val transform = scaling(2, 3, 4)
        val inv = transform.inversed()
        val v = vector(-4, 6, 8)
        assert(inv * v `~==` vector(-2, 2, 2))
    }

    @Test
    fun `Reflection is scaling by a negative value`() {
        val transform = scaling(-1, 1, 1)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(-2, 3, 4))
    }
}