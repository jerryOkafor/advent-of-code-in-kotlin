package adventOfCode.aoc2023.day16

import core.geoemtry.Direction
import core.geoemtry.Direction.*
import core.geoemtry.Point
import utils.moveCardinal
import utils.solve

fun main() = solve { lines ->
    val grid = lines.toGrid()

    data class Beam(val position: Point, val direction: Direction)

    val beams = mutableListOf(Beam(Point(-1, 0), EAST))
    val beamsHistory = mutableSetOf<Beam>()
    while (beams.isNotEmpty()) {
        val beam = beams.removeFirst()
        val next = beam.position.moveCardinal(beam.direction)
        if (next !in grid || beam.copy(position = next) in beamsHistory) continue else beamsHistory += beam.copy(
            position = next
        )
        beams += when (grid[next]) {
            '.' -> listOf(beam.direction)
            '/' -> listOf(mapOf(NORTH to EAST, EAST to NORTH, SOUTH to WEST, WEST to SOUTH)[beam.direction]!!)
            '\\' -> listOf(mapOf(NORTH to WEST, EAST to SOUTH, SOUTH to EAST, WEST to NORTH)[beam.direction]!!)
            '-' -> mapOf(
                NORTH to listOf(WEST, EAST),
                EAST to listOf(beam.direction),
                SOUTH to listOf(WEST, EAST),
                WEST to listOf(beam.direction)
            )[beam.direction]!!

            '|' -> mapOf(
                NORTH to listOf(beam.direction),
                EAST to listOf(NORTH, SOUTH),
                SOUTH to listOf(beam.direction),
                WEST to listOf(NORTH, SOUTH)
            )[beam.direction]!!

            else -> throw RuntimeException()
        }.map { Beam(next, it) }
    }
    beamsHistory.map { it.position }.toSet().size
}