package adventOfCode.aoc2023.day06

import utils.solve

fun main() = solve { lines ->
    val cleanData = lines.map { it.substringAfter(":") }
        .map { it.trim() }
        .map { it.split("\\s+".toRegex()).map { it.trim().toInt() } }

    val (times, distances) = cleanData


    val totalPossibleWaysOneCanwin = times.zip(distances) { time, distance -> time to distance }
        .map { race ->
            var possibleWaysOneCanwin = 0
            val (time, distance) = race
            for (holdTime in 0 until time) {
                val gainedSpeed = holdTime * 1
                val timeLeftAfterhold = time - holdTime
                val totaldistanceBoatCanMove = gainedSpeed * timeLeftAfterhold
                if (totaldistanceBoatCanMove > distance) {
                    possibleWaysOneCanwin++
                }
            }

            possibleWaysOneCanwin
        }

    totalPossibleWaysOneCanwin.reduce { acc, i -> acc * i }
}