package me.pierre.aoc2021

import java.io.File
import kotlin.math.abs

class Day7 {

    fun run() {
        val crabs = File("src/main/resources/day7/input.txt").readLines().first().split(",").map(String::toInt).sorted()
        println(step1(crabs))
        println(step2(crabs))
    }

    private fun step1(crabs: List<Int>) =
        (crabs.minOrNull()!!..crabs.maxOrNull()!!)
            .map {
                crabs.sumOf { crab ->
                    abs(crab - it)
                }
            }.minOrNull()!!


    private fun step2(crabs: List<Int>) =
        (crabs.minOrNull()!!..crabs.maxOrNull()!!)
            .map {
                crabs.sumOf { crab ->
                    val diff = abs(crab - it)
                    diff * (diff + 1) / 2
                }
            }.minOrNull()!!
}

fun main() {
    Day7().run()
}