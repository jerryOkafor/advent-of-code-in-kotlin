/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day10

import core.geoemtry.Direction
import core.geoemtry.Point

val pipes = mutableMapOf(
    'S' to listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST),
    '|' to listOf(Direction.SOUTH, Direction.NORTH),
    '-' to listOf(Direction.WEST, Direction.EAST),
    'L' to listOf(Direction.NORTH, Direction.EAST),
    'J' to listOf(Direction.NORTH, Direction.WEST),
    '7' to listOf(Direction.SOUTH, Direction.WEST),
    'F' to listOf(Direction.SOUTH, Direction.EAST)
)

fun List<String>.toGrid(): MutableMap<Point, Char> {
    val grid = mutableMapOf<Point, Char>()
    this.forEachIndexed { y, row ->
        row.forEachIndexed { x, c ->
            grid[Point(x, y)] = c
        }
    }

    return grid
}