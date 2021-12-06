package me.pierre.aoc2021

import java.io.File

class Day6 {

    fun run() {
        step1()
        step2()
    }

    private fun step1() {
        val fishes = File("src/main/resources/day6/input.txt").readLines().first().split(",").map(String::toInt)
            .map { Lanternfish(it) }.toMutableList()
        println("Initial : ${fishes.count()}")
        repeat(80) {
            val newBorn = fishes.mapNotNull { it.livesOneDay() }
            fishes += newBorn
        }
        println("Step 1 : ${fishes.count()}")
    }


    private fun step2() {
        val fishes = File("src/main/resources/day6/input.txt").readLines().first().split(",").map(String::toInt)
            .map { Lanternfish(it) }.toMutableList()
        println("Initial : ${fishes.count()}")
        var occ: Map<Int, Long> = fishes.groupingBy { it.timer }.eachCount().mapValues { (_, v) -> v.toLong() }
        repeat(256) {
            occ = occ.mapKeys { (key, _) -> key - 1 }.toMutableMap().also {
                it.put(8, it.getOrDefault(-1, 0))
                it.put(6, it.getOrDefault(6, 0) + it.getOrDefault(-1, 0))
                it.remove(-1)
            }
        }
        println("Step 2 : ${occ.values.sum()}")
    }
}

data class Lanternfish(
    var timer: Int,
) {
    fun livesOneDay(): Lanternfish? {
        this.timer--
        if (timer == -1) {
            timer = 6
            return Lanternfish(8)
        }
        return null
    }
}

fun main() = Day6().run()