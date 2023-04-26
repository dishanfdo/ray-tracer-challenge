package com.dishan.raytracer.samples

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import com.dishan.raytracer.rays.PointLight
import com.dishan.raytracer.rays.Sphere
import com.dishan.raytracer.rays.camera
import com.dishan.raytracer.rays.material
import com.dishan.raytracer.samples.projectile.writeToFile
import com.dishan.raytracer.world.world
import kotlin.math.PI

fun main() {

    val world = world()
    world.light = PointLight(point(-10, 10, -10), Color(1.0, 1.0, 1.0))

    val floor = Sphere()
    floor.transform = identity()
        .scale(10.0, 0.01, 10.0)
    floor.material = material(
        color = Color(1.0, 0.9, 0.9),
        specular = 0.0,
    )
    world.add(floor)

    val leftWall = Sphere()
    leftWall.transform = identity()
        .scale(10.0, 0.01, 10.0)
        .rotateX(PI / 2)
        .rotateY(-PI / 4)
        .translate(0, 0, 5)
    leftWall.material = floor.material
    world.add(leftWall)

    val rightWall = Sphere()
    rightWall.transform = identity()
        .scale(10.0, 0.01, 10.0)
        .rotateX(PI / 2)
        .rotateY(PI / 4)
        .translate(0, 0, 5)
    rightWall.material = floor.material
    world.add(rightWall)

    val middle = Sphere()
    middle.transform = identity().translate(-0.5, 1.0, 0.5)
    middle.material = material(
        color = Color(0.1, 1.0, 0.5),
        diffuse = 0.7,
        specular = 0.3,
    )
    world.add(middle)

    val right = Sphere()
    right.transform = identity()
        .scale(0.5, 0.5, 0.5)
        .translate(1.5, 0.5, -0.5)
    right.material = material(
        color = Color(0.5, 1.0, 0.1),
        diffuse = 0.7,
        specular = 0.3,
    )
    world.add(right)

    val left = Sphere()
    left.transform = identity()
        .scale(0.33, 0.33, 0.33)
        .translate(-1.5, 0.33, -0.75)
    left.material = material(
        color = Color(1.0, 0.8, 0.1),
        diffuse = 0.7,
        specular = 0.3,
    )
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

    canvas.toPPM().writeToFile("./output/sphere_scene.ppm")
}