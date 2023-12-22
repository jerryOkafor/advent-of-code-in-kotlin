/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day03

sealed interface Element

typealias SchematicRow = MutableList<Element>

fun IntRange.expandBy(value: Int) = first - value..last + value

fun Int.toRange() = this..this


data class PartNumber(val value: Int, val row: Int, val xRange: IntRange) : Element {
    val adjacentColSpan = xRange.expandBy(1)
    val adjacentRowSpan = row.toRange().expandBy(1)
}

fun buildNumber(value: String, row: Int, xRange: IntRange): PartNumber =
    PartNumber(value = value.toInt(), row = row, xRange = xRange)

fun buildNumber(value: String, row: Int, xStart: Int, xEnd: Int): PartNumber =
    PartNumber(value = value.toInt(), row = row, xRange = xStart..xEnd)

data class Symbol(val value: Char, val row: Int, val xRange: IntRange) : Element {
    val col: Int = xRange.first //or xRange.last
}

fun buildSchematic(input: List<String>): List<SchematicRow> =
    input.foldIndexed(mutableListOf<SchematicRow>()) { index, acc, line ->
        val numbers = "\\d++".toRegex().findAll(line)
        val symbols = "[^.\\s0-9]".toRegex().findAll(line)

        val iterator = numbers.iterator()
        val symbolsItertor = symbols.iterator()

        val schematicRows = mutableListOf<Element>()
        while (iterator.hasNext()) {
            val i = iterator.next()
            schematicRows.add(buildNumber(value = i.value, row = index, xRange = i.range))
        }

        while (symbolsItertor.hasNext()) {
            val s = symbolsItertor.next()
            schematicRows.add(Symbol(value = s.value.toCharArray().first(), row = index, xRange = s.range))
        }

        acc.add(schematicRows)
        acc
    }

fun List<SchematicRow>.findPartNumbers(): Set<PartNumber> {
    val result = mutableSetOf<PartNumber>()

    //Use the windowed api here
    this.windowed(2).map { rowPair ->
        val symbols = rowPair.flatten().filterIsInstance<Symbol>()
        val partNumbers = rowPair.flatten().filterIsInstance<PartNumber>()

        partNumbers.filter { p ->
            symbols.any { s ->
                s.xRange.last() in p.adjacentColSpan
            }
        }.forEach { result.add(it) }
    }
    return result
}

fun List<Element>.findGearParts(): List<Pair<PartNumber, PartNumber>> {
    val partNumbers = this.filterIsInstance<PartNumber>()
    val symbolsThatCouldBeGears = this.filterIsInstance<Symbol>().filter { it.value == '*' }

    return symbolsThatCouldBeGears.map { s ->
        partNumbers.filter { p ->
            //Take all neighbors
            s.row in p.adjacentRowSpan && s.xRange.first in p.adjacentColSpan
        }
    }.filter {
        //take neighbors that adjacent to the gear symbol
        it.size == 2
    }.map { it[0] to it[1] }
}