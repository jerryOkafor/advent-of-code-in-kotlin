package adventOfCode.aoc2023.day10

import core.geoemtry.Point
import utils.getPointsAdjacentToSelfInCardinal
import utils.moveCardinal
import utils.solve

fun main() = solve { lines ->
    val grid = lines.toGrid()
    val start = grid.entries.first { it.value == 'S' }.key
    val unexplored = mutableListOf(start)
    val explored = mutableSetOf<Point>()
    while (unexplored.isNotEmpty()) {
        val current = unexplored.removeFirst()
        explored += current
        pipes[grid[current]]!!.forEach { direction ->
            val point = current.moveCardinal(direction)
            if (point !in explored) {
                val pipe = grid[point]
                if (pipe != null && direction.reverse() in pipes[pipe]!!) unexplored += point
            }
        }
    }

    val expandedGrid = mutableMapOf<Point, Char>()
    grid.forEach { (point, char) ->
        val expandedPoint = Point(point.x * 3, point.y * 3)
        expandedGrid[expandedPoint] = if (char != '.' && point in explored) '#' else '.'
        expandedPoint.getPointsAdjacentToSelfInCardinal().forEach { expandedGrid[it] = '.' }
        if (point in explored) pipes[char]!!.forEach { expandedGrid[expandedPoint.moveCardinal(it)] = '#' }
    }

    val toFlood = mutableListOf(Point.ORIGIN)
    while (toFlood.isNotEmpty()) {
        val current = toFlood.removeFirst()
        expandedGrid[current] = '='
        toFlood += current.getPointsAdjacentToSelfInCardinal().filter { expandedGrid[it] == '.' && it !in toFlood }
    }

    grid.keys.count { expandedGrid[Point(it.x * 3, it.y * 3)] == '.' }
}