package adventOfCode.aoc2023.day02

import utils.solve

fun main() = solve { lines ->
    processInput(lines)
        .flatMap { games ->
            games.values.map { sets ->
                val maxRed = sets.maxBy { it["red"]!! }["red"]!!
                val maxGreen = sets.maxBy { it["green"]!! }["green"]!!
                val maxBlue = sets.maxBy { it["blue"]!! }["blue"]!!
                mapOf("red" to maxRed, "green" to maxGreen, "blue" to maxBlue)
            }
        }.sumOf {
            it["red"]!! * it["green"]!! * it["blue"]!!
        }
}