package com.dishan.raytracer.foundation

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CanvasTest {

    @Test
    fun `Creating a canvas`() {
        val canvas = Canvas(10, 20)

        assertEquals(10, canvas.width)
        assertEquals(20, canvas.height)
        assert(canvas.pixels.all { it == Color.Black })
    }

    @Test
    fun `Writing pixels to a canvas`() {
        val canvas = Canvas(10, 20)
        val red = Color(1f, 0f, 0f)

        canvas[2, 3] = red

        assertEquals(red, canvas[2, 3])
    }
}