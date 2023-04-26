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
        1.0, 2.0, 7.0, 4.0,
        5.0, 3.0, 7.0, 8.0,
        4.0, 5.0, 8.0, 7.0,
        6.0, 1.0, 8.0, 9.0,
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