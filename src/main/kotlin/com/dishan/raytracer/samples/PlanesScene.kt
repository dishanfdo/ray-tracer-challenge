package com.dishan.raytracer.samples

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import com.dishan.raytracer.rays.*
import com.dishan.raytracer.samples.projectile.writeToFile
import com.dishan.raytracer.world.world
import kotlin.math.PI

fun main() {
    val world = world()
    world.light = PointLight(point(-10, 10, -10), Color(1.0, 1.0, 1.0))

    val floor = Plane().apply {
        material = material(
            color = Color(1.0, 0.9, 0.9),
            specular = 0.0,
        )
    }
    world.add(floor)

    val backdrop = Plane().apply {
        transform = identity().rotateX(PI/2).translate(0, 0, 20)
        material = material(
            color = Color(1.0, 0.9, 0.9),
            specular = 0.0,
        )
    }
    world.add(backdrop)

    val middle = Sphere().apply {
        transform = identity().translate(-0.5, 1.0, 0.5)
        material = material(
            color = Color(0.1, 1.0, 0.5),
            diffuse = 0.7,
            specular = 0.3,
        )
    }
    world.add(middle)

    val right = Sphere().apply {
        transform = identity()
            .scale(0.5, 0.5, 0.5)
            .translate(1.5, 0.5, -0.5)
        material = material(
            color = Color(0.5, 1.0, 0.1),
            diffuse = 0.7,
            specular = 0.3,
        )
    }
    world.add(right)

    val left = Sphere().apply {
        transform = identity()
            .scale(0.33, 0.33, 0.33)
            .translate(-1.5, 0.33, -0.75)
        material = material(
            color = Color(1.0, 0.8, 0.1),
            diffuse = 0.7,
            specular = 0.3,
        )
    }
    world.add(left)


    val camera = camera(
        hSize = 1000,
        vSize = 500,
        fieldOfView = PI / 3,
        transform = viewTransform(
            from = point(x = 0.0, y = 1.5, z = -5.0),
            to = point(0, 1, 0),
            up = vector(0, 1, 0)
        )
    )

    val canvas = camera.render(world)

    canvas.toPPM().writeToFile("./output/plane_scene.ppm")
}