package com.dishan.raytracer.samples.projectile

import com.dishan.raytracer.foundation.Tuple
import com.dishan.raytracer.foundation.normalized
import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.vector

data class Environment(val gravity: Tuple, val wind: Tuple)

data class Projectile(val position: Tuple, val velocity: Tuple)

fun tick(env: Environment, proj: Projectile): Projectile {
    val position = proj.position + proj.velocity
    val velocity = proj.velocity + env.gravity + env.wind
    return Projectile(position, velocity)
}

fun main() {
    // projectile starts one unit above the origin.
    // velocity is normalized to 1 unit/tick.
    var p = Projectile(point(0, 1, 0), vector(1, 1, 0).normalized())
    // gravity -0.1 unit/tick, and wind is -0.01 unit/tick.
    val env = Environment(vector(0f, -0.1f, 0f), vector(-0.01f, 0f, 0f))

    var ticks = 0
    while (p.position.y > 0) {
        ticks++
        p = tick(env, p)
        println("$ticks: $p")
    }
}