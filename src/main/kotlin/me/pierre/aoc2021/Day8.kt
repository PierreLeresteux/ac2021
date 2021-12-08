package me.pierre.aoc2021

import java.io.File

class Day8 {

    fun run() {
        val inputs = File("src/main/resources/day8/input.txt").readLines()
        step1(inputs)
        step2(inputs)
    }

    private fun step1(lines: List<String>) {
        val count = lines.sumOf {
            it.substringAfter(" | ")
                .split(' ')
                .count {
                    listOf(2, 3, 4, 7).contains(it.length)
                }
        }
        println("Step 1 : $count")
    }


    private fun step2(lines: List<String>) {
        val count = lines.sumOf {
            val (allCodes, numbers) = it.split(" | ")

            numbers.split(' ')
                .fold(0) { acc: Int, s -> 10 * acc + getDigitsFromCodes(allCodes).indexOf(s.toSet()) }
        }
        println("Step 2 : $count")
    }

    private fun getDigitsFromCodes(allCodes: String): Array<Set<Char>> {
        val codes = allCodes.split(' ').map { it.toSet() }.groupBy { it.size }
        // easy : in one step
        val one = codes.getValue(2).single()
        val four = codes.getValue(4).single()
        val seven = codes.getValue(3).single()
        val eight = codes.getValue(7).single()
        // extract 2,3,5 (size = 5)
        // extract 6,9,0 (size = 6)
        val `two  three  five` = codes.getValue(5)
        val `six  nine  zero` = codes.getValue(6)
        // partition 2,3,5 => 2 and 3,5 (compare with four [2 / 4 have only 2 same segment, when 3 and 5 / 4 have 3 segments)
        val (twos, `three five`) = `two  three  five`.partition { (it - four).size == 3 }
        val two = twos.single()
        // partition 3,5 using comparison with 2
        val (threes, fives) = `three five`.partition { (it - two).size == 1 }
        val three = threes.single()
        val five = fives.single()
        // partition 6,9,0 => 6 and 9,0 using comparison with 1
        val (sixes, `nine  zero`) = `six  nine  zero`.partition { (one - it).isNotEmpty() }
        val six = sixes.single()
        val (zeros, nines) = `nine  zero`.partition { (it - three).size == 2 }
        val nine = nines.single()
        val zero = zeros.single()
        // get all digits
        val digits = arrayOf(zero, one, two, three, four, five, six, seven, eight, nine)
        return digits
    }

}

fun main() {
    Day8().run()
}
