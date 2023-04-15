package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import kotlin.math.tan

class Camera(val hSize: Int, val vSize: Int, val fieldOfView: Float, val transform: Matrix4) {
    val pixelSize: Float
    val halfWidth: Float
    val halfHeight: Float

    init {
        val halfView = tan(fieldOfView / 2)
        val aspect = hSize.toFloat() / vSize

        if (aspect >= 1) {
            halfWidth = halfView
            halfHeight = halfView / aspect
        } else {
            halfWidth = halfView * aspect
            halfHeight = halfView
        }

        pixelSize = (halfWidth * 2) / hSize
    }

    fun rayForPixel(x: Int, y: Int): Ray {
        // the offset from the edge of the canvas to the pixel's center
        val xOffset = (x + 0.5f) * pixelSize
        val yOffset = (y + 0.5f) * pixelSize

        // the untransformed coordinates of the pixel in world space.
        // (remember that the camera looks toward -z, so +x is to the *left*.)
        val worldX = halfWidth - xOffset
        val worldY = halfHeight - yOffset

        // using the camera matrix, transform the canvas point and the origin,
        // and then compute the ray's direction vector.
        // (remember that the canvas is at z=-1)
        val pixel = transform.inversed() * point(worldX, worldY, -1f)
        val origin = transform.inversed() * point(0, 0, 0)
        val direction = (pixel - origin).normalized()

        return Ray(origin, direction)
    }

}

fun camera(hSize: Int, vSize: Int, fieldOfView: Float, transform: Matrix4 = identity()): Camera {
    return Camera(hSize, vSize, fieldOfView, transform)
}