package adventOfCode.aoc2023.day21

import utils.RDLU_DIRS
import utils.solve
import utils.toCharArray2
import utils.toPoints

fun main() = solve { lines ->
    val a = lines.toCharArray2()
    val (n, m) = a.toPoints()
    val (di, dj) = RDLU_DIRS

    var answer: Int = 0

    for (i0 in 0..<n) for (j0 in 0..<m) if (a[i0][j0] == 'S') {
        data class Q(val i: Int, val j: Int, val s: Int)

        val q = ArrayDeque<Q>()
        val u = Array(n) { IntArray(m) { -1 } }
        fun enq(i: Int, j: Int, s: Int) {
            if (i !in 0..<n || j !in 0..<m) return
            if (u[i][j] == s) return
            if (a[i][j] == '#') return
            u[i][j] = s
            q.add(Q(i, j, s))
        }
        enq(i0, j0, 0)
        while (true) {
            val (i, j, s) = q.removeFirst()
            if (s == 64) break
            for (d in 0..3) enq(i + di[d], j + dj[d], s + 1)
        }

        answer = q.size + 1
    }

    answer
}