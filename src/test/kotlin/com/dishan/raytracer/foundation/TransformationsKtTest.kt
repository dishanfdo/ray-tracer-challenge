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


}