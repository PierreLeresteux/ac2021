package me.pierre.aoc2021

import java.io.File

class Day2 {
    fun run() {
        val sonarCourse = File("src/main/resources/day2/input.txt").readLines().map(String::toCommand)
        step1(sonarCourse)
        step2(sonarCourse)
    }

    private fun step1(sonarCourse: List<Command>) {
        var depth = 0
        var horizontal = 0
        sonarCourse.forEach {
            when (it.instruction) {
                "forward" -> horizontal += it.value
                "down" -> depth += it.value
                "up" -> depth -= it.value
            }
        }
        println(" Step 1 : ${horizontal * depth}")
    }

    private fun step2(sonarCourse: List<Command>) {
        var depth = 0
        var horizontal = 0
        var aim = 0
        sonarCourse.forEach {
            when (it.instruction) {
                "forward" -> {
                    horizontal += it.value
                    depth += aim * it.value
                }
                "down" -> aim += it.value
                "up" -> aim -= it.value
            }
        }
        println(" Step 2 : ${horizontal * depth}")
    }
}

fun String.toAction() = this.filter { it.isLetter() }

fun String.toIntValue() = this.filter { it.isDigit() }
data class Command(
    val instruction: String, val value: Int
)

fun String.toCommand() = Command(this.toAction(), this.toIntValue().toInt())

fun main() {
    Day2().run()
}
