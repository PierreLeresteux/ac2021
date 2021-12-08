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
                .count { listOf(2, 3, 4, 7).contains(it.length)}
        }
        println("Step 1 : $count")
    }


    private fun step2(inputs: List<String>) {
    }
}

fun main() {
    Day8().run()
}