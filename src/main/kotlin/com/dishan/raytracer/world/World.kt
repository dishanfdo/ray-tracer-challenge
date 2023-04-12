package com.dishan.raytracer.world

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.identity
import com.dishan.raytracer.foundation.point
import com.dishan.raytracer.foundation.scale
import com.dishan.raytracer.rays.*

class World(private val objects: MutableList<Object> = mutableListOf(), var light: Light? = null) {

    fun isEmpty(): Boolean = objects.isEmpty()

    operator fun contains(body: Object): Boolean = objects.any { it `~==` body }
}

fun world(): World = World()
fun defaultWorld(): World {
    val light = PointLight(point(-10, 10, -10), Color(1f, 1f, 1f))
    val s1 = Sphere().apply {
        material = material(
            color = Color(0.8f, 1.0f, 0.6f),
            diffuse = 0.7f,
            specular = 0.2f,
        )
    }
    val s2 = Sphere().apply {
        transform = identity().scale(0.5f, 0.5f, 0.5f)
    }
    return World(objects = mutableListOf(s1, s2), light = light)
}