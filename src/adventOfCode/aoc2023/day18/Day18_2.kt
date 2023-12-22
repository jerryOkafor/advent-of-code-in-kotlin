package adventOfCode.aoc2023.day18

import core.geoemtry.Direction.*
import core.geoemtry.PointL
import utils.solve

fun main() = solve { lines ->
    var current = PointL.ORIGIN
    val points = mutableListOf(current)
    var area = 0L
    var perimeter = 0L
    lines.map { it.substringAfter("(").substringBefore(")") }.forEach { line ->
        val direction = listOf(EAST, SOUTH, WEST, NORTH)[line.last().digitToInt()]
        val distance = Integer.parseInt(line.drop(1).dropLast(1), 16)
        perimeter += distance
        current = current.move(direction, distance.toLong())
        area += points.last().x * current.y - current.x * points.last().y
        points += current
    }
    area / 2 - perimeter / 2 + 1 + perimeter
}