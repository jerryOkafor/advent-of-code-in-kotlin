package adventOfCode.aoc2023.day09

import utils.solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val sequences = mutableListOf(line.split("\\s++".toRegex()).map { it.trim().toLong() })

        while (sequences.last().any { it != 0L })
            sequences += sequences.last().windowed(2).map { (num1, num2) -> num2 - num1 }

        sequences.sumOf { it.last() }
    }
}