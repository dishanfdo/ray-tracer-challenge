package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.Tuple

interface Light {
    val position: Tuple
    val intensity: Color

    infix fun `~==`(other: Light): Boolean
}

class PointLight(override val position: Tuple, override val intensity: Color) : Light {
    override fun `~==`(other: Light): Boolean {
        if (other !is PointLight) return false
        return position `~==` other.position && intensity `~==` other.intensity
    }
}

