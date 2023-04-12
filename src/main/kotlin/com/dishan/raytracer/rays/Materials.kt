package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.util.`~==`

class Material(val color: Color, val ambient: Float, val diffuse: Float, val specular: Float, val shininess: Float) {
    infix fun `~==`(other: Material): Boolean {
        return color `~==` other.color &&
                ambient `~==` other.ambient &&
                diffuse `~==` other.diffuse &&
                specular `~==` other.specular &&
                shininess `~==` other.shininess
    }
}

fun material(
    color: Color = Color(1f, 1f, 1f),
    ambient: Float = 0.1f,
    diffuse: Float = 0.9f,
    specular: Float = 0.9f,
    shininess: Float = 200.0f
): Material = Material(
    color,
    ambient,
    diffuse,
    specular,
    shininess
)