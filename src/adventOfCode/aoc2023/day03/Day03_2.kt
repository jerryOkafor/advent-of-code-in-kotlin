package adventOfCode.aoc2023.day03

import utils.solve

fun main() = solve { lines ->
    val engineSchematic = buildSchematic(lines)

    engineSchematic
        .flatten()
        .findGearParts()
        .sumOf { it.first.value * it.second.value }
}