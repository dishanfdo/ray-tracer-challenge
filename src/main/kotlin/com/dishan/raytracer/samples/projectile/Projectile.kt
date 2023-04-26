package com.dishan.raytracer.samples.projectile

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.output.toPPM
import java.io.File

data class Environment(val gravity: Tuple, val wind: Tuple)

data class Projectile(val position: Tuple, val velocity: Tuple)

fun tick(env: Environment, proj: Projectile): Projectile {
    val position = proj.position + proj.velocity
    val velocity = proj.velocity + env.gravity + env.wind
    return Projectile(position, velocity)
}

fun Projectile.drawOn(canvas: Canvas) {
    val x = position.x.toInt().coerceIn(0 until canvas.width)
    val y = (canvas.height - position.y.toInt()).coerceIn(0 until canvas.height)
    canvas[x, y] = Color.White
}

fun String.writeToFile(filePath: String) {
    File(filePath).writeText(this)
}

fun runSimulation(projectile: Projectile, env: Environment, canvas: Canvas) {
    var p = projectile
    while (p.position.y > 0) {
        p = tick(env, p)
        p.drawOn(canvas)
    }
}

fun main() {
    val start = point(0, 1, 0)
    val velocity = vector(1.0, 1.8, 0.0).normalized() * 11.25
    val p = Projectile(start, velocity)

    val gravity = vector(0.0, -0.1, 0.0)
    val wind = vector(-0.01, 0.0, 0.0)
    val env = Environment(gravity, wind)

    val canvas = Canvas(900, 550)
    runSimulation(p, env, canvas)
    canvas.toPPM().writeToFile("./output/projectile.ppm")
}