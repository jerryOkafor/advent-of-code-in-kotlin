package adventOfCode.aoc2023.day03

import utils.solve

fun main() = solve { lines ->
    //Go through each row, record numbers, index of the last digit
    val engineSchematic = buildSchematic(lines)
    val partNumbers = engineSchematic.findPartNumbers()

    partNumbers.sumOf { it.value }
}