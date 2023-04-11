package com.dishan.raytracer.rays

interface Object

class Intersection(val t: Float, val body: Object)

typealias Intersections = List<Intersection>

fun intersections(vararg intersection: Intersection): Intersections = listOf(*intersection)