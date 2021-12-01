package me.pierre.aoc2021

class Day1 {
    fun run() {
        val sonarInput =
            this::class.java.getResourceAsStream("day1/input.txt").bufferedReader().readLines().map { it.toInt() }
        val step1 = countWithWindowOf2(sonarInput)
        println(step1)
        val step2 = countWithWindowOf2(sonarInput.windowed(3) { it.sum() })
        println(step2)
    }

    private fun countWithWindowOf2(inputs: List<Int>): Int =
        inputs.windowed(2).count { (first, second) -> second > first }

}

fun main() {
    Day1().run()
}