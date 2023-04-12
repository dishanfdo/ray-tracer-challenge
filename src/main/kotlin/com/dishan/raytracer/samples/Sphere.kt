package com.dishan.raytracer.samples

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import com.dishan.raytracer.rays.Ray
import com.dishan.raytracer.rays.Sphere
import com.dishan.raytracer.rays.hit
import com.dishan.raytracer.samples.projectile.writeToFile

fun main() {
    val rayOrigin = point(0, 0, -5)
    val wallZ = 10f
    val wallSize = 7.0f
    val canvasPixels = 100
    val pixelSize = wallSize / canvasPixels
    val half = wallSize / 2

    val canvas = Canvas(canvasPixels, canvasPixels)
    val color = Color(1f, 0f, 0f)
    val shape = Sphere()
    // shape.transform = shearing(1) * scaling(0.5f, 1f, 1f)

    for (y in 0 until canvas.height) {
        val worldY = half - pixelSize * y
        for (x in 0 until canvas.width) {
            val worldX = -half + pixelSize * x
            val position = point(worldX, worldY, wallZ)

            val ray = Ray(rayOrigin, (position - rayOrigin).normalized())
            val xs = shape.intersect(ray)

            if (xs.hit() != null) {
                canvas[x, y] = color
            }
        }
    }

    canvas.toPPM().writeToFile("./output/sphere.ppm")
}