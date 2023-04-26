package com.dishan.raytracer.foundation

import com.dishan.raytracer.foundation.Type.*
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class TupleTest {

    @Test
    fun `tuple with w=1 is a point`() {
        val tuple = Tuple(4.3, -4.2, 3.1, 1.0)

        assert(tuple.type == Point)
    }

    @Test
    fun `tuple with w=0 is a vector`() {
        val tuple = Tuple(4.3, -4.2, 3.1, 0.0)

        assert(tuple.type == Vector)
    }

    @Test
    fun `point() creates tuples with w=1`() {
        val point = point(4, -4, 3)

        assert(point `~==` Tuple(4, -4, 3, 1))
    }

    @Test
    fun `vector() creates tuples with w=0`() {
        val point = vector(4, -4, 3)

        assert(point `~==` Tuple(4, -4, 3, 0))
    }

    @Test
    fun `Reflecting a vector approaching at 45 degrees`() {
        val v = vector(1, -1, 0)
        val n = vector(0, 1, 0)
        val r = v.reflect(n)

        assert(r `~==` vector(1, 1, 0))
    }

    @Test
    fun `Reflecting a vector off a slanted surface`() {
        val v = vector(0, -1, 0)
        val n = vector(sqrt(2.0) / 2, sqrt(2.0) / 2, 0.0)
        val r = v.reflect(n)

        assert(r `~==` vector(1, 0, 0))
    }
}