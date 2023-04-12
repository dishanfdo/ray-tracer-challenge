package com.dishan.raytracer.samples

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import com.dishan.raytracer.rays.*
import com.dishan.raytracer.samples.projectile.writeToFile

fun main() {
    val rayOrigin = point(0, 0, -5)
    val wallZ = 10f
    val wallSize = 7.0f
    val canvasPixels = 500
    val pixelSize = wallSize / canvasPixels
    val half = wallSize / 2

    val canvas = Canvas(canvasPixels, canvasPixels)
    val shape = Sphere()
    shape.material = material(color = Color(1f, 0.2f, 1f))
    // shape.transform = identity().scale(x = 0.5f ).rotateZ(PI / 2)

    val lightPosition = point(-10, 10, -10)
    val lightColor = Color.White
    val light = PointLight(lightPosition, lightColor)

    for (y in 0 until canvas.height) {
        val worldY = half - pixelSize * y
        for (x in 0 until canvas.width) {
            val worldX = -half + pixelSize * x
            val position = point(worldX, worldY, wallZ)

            val ray = Ray(rayOrigin, (position - rayOrigin).normalized())
            val xs = shape.intersect(ray)

            val hit = xs.hit()
            if (hit != null) {
                val point = ray.position(hit.t)
                val normal = hit.body.normalAt(point)
                val eye = -ray.direction

                val color = hit.body.material.lighting(light, point, eye, normal)
                canvas[x, y] = color
            }
        }
    }

    canvas.toPPM().writeToFile("./output/lighted_sphere.ppm")
}