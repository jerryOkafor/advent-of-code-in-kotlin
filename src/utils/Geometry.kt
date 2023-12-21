/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 20/12/2023
*/

package utils

import core.geoemtry.Direction
import core.geoemtry.Direction8
import core.geoemtry.Point
import core.geoemtry.Vector3
import kotlin.math.absoluteValue


fun <T> Map<Point, T>.printArea(visualization: (T) -> Char = { it.toString()[0] }) {
    val xRange = keys.minOf { it.x }..keys.maxOf { it.x }
    val yRange = keys.minOf { it.y }..keys.maxOf { it.y }
    for (y in yRange) {
        for (x in xRange) {
            val value = get(Point(x, y))
            if (value != null) {
                print(visualization(value))
            } else {
                print(" ")
            }
        }
        println()
    }
}

@JvmName("printAreaBoolean")
fun Map<Point, Boolean>.printArea() {
    printArea { if (it) '█' else ' ' }
}

@JvmName("printAreaChar")
fun Map<Point, Char>.printArea() {
    printArea { it }
}

fun Point.moveCardinal(direction: Direction, distance: Int = 1) = when (direction) {
    Direction.EAST -> Point(x + distance, y)
    Direction.WEST -> Point(x - distance, y)
    Direction.NORTH -> Point(x, y - distance)
    Direction.SOUTH -> Point(x, y + distance)
}

fun Point.moveInterCardinal(direction: Direction8, distance: Int = 1) = when (direction) {
    Direction8.NORTH -> Point(x, y - distance)
    Direction8.NORTH_EAST -> Point(x + distance, y - distance)
    Direction8.EAST -> Point(x + distance, y)
    Direction8.SOUTH_EAST -> Point(x + distance, y + distance)
    Direction8.SOUTH -> Point(x, y + distance)
    Direction8.SOUTH_WEST -> Point(x - distance, y + distance)
    Direction8.WEST -> Point(x - distance, y)
    Direction8.NORTH_WEST -> Point(x - distance, y - distance)
}

fun Point.getPointsAdjacentToSelfInCardinal(): List<Point> = listOf(
    Point(x, y - 1),
    Point(x - 1, y),
    Point(x + 1, y),
    Point(x, y + 1),
)

fun Point.getPointsAdjacentToSelfInInterCardinal(): List<Point> = listOf(
    Point(x - 1, y - 1),
    Point(x, y - 1),
    Point(x + 1, y - 1),
    Point(x - 1, y),
    Point(x + 1, y),
    Point(x - 1, y + 1),
    Point(x, y + 1),
    Point(x + 1, y + 1),
)

fun Vector3.getAdjacent(): List<Vector3> {
    val adjacent = mutableListOf<Vector3>()
    for (x in -1..1) {
        for (y in -1..1) {
            for (z in -1..1) {
                if (x == 0 && y == 0 && z == 0) continue
                adjacent += (Vector3(x, y, z) + this)
            }
        }
    }
    return adjacent
}

fun Vector3.getAdjacentSides(): List<Vector3> {
    val adjacent = mutableListOf<Vector3>()
    for (x in -1..1) {
        for (y in -1..1) {
            for (z in -1..1) {
                if (x == 0 && y == 0 && z == 0) continue
                if (x.absoluteValue + y.absoluteValue + z.absoluteValue > 1) continue
                adjacent += (Vector3(x, y, z) + this)
            }
        }
    }
    return adjacent
}