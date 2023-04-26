package com.dishan.raytracer.world

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.rays.*

class World(private val objects: MutableList<Object> = mutableListOf(), var light: Light? = null) {

    fun isEmpty(): Boolean = objects.isEmpty()

    operator fun contains(body: Object): Boolean = objects.any { it `~==` body }

    operator fun get(i: Int): Object = objects[i]

    fun first(): Object = objects.first()

    fun add(body: Object) {
        objects.add(body)
    }

    fun intersect(ray: Ray): Intersections {
        return objects
            .map { body -> body.intersect(ray) }.flatten()
            .sortedBy { intersection -> intersection.t }
    }

    fun shadeHit(computation: Computation): Color {
        val light = light ?: error("No light source")
        return computation.body.material.lighting(
            light = light,
            point = computation.point,
            eye = computation.eyev,
            normal = computation.normalv,
            inShadow = isShadowed(computation.overPoint)
        )
    }

    fun colorAt(ray: Ray): Color {
        val intersections = intersect(ray)
        val hit = intersections.hit() ?: return Color.Black
        val computation = hit.prepareComputation(ray)
        return shadeHit(computation)
    }

    fun isShadowed(point: Tuple): Boolean {
        val light = light ?: error("No light source")
        val v = light.position - point
        val distance = v.magnitude
        val direction = v.normalized()

        val r = Ray(point, direction)
        val intersections = intersect(r)

        val h = intersections.hit()
        return h != null && h.t < distance
    }
}

fun world(): World = World()

fun defaultWorld(): World {
    val light = PointLight(point(-10, 10, -10), Color(1.0, 1.0, 1.0))
    val s1 = Sphere().apply {
        material = material(
            color = Color(0.8, 1.0, 0.6),
            diffuse = 0.7,
            specular = 0.2,
        )
    }
    val s2 = Sphere().apply {
        transform = identity().scale(0.5, 0.5, 0.5)
    }
    return World(objects = mutableListOf(s1, s2), light = light)
}