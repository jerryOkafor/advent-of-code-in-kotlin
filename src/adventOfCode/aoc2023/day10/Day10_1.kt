package adventOfCode.aoc2023.day10


import utils.moveCardinal
import utils.solve

fun main() = solve { lines ->
    val grid = lines.toGrid()
    val startPoint = grid.entries.first { it.value == 'S' }.key
    val unexplored = mutableListOf(startPoint to 0)
    val explored = mutableMapOf(startPoint to 0)

    while (unexplored.isNotEmpty()) {
        val (current, distance) = unexplored.removeFirst()
        explored[current] = distance
        pipes[grid[current]]!!.forEach { direction ->
            val point = current.moveCardinal(direction)
            if (point !in explored.keys && point in grid.keys && direction.reverse() in pipes[grid[point]]!!) {
                unexplored += point to (distance + 1)
            }
        }
    }

    explored.values.max()

}