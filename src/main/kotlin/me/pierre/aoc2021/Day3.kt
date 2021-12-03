package me.pierre.aoc2021

import java.io.File

class Day3 {

    fun run() {
        val sonarInput = File("src/main/resources/day3/input.txt").readLines()

        step1(sonarInput)
    }

    private fun step1(sonarInput: List<String>) {
        val gamma = sonarInput[0].indices.map { index -> sonarInput.groupingBy { it.get(index) }.eachCount() }.map {
            it.maxByOrNull { it.value }?.key
        }.joinToString("")
        val epsilon = gamma.invertBit()
        println("""
    Gamma = ${gamma} [${gamma.toInt(2)}]
    Epsilon = ${epsilon} [${epsilon.toInt(2)}]
    Power Consumption = ${gamma.toInt(2) * epsilon.toInt(2)}
        """)
    }
}

fun String.invertBit() = this.map { if (it == '1') '0' else '1' }.joinToString("")

fun main() {
    Day3().run()
}