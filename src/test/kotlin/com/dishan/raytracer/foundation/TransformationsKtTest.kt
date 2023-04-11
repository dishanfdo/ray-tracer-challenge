package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.math.sqrt

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

    @Test
    fun `Rotating a point around the x axis`() {
        val p = point(0, 1, 0)
        val halfQuarter = rotationX(PI / 4)
        val fullQuarter = rotationX(PI / 2)
        assert(halfQuarter * p `~==` point(0f, sqrt(2f)/2, sqrt(2f)/2))
        assert(fullQuarter * p `~==` point(0, 0, 1))
    }

    @Test
    fun `The inverse of an x-rotation rotates in the opposite direction`() {
        val p = point(0, 1, 0)
        val halfQuarter = rotationX(PI / 4)
        val inv = halfQuarter.inversed()
        assert(inv * p `~==` point(0f, sqrt(2f)/2, -sqrt(2f)/2))
    }

    @Test
    fun `Rotating a point around the y axis`() {
        val p = point(0, 0, 1)
        val halfQuarter = rotationY(PI / 4)
        val fullQuarter = rotationY(PI / 2)
        assert(halfQuarter * p `~==` point(sqrt(2f)/2, 0f, sqrt(2f)/2))
        assert(fullQuarter * p `~==` point(1, 0, 0))
    }

    @Test
    fun `Rotating a point around the z axis`() {
        val p = point(0, 1, 0)
        val halfQuarter = rotationZ(PI / 4)
        val fullQuarter = rotationZ(PI / 2)
        assert(halfQuarter * p `~==` point(-sqrt(2f)/2, sqrt(2f)/2, 0f))
        assert(fullQuarter * p `~==` point(-1, 0, 0))
    }
}