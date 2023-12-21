/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 20/12/2023
*/

package utils

//lcm(a, b) = |a*b| / gcd(a,b)

/**
 * Calculate the GCD of a and b where a and b are integers
 *
 * @param a
 * @param b
 * @return [Int], the GCD of a and b
 */
fun greatestCommonDivisor(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

/**
 * Calculate the LCM of a and b, where a and b are integers
 *
 * @param a
 * @param b
 * @return [Int], the LCM of a and b
 */
fun leastCommonMultiple(a: Int, b: Int): Int {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0 && lcm % b == 0) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}


/**
 * Calculate the GCD of a and b, where a and b are Doubles
 *
 * @param a
 * @param b
 * @return [Double], the GDC of a and b
 */
fun greatestCommonDivisor(a: Double, b: Double): Double {
    var num1 = a
    var num2 = b
    while (num2 != 0.0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

/**
 * Calculate the LCM of a and b, where a and b are Doubles
 *
 * @param a
 * @param b
 * @return [Double],  the LCM of a and b
 */
fun leastCommonMultiple(a: Double, b: Double): Double {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0.0 && lcm % b == 0.0) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

/**
 * Calculate the GCD of a and b, where a and b are Long
 *
 * @param a
 * @param b
 * @return [Long], the GCD of a and b
 */
fun greatestCommonDivisor(a: Long, b: Long): Long {
    var num1 = a
    var num2 = b
    while (num2 != 0L) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

/**
 * Calculate the LCM of a and b, where a and b are Long
 *
 * @param a
 * @param b
 * @return [Long], the LCM of a and b
 */
fun leastCommonMultiple(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}