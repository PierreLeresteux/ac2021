package me.pierre.aoc2021

import java.io.File

class Day2 {
    fun run() {
        val sonarCourse =
            File("src/main/resources/day2/input.txt").readLines().map { Pair(it.toAction(), it.toIntValue()) }
        val forwards = sonarCourse.filter { (action, _) -> action == "forward" }.map { it.second.toInt() }.sum()
        val ups = sonarCourse.filter { (action, _) -> action == "up" }.map { it.second.toInt() }.sum()
        val downs = sonarCourse.filter { (action, _) -> action == "down" }.map { it.second.toInt() }.sum()
        val depth = downs - ups
        println("Step 1 : ${forwards * depth}")
    }
}

fun String.toAction() = this.filter { it.isLetter() }
fun String.toIntValue() = this.filter { it.isDigit() }

fun main() {
    Day2().run()
}