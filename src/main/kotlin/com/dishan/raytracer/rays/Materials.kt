package com.dishan.raytracer.rays

import com.dishan.raytracer.foundation.Color

class Material(val color: Color, val ambient: Float, val diffuse: Float, val specular: Float, val shininess: Float)

fun material(): Material = Material(Color(1f, 1f, 1f), 0.1f, 0.9f, 0.9f, 200.0f)