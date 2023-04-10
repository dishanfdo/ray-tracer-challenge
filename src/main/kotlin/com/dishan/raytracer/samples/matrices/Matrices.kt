package com.dishan.raytracer.samples.matrices

import com.dishan.raytracer.foundation.Matrix4
import com.dishan.raytracer.foundation.inversed
import com.dishan.raytracer.foundation.transposed

fun main() {
    val identity = Matrix4.identity
    println("Inverse of identity matrix:")
    println(identity.inversed())
    println("Inverse of Identity == Identity? -> ${identity.inversed() `~==` identity}")

    val a = Matrix4(
        1f, 2f, 7f, 4f,
        5f, 3f, 7f, 8f,
        4f, 5f, 8f, 7f,
        6f, 1f, 8f, 9f,
    )
    println("A: ")
    println(a)
    println("A.inversed(): ")
    println(a.inversed())
    println("A * A.inversed():")
    println(a * a.inversed())
    println("A * A.inversed() == Identity? -> ${a * a.inversed() `~==` Matrix4.identity}")

    println("A.transposed(): ")
    println(a.transposed())
    println("A.transposed().inversed(): ")
    println(a.transposed().inversed())
    println("A.transposed().inversed(): ")
    println(a.transposed().inversed())
    println(
        "A.transposed().inversed() == A.inversed().transposed()? -> ${
            a.transposed().inversed() `~==` a.inversed().transposed()
        }"
    )
}