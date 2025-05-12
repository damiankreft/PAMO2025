package org.example.exFunctions1

import kotlin.math.PI
fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

/**
 * Write a function called circleArea that
 * takes the radius of a circle in integer format as a parameter and outputs the area of that circle
 */
fun main() {
    println(circleArea(2)) // 12.566370614359172
}