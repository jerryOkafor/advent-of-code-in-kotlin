/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package core.geoemtry

import kotlin.math.absoluteValue

data class Vector3(val x: Int, val y: Int, val z: Int) {

    companion object {
        val ORIGIN = Vector3(0, 0, 0)
    }

    operator fun plus(other: Vector3): Vector3 {
        return Vector3(x + other.x, y + other.y, z + other.z)
    }

    operator fun minus(other: Vector3): Vector3 {
        return Vector3(x - other.x, y - other.y, z - other.z)
    }

    operator fun get(index: Int): Int = when (index) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IllegalArgumentException()
    }
}
