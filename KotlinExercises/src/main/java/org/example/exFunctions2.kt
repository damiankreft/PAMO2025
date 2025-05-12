package org.example.exFunctions2


import kotlin.math.PI
fun circleArea(radius: Int): Double = PI * radius * radius

/**
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 */
fun main() {
    println(circleArea(2)) // 12.566370614359172
}