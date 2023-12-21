/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 20/12/2023
*/

package utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/input/$name.txt").readLines()


/** Converts string to md5 hash. */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/** The cleaner shorthand for printing output. */
fun Any?.println(desc: String = "") = kotlin.io.println("${if (desc.isNotBlank()) "$desc:\n" else ""}$this")


fun List<String>.splitOnEmpty() = filter { it.isNotBlank() }

fun List<String>.chunkedByEmptyLine() = this.joinToString("\n")
    .split("\n\n")

/**
 * Right, Down, Left, Up directions on a 2D grid. Usage:
 * ```
 * val (di, dj) = RDLU_DIRS
 * ```
 */
val RDLU_DIRS: Pair<IntArray, IntArray> = Pair(
    intArrayOf(0, 1, 0, -1),
    intArrayOf(1, 0, -1, 0)
)

/** Splits into space-separate parts of input and maps each part. */
fun <R> List<String>.parts(map: (List<String>) -> R): List<R> = buildList {
    var cur = ArrayList<String>()
    for (s in this@parts) {
        if (s == "") {
            add(map(cur))
            cur = ArrayList()
            continue
        }
        cur.add(s)
    }
    if (cur.isNotEmpty()) add(map(cur))
}

private fun getInputFile(): File {
    val name = Throwable().stackTrace.first { it.className.contains("day") }.fileName
    val day = name?.substringBefore("_")?.removePrefix("Day")?.padStart(2, '0')
    val part = name?.substringAfter("_")?.removeSuffix(".kt")
    val file = File("src/day$day/input$part.txt")

    return if (file.readText().isBlank()) {
        File("src/day$day/input1.txt")
    } else file
}

private fun getInput(): String {
    return getInputFile().readText()
}

fun printInput(input: String) {
    if (input.contains("\n")) {
        if (input.lines().size >= 10) {
            println("Input:")
            println(input.lines().take(2).joinToString("\n"))
            println("[> Not showing ${input.lines().size - 4} lines <]")
            println(input.lines().takeLast(2).joinToString("\n"))
        } else {
            println("Input:\n$input")
        }
    } else {
        println("Input: $input")
    }
}

fun solveRaw(
    additionalTiming: Boolean = false,
    trim: Boolean = true,
    solve: (String) -> Any?
) {
    val input = getInput().let { if (trim) it.trim() else it }
    printInput(input)
    solveRaw(input, additionalTiming, solve)
}

fun solve(
    additionalTiming: Boolean = false,
    trim: Boolean = true,
    solve: (List<String>) -> Any?
) {
    val input = getInput().let { if (trim) it.trim() else it }
    printInput(input)
    solveRaw(input.lines(), additionalTiming, solve)
}

@OptIn(ExperimentalTime::class)
private fun <T> solveRaw(
    input: T,
    additionalTiming: Boolean = false,
    solve: (T) -> Any?
) {
    val (answer, duration) = measureTimedValue {
        solve(input).toString()
    }
    val time = "${String.format("%.3f", duration.inWholeMicroseconds / 1000.0)}ms"
    if (answer != "kotlin.Unit") {
        println("Out: $answer [$time]")

        //Past the answer to the clipboard
        Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(answer), null)
        Thread.sleep(200) // Wait so the system has chance to notice the clipboard change
    } else {
        println("No answer [$time]")
    }

    if (additionalTiming) {
        println("Rerunning for timing")
        val count = if (duration < 100.milliseconds) 1000 else 25
        val fastest = List(count) {
            measureTime { solve(input) }
        }.minOrNull()!!
        println("Fastest time: [${String.format("%.3f", fastest.inWholeMicroseconds / 1000.0)}ms]")
    }
}

fun solveRawSuspending(
    additionalTiming: Boolean = false,
    trim: Boolean = true,
    solve: suspend CoroutineScope.(String) -> Any?
) {
    val scope = CoroutineScope(Job())
    val input = getInput().let { if (trim) it.trim() else it }
    printInput(input)
    solveRaw(input, additionalTiming) { runBlocking { scope.solve(it) } }
}

fun solveSuspending(
    additionalTiming: Boolean = false,
    trim: Boolean = true,
    solve: suspend CoroutineScope.(List<String>) -> Any?
) {
    val scope = CoroutineScope(Job())
    val input = getInput().let { if (trim) it.trim() else it }
    printInput(input)
    solveRaw(input.lines(), additionalTiming) { runBlocking { scope.solve(it) } }
}