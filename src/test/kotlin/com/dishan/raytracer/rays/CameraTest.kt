package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.`~==`
import org.junit.jupiter.api.Test

import kotlin.math.PI
import kotlin.math.sqrt

class CameraTest {

    @Test
    fun `Constructing a camera`() {
        val hSize = 160
        val vSize = 120
        val fieldOfView = (PI / 2).toFloat()

        val c = camera(hSize, vSize, fieldOfView)

        assert(c.hSize == hSize)
        assert(c.vSize == vSize)
        assert(c.fieldOfView `~==` fieldOfView)
        assert(c.transform `~==` identity())
    }

    @Test
    fun `The pixel size for a horizontal canvas`() {
        val c = camera(200, 125, (PI / 2).toFloat())

        assert(c.pixelSize `~==` 0.01f)
    }

    @Test
    fun `The pixel size for a vertical canvas`() {
        val c = camera(125, 200, (PI / 2).toFloat())

        assert(c.pixelSize `~==` 0.01f)
    }

    @Test
    fun `Constructing a ray through the center of the canvas`() {
        val c = camera(201, 101, (PI / 2).toFloat())
        val r = c.rayForPixel(100, 50)

        assert(r.origin `~==` point(0, 0, 0))
        assert(r.direction `~==` vector(0, 0, -1))
    }

    @Test
    fun `Constructing a ray through a corner of the canvas`() {
        val c = camera(201, 101, (PI / 2).toFloat())
        val r = c.rayForPixel(0, 0)

        assert(r.origin `~==` point(0, 0, 0))
        assert(r.direction `~==` vector(0.66519f, 0.33259f, -0.66851f))
    }

    @Test
    fun `Constructing a ray when the camera is transformed`() {
        val c = camera(
            hSize = 201,
            vSize = 101,
            fieldOfView = (PI / 2).toFloat(),
            transform = rotationY(PI / 4) * translation(0, -2, 5),
        )

        val r = c.rayForPixel(100, 50)

        assert(r.origin `~==` point(0, 2, -5))
        assert(r.direction `~==` vector(sqrt(2f) / 2, 0.0f, -sqrt(2f) / 2))
    }
}