package com.dishan.raytracer.foundation

import com.dishan.raytracer.foundation.Type.*
import org.junit.jupiter.api.Test

class TupleTest {

    @Test
    fun `tuple with w=1 is a point`() {
        val tuple = Tuple(4.3f, -4.2f, 3.1f, 1.0f)

        assert(tuple.type == Point)
    }

    @Test
    fun `tuple with w=0 is a vector`() {
        val tuple = Tuple(4.3f, -4.2f, 3.1f, 0.0f)

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
}