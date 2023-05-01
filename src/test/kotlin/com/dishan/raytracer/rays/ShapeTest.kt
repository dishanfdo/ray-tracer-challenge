package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test
import kotlin.math.PI
import kotlin.math.sqrt

class TestShape : Shape() {

    var savedRay: Ray? = null

    override fun localNormalAt(point: Tuple): Tuple {
        return vector(point.x, point.y, point.z)
    }

    override fun localIntersect(localRay: Ray): Intersections {
        savedRay = localRay
        return emptyList()
    }

    override fun `~==`(other: Shape): Boolean {
        TODO("Not yet implemented")
    }

}

class ShapeTest {
    @Test
    fun `A shape's default transformation`() {
        val s = TestShape()
        assert(s.transform `~==` Matrix4.identity)
    }

    @Test
    fun `Changing a shape's transformation`() {
        val s = TestShape()
        val t = translation(2, 3, 4)
        s.transform = t

        assert(s.transform `~==` t)
    }

    @Test
    fun `A shape has a default material`() {
        val s = TestShape()
        val m = s.material

        assert(m `~==` material())
    }

    @Test
    fun `A shape may be assigned a material`() {
        val s = TestShape()
        val m = material(ambient = 1.0)
        s.material = m

        assert(s.material `~==` m)
    }

    @Test
    fun `Intersecting a scaled shape with a ray`() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val s = TestShape()
        s.transform = scaling(2, 2, 2)

        s.intersect(r)

        assert(s.savedRay!!.origin `~==` point(0.0, 0.0, -2.5))
        assert(s.savedRay!!.direction `~==` vector(0.0, 0.0, 0.5))
    }

    @Test
    fun `Intersecting a translated shape with a ray `() {
        val r = Ray(point(0, 0, -5), vector(0, 0, 1))
        val s = TestShape()
        s.transform = translation(5, 0, 0)

        s.intersect(r)

        assert(s.savedRay!!.origin `~==` point(-5, 0, -5))
        assert(s.savedRay!!.direction `~==` vector(0, 0, 1))
    }

    @Test
    fun `Computing the normal on a translated shape`() {
        val s = TestShape()
        s.transform = translation(0, 1, 0)
        val n = s.normalAt(point(0.0, 1.70711, -0.70711))

        assert(n `~==` vector(0.0, 0.70711, -0.70711))
    }

    @Test
    fun `Computing the normal on a transformed shape`() {
        val s = TestShape()
        val m = scaling(1.0, 0.5, 1.0) * rotationZ(PI / 5)
        s.transform = m

        val n = s.normalAt(point(0.0, sqrt(2.0) /2, -sqrt(2.0) /2))

        assert(n `~==` vector(0.0, 0.97014, -0.24254))
    }
}

