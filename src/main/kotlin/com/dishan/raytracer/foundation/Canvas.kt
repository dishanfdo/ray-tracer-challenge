package com.dishan.raytracer.foundation

class Canvas(val width: Int, val height: Int) {
    private val _pixels = Array(width * height) { Color.Black }

    operator fun set(x: Int, y: Int, color: Color) {
        _pixels[y * width + x] = color
    }

    operator fun get(x: Int, y: Int): Color {
        return _pixels[y * width + x]
    }

    val pixels: Sequence<Color> = _pixels.asSequence()
}