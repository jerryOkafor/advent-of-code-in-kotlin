package adventOfCode.aoc2023.day01

import utils.solve

fun main() = solve { lines ->
    val digitMap = buildMap {
        listOf(
            1 to "one",
            2 to "two",
            3 to "three",
            4 to "four",
            5 to "five",
            6 to "six",
            7 to "seven",
            8 to "eight",
            9 to "nine",
        ).forEach { (number, name) ->
            put(number.toString(), number)
            put(name, number)
        }
    }

    lines.sumOf { line ->
        //find the first occurrence of the keys
        val fistItemKey = line.findAnyOf(digitMap.keys)!!.second

        //find the last occurrence of the keys
        val lastItemKey = line.findLastAnyOf(digitMap.keys)!!.second

        //use the lookup map and transform
        val firstDigit = digitMap.getValue(fistItemKey)
        val lastDigit = digitMap.getValue(lastItemKey)

        "$firstDigit$lastDigit".toInt()
    }
}