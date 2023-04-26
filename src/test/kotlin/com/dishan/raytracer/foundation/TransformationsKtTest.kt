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
        assert(halfQuarter * p `~==` point(0.0, sqrt(2.0) / 2, sqrt(2.0) / 2))
        assert(fullQuarter * p `~==` point(0, 0, 1))
    }

    @Test
    fun `The inverse of an x-rotation rotates in the opposite direction`() {
        val p = point(0, 1, 0)
        val halfQuarter = rotationX(PI / 4)
        val inv = halfQuarter.inversed()
        assert(inv * p `~==` point(0.0, sqrt(2.0) / 2, -sqrt(2.0) / 2))
    }

    @Test
    fun `Rotating a point around the y axis`() {
        val p = point(0, 0, 1)
        val halfQuarter = rotationY(PI / 4)
        val fullQuarter = rotationY(PI / 2)
        assert(halfQuarter * p `~==` point(sqrt(2.0) / 2, 0.0, sqrt(2.0) / 2))
        assert(fullQuarter * p `~==` point(1, 0, 0))
    }

    @Test
    fun `Rotating a point around the z axis`() {
        val p = point(0, 1, 0)
        val halfQuarter = rotationZ(PI / 4)
        val fullQuarter = rotationZ(PI / 2)
        assert(halfQuarter * p `~==` point(-sqrt(2.0) / 2, sqrt(2.0) / 2, 0.0))
        assert(fullQuarter * p `~==` point(-1, 0, 0))
    }

    @Test
    fun `A shearing transformation moves x in proportion to y`() {
        val transform = shearing(1, 0, 0, 0, 0, 0)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(5, 3, 4))
    }

    @Test
    fun `A shearing transformation moves x in proportion to z`() {
        val transform = shearing(0, 1, 0, 0, 0, 0)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(6, 3, 4))
    }

    @Test
    fun `A shearing transformation moves y in proportion to x`() {
        val transform = shearing(0, 0, 1, 0, 0, 0)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(2, 5, 4))
    }

    @Test
    fun `A shearing transformation moves y in proportion to z`() {
        val transform = shearing(0, 0, 0, 1, 0, 0)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(2, 7, 4))
    }

    @Test
    fun `A shearing transformation moves z in proportion to x`() {
        val transform = shearing(0, 0, 0, 0, 1, 0)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(2, 3, 6))
    }

    @Test
    fun `A shearing transformation moves z in proportion to y`() {
        val transform = shearing(0, 0, 0, 0, 0, 1)
        val p = point(2, 3, 4)
        assert(transform * p `~==` point(2, 3, 7))
    }

    @Test
    fun `Individual transformations are applied in sequence`() {
        val p = point(1, 0, 1)
        val a = rotationX(PI / 2)
        val b = scaling(5, 5, 5)
        val c = translation(10, 5, 7)

        // apply rotation first
        val p2 = a * p
        assert(p2 `~==` point(1, -1, 0))

        // then apply scaling
        val p3 = b * p2
        assert(p3 `~==` point(5, -5, 0))

        // then apply translation
        val p4 = c * p3
        assert(p4 `~==` point(15, 0, 7))
    }

    @Test
    fun `Chained transformations must be applied in reverse order`() {
        val p = point(1, 0, 1)
        val a = rotationX(PI / 2)
        val b = scaling(5, 5, 5)
        val c = translation(10, 5, 7)

        val t = c * b * a
        assert(t * p `~==` point(15, 0, 7))
    }

    @Test
    fun `Chain transformations with fluent API`() {
        val p = point(1, 0, 1)
        val t = identity()
            .rotateX(PI / 2)
            .scale(5, 5, 5)
            .translate(10, 5, 7)

        assert(t * p `~==` point(15, 0, 7))
    }

    @Test
    fun `The transformation matrix for the default orientation`() {
        val from = point(0, 0, 0)
        val to = point(0, 0, -1)
        val up = vector(0, 1, 0)

        val t = viewTransform(from, to, up)
        assert(t `~==` identity())
    }

    @Test
    fun `A view transformation matrix looking in positive z direction`() {
        val from = point(0, 0, 0)
        val to = point(0, 0, 1)
        val up = vector(0, 1, 0)

        val t = viewTransform(from, to, up)
        assert(t `~==` scaling(-1, 1, -1))
    }

    @Test
    fun `The view transformation moves the world`() {
        val from = point(0, 0, 8)
        val to = point(0, 0, 0)
        val up = vector(0, 1, 0)

        val t = viewTransform(from, to, up)
        assert(t `~==` translation(0, 0, -8))
    }

    @Test
    fun `An arbitrary view transformation`() {
        val from = point(1, 3, 2)
        val to = point(4, -2, 8)
        val up = vector(1, 1, 0)

        val t = viewTransform(from, to, up)
        val result = Matrix4(
            -0.50709, +0.50709, +0.67612, -2.36643,
            +0.76772, +0.60609, +0.12122, -2.82843,
            -0.35857, +0.59761, -0.71714, +0.00000,
            +0.00000, +0.00000, +0.00000, +1.00000,
        )

        assert(t `~==` result)
    }
}