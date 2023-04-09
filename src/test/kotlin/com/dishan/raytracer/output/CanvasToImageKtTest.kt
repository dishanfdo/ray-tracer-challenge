package com.dishan.raytracer.output

import com.dishan.raytracer.foundation.Canvas
import com.dishan.raytracer.foundation.Color
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CanvasToImageKtTest {
    @Test
    fun `Constructing the PPM header`() {
        val canvas = Canvas(5, 3)

        val ppm = canvas.toPPM().lines()

        assertEquals("P3", ppm[0])
        assertEquals("5 3", ppm[1])
        assertEquals("255", ppm[2])
    }

    @Test
    fun `Constructing the PPM pixel data`() {
        val canvas = Canvas(5, 3)
        val c1 = Color(1.5f, 0f, 0f)
        val c2 = Color(0f, 0.5f, 0f)
        val c3 = Color(-0.5f, 0f, 1f)

        canvas[0, 0] = c1
        canvas[2, 1] = c2
        canvas[4, 2] = c3

        val ppm = canvas.toPPM().lines()

        assertEquals("255 0 0 0 0 0 0 0 0 0 0 0 0 0 0", ppm[3])
        assertEquals("0 0 0 0 0 0 0 128 0 0 0 0 0 0 0", ppm[4])
        assertEquals("0 0 0 0 0 0 0 0 0 0 0 0 0 0 255", ppm[5])
    }

    @Test
    fun `Splitting long lines in PPM files`() {
        val canvas = Canvas(10, 2)
        for (x in 0 until canvas.width) {
            for (y in 0 until canvas.height) {
                canvas[x, y] = Color(1f, 0.8f, 0.6f)
            }
        }

        val ppm = canvas.toPPM().lines()

        assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204", ppm[3])
        assertEquals("153 255 204 153 255 204 153 255 204 153 255 204 153", ppm[4])
        assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204", ppm[5])
        assertEquals("153 255 204 153 255 204 153 255 204 153 255 204 153", ppm[6])
    }

    @Test
    fun `PPM files are terminated by a newline character`() {
        val canvas = Canvas(5, 3)

        val ppm = canvas.toPPM()
        assertEquals('\n', ppm.last())
    }
}