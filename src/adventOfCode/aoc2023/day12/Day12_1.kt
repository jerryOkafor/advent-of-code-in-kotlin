package adventOfCode.aoc2023.day12

import utils.solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val (arrangement, patternString) = line.split(" ")
        val pattern = patternString.split(",").map { it.toInt() }
        findPossibleArrangements(arrangement, pattern)
    }
}