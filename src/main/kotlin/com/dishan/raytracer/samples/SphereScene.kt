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
    world.light = PointLight(point(-10, 10, -10), Color(1f, 1f, 1f))

    val floor = Sphere()
    floor.transform = identity()
        .scale(10f, 0.01f, 10f)
    floor.material = material(
        color = Color(1f, 0.9f, 0.9f),
        specular = 0f,
    )
    world.add(floor)

    val leftWall = Sphere()
    leftWall.transform = identity()
        .scale(10f, 0.01f, 10f)
        .rotateX(PI / 2)
        .rotateY(-PI / 4)
        .translate(0, 0, 5)
    leftWall.material = floor.material
    world.add(leftWall)

    val rightWall = Sphere()
    rightWall.transform = identity()
        .scale(10f, 0.01f, 10f)
        .rotateX(PI / 2)
        .rotateY(PI / 4)
        .translate(0, 0, 5)
    rightWall.material = floor.material
    world.add(rightWall)

    val middle = Sphere()
    middle.transform = identity().translate(-0.5f, 1f, 0.5f)
    middle.material = material(
        color = Color(0.1f, 1f, 0.5f),
        diffuse = 0.7f,
        specular = 0.3f,
    )
    world.add(middle)

    val right = Sphere()
    right.transform = identity()
        .scale(0.5f, 0.5f, 0.5f)
        .translate(1.5f, 0.5f, -0.5f)
    right.material = material(
        color = Color(0.5f, 1f, 0.1f),
        diffuse = 0.7f,
        specular = 0.3f,
    )
    world.add(right)

    val left = Sphere()
    left.transform = identity()
        .scale(0.33f, 0.33f, 0.33f)
        .translate(-1.5f, 0.33f, -0.75f)
    left.material = material(
        color = Color(1f, 0.8f, 0.1f),
        diffuse = 0.7f,
        specular = 0.3f,
    )
    world.add(left)

    val camera = camera(
        hSize = 100,
        vSize = 50,
        fieldOfView = (PI / 3).toFloat(),
        transform = viewTransform(
            from = point(x = 0f, y = 1.5f, z = -5f),
            to = point(0, 1, 0),
            up = vector(0, 1, 0)
        )
    )

    val canvas = camera.render(world)

    canvas.toPPM().writeToFile("./output/sphere_scene.ppm")
}