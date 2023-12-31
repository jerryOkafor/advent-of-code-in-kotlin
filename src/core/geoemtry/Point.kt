/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package core.geoemtry

import core.geoemtry.Direction.*
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

data class PointL(val x: Long, val y: Long) {

    companion object {
        val ORIGIN = PointL(0, 0)
    }

    fun move(direction: Direction, distance: Long = 1) = when (direction) {
        EAST -> PointL(x + distance, y)
        WEST -> PointL(x - distance, y)
        NORTH -> PointL(x, y - distance)
        SOUTH -> PointL(x, y + distance)
    }
}

data class Point(val x: Int, val y: Int) {

    companion object {
        val ORIGIN = Point(0, 0)
    }

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    private fun manhattan(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun distanceTo(other: Point) = sqrt((x - other.x).toDouble().pow(2) + (y - other.y).toDouble().pow(2))

    fun isAdjacentTo(other: Point) = manhattan(other) == 1

    fun angleTo(other: Point): Double {
        val angle = Math.toDegrees(atan2((other.y - y).toDouble(), (other.x - x).toDouble())) + 90
        return if (angle >= 0) angle else angle + 360
    }
}