#!/usr/bin/env kotlin

import java.io.File
import java.time.LocalDate

/*
 * This script is a template for quickly creating Advent of code for a given day
 * More info: https://github.com/kaushikgopal/kotlin-scripts
 * To run kscripts do it like so:
    $>  brew install kotlin
    $>  chmod +x x.test.main.kts
    $>  ./x.test.main.kts
*/

println("Creating Advent of code!")
println("******* PROGRAM START ***************** ")
createAdventOfCode(args)
println("******* PROGRAM END ***************** ")

fun createAdventOfCode(args: Array<String>) {
    val date = LocalDate.now()
    val year = args.getOrNull(0) ?: date.year
    var day = args.getOrNull(1)
    val dayOfMonth = date.dayOfMonth.toString()

    if(day == null) {
        println("Please enter the day for the advent of code...")
        val dayInput = readlnOrNull()
        day = dayInput?.ifBlank { dayOfMonth } ?: dayOfMonth
    }
    day = day.toString().padStart(2, '0')

    val packageName = "adventOfCode.aoc$year.day$day"
    val dayDir = "src/adventOfCode/aoc$year/day$day"
    val solve = """
        package $packageName

        import utils.solve

        fun main() = solve { lines ->
            
        }
    """.trimIndent()
    val input = """"""

    File(dayDir).mkdirs()

    with(File("$dayDir/input1.txt")) {
        writeText(input)
    }

    with(File("$dayDir/input2.txt")) {
        writeText(input)
    }

    with(File("$dayDir/Day${day}_1.kt")) {
        writeText(solve)
    }
    with(File("$dayDir/Day${day}_2.kt")) {
        writeText(solve)
    }

    println("\uD83D\uDEE0️  AOC created for day $day in $year")
}