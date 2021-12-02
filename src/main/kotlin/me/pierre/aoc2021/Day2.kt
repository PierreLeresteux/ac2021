package me.pierre.aoc2021

import java.io.File

class Day2 {
    fun run() {
        val sonarCourse = File("src/main/resources/day2/input.txt").readLines().map(String::toCommand)
        step1(sonarCourse)
        step1Alt(sonarCourse)
        step2(sonarCourse)
        step2Alt(sonarCourse)
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

    private fun step1Alt(sonarCourse: List<Command>) {
        val (h, d) = sonarCourse.fold(Step1Fold(0, 0)) { acc, command ->
            when (command.instruction) {
                "forward" -> acc.horizontal += command.value
                "down" -> acc.depth += command.value
                "up" -> acc.depth -= command.value
            }
            acc
        }
        println(" Step 1 Alt : ${h * d}")
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

    private fun step2Alt(sonarCourse: List<Command>) {
        val (h, d) = sonarCourse.fold(Step2Fold(0, 0, 0)) { acc, command ->
            when (command.instruction) {
                "forward" -> {
                    acc.horizontal += command.value
                    acc.depth += acc.aim * command.value
                }
                "down" -> acc.aim += command.value
                "up" -> acc.aim -= command.value
            }
            acc
        }
        println(" Step 2 Alt : ${h * d}")
    }
}

fun String.toAction() = this.filter { it.isLetter() }

fun String.toIntValue() = this.filter { it.isDigit() }
data class Command(
    val instruction: String, val value: Int
)

data class Step1Fold(var horizontal: Int, var depth: Int)
data class Step2Fold(var horizontal: Int, var depth: Int, var aim: Int)

fun String.toCommand() = Command(this.toAction(), this.toIntValue().toInt())

fun main() {
    Day2().run()
}
