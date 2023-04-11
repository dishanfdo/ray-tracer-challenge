package com.dishan.raytracer.rays

interface Object

class Intersection(val t: Float, val body: Object)

fun intersections(vararg intersection: Intersection): List<Intersection> = listOf(*intersection)