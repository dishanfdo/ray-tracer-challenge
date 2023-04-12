package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color
import com.dishan.raytracer.foundation.Tuple

interface Light {
    val position: Tuple
    val intensity: Color
}

class PointLight(override val position: Tuple, override val intensity: Color): Light

