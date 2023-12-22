package adventOfCode.aoc2023.day12

import utils.solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val (arrangement, patternString) = line.split(" ")
        val newPatternString = "$patternString,".repeat(5).dropLast(1)
        val pattern = newPatternString.split(",").map { it.toInt() }
        // Make a new string of "arrangement" as five copies of itself, separated by a "?" character
        val newArrangement = "$arrangement?".repeat(5).dropLast(1)
        findPossibleArrangements(newArrangement, pattern)
    }
}