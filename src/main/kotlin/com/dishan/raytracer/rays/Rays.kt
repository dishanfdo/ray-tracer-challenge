package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Tuple

class Ray(val origin: Tuple, val direction: Tuple)

fun Ray.position(t: Float): Tuple = origin + direction * t