package me.pierre.aoc2021

import java.io.File

class Day3 {
    fun run() {
        val sonarInput = File("src/main/resources/day3/input.txt").readLines()

        step1(sonarInput)
        step2(sonarInput)
    }

    private fun step1(sonarInput: List<String>) {
        val gamma = sonarInput[0].indices.map { index -> sonarInput.groupingBy { it[index] }.eachCount() }.map {
            it.maxByOrNull { it.value }?.key
        }.joinToString("")
        val epsilon = gamma.invertBit()
        println("""
    Gamma = ${gamma} [${gamma.toInt(2)}]
    Epsilon = ${epsilon} [${epsilon.toInt(2)}]
    Power Consumption = ${gamma.toInt(2) * epsilon.toInt(2)}
        """)
    }

    private fun step2(sonarInput: List<String>) {
        var o2Matched = sonarInput
        var co2Matched = sonarInput
        sonarInput[0].indices.forEach { column ->
            if (o2Matched.size > 1) {
                o2Matched = o2Matched.filter {
                    filter(o2Matched, column, it) { zero, one ->
                        if (zero > one) '0' else '1'
                    }
                }
            }
            if (co2Matched.size > 1) {
                co2Matched = co2Matched.filter {
                    filter(co2Matched, column, it) { zero, one ->
                        if (zero > one) '1' else '0'
                    }
                }
            }
        }
        val oxygen = o2Matched.single()
        val co2 = co2Matched.single()
        println("""
    O2 = ${oxygen} [${oxygen.toInt(2)}]
    CO2 = ${co2} [${co2.toInt(2)}]
    Life Support = ${oxygen.toInt(2) * co2.toInt(2)}
        """)
    }

    fun filter(
        matchedList: List<String>,
        column: Int,
        value: String,
        cond: (zeroes: Int, ones: Int) -> Char,
    ): Boolean {
        val chars = matchedList.groupingBy { it[column] }.eachCount()
        val zero = chars['0'] ?: 0
        val one = chars['1'] ?: 0
        return value[column] == cond(zero, one)
    }
}

fun String.invertBit() = this.map { if (it == '1') '0' else '1' }.joinToString("")

fun main() {
    Day3().run()
}