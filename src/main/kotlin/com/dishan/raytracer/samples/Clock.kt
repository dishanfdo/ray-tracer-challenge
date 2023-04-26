package com.dishan.raytracer.samples

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import com.dishan.raytracer.samples.projectile.writeToFile
import kotlin.math.PI

fun main() {
    val canvasSize = 200
    val padding = 40
    val clockRadius = (canvasSize - padding) / 2.0

    val canvas = Canvas(canvasSize, canvasSize)
    val p = point(0, 1, 0)
    val scaling = scaling(clockRadius, clockRadius)
    val translation = translation(canvasSize / 2, canvasSize / 2)

    for (i in 1 .. 12) {
        val rotation = rotationZ((PI / 6) * i)
        val point = translation * scaling * rotation * p
        canvas[point.x.toInt(), point.y.toInt()] = Color.White
    }

    canvas.toPPM().writeToFile("./output/clock.ppm")
}