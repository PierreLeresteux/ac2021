package me.pierre.aoc2021

import java.io.File

class Day10 {

    private val charMapping = mapOf('(' to ')', '{' to '}', '<' to '>', '[' to ']')

    fun run() {
        val input = File("src/main/resources/day10/input.txt").readLines()
        step1(input)
    }

    private fun step1(inputs: List<String>) {
        val illegalCharsValue = inputs.map {
            val queue = ArrayDeque<Char>()
            var illegarChar: Char? = null
            it.forEach {
                if (charMapping.keys.contains(it)) {
                    queue.addLast(charMapping.getValue(it))
                } else {
                    if (it != queue.removeLastOrNull()) illegarChar = it
                }
            }
            illegarChar
        }.mapNotNull { it?.toValue() }.sum()
        println("Step 1")
        println(illegalCharsValue)
    }

    private fun Char.toValue(): Int =
        when (this) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }

}

fun main() {
    Day10().run()
}