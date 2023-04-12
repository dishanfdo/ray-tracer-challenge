package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.*
import com.dishan.raytracer.util.`~==`
import kotlin.math.pow

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

fun Material.lighting(light: Light, point: Tuple, eye: Tuple, normal: Tuple): Color {
    // combine the surface color with the light's color/intensity
    val effectiveColor = color * light.intensity

    //find the direction to the light source
    val lightV = (light.position - point).normalized()

    // compute the ambient contribution
    val ambientC = effectiveColor * ambient

    // light_dot_normal represents the cosine of the angle between the
    // light vector and the normal vector. A negative number means the
    // light is on the other side of the surface.
    val lightDotNormal = lightV dot normal

    val diffuseC: Color
    val specularC: Color
    if (lightDotNormal < 0) {
        diffuseC = Color.Black
        specularC = Color.Black
    } else {
        // compute the diffuse contribution
        diffuseC = effectiveColor * diffuse * lightDotNormal

        // reflect_dot_eye represents the cosine of the angle between the
        // reflection vector and the eye vector. A negative number means the
        // light reflects away from the eye.
        val reflectV = -lightV.reflect(normal)
        val reflectDotEye = reflectV dot eye
        if (reflectDotEye <= 0) {
            specularC = Color.Black
        } else {
            // compute the specular contribution
            val factor = reflectDotEye.pow(shininess)
            specularC = light.intensity * specular * factor
        }
    }

    return ambientC + diffuseC + specularC
}