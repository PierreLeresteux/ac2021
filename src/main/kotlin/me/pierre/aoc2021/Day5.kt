package me.pierre.aoc2021

import java.io.File

typealias Coordinate = Pair<Int, Int>

class Day5 {

    fun run() {
        val linesOfVents = File("src/main/resources/day5/input.txt").readLines().map {
            it.split(" -> ").let { (from, to) -> from.toCoordinate() to to.toCoordinate() }
        }
        step1(linesOfVents)
        step2(linesOfVents)
    }

    private fun step1(linesOfVents: List<Pair<Coordinate, Coordinate>>) {
        println("Step 1")
        println(linesOfVents
            .filter { (c1, c2) -> c1.first == c2.first || c1.second == c2.second }
            .toPoints()
            .groupBy { it }
            .count { (_, v) -> v.size > 1 }
        )
    }

    private fun step2(linesOfVents: List<Pair<Coordinate, Coordinate>>) {
        println("Step 2")

        println(linesOfVents
            .toPoints()
            .groupBy { it }
            .count { (_, v) -> v.size > 1 }
        )
    }

    private fun List<Pair<Coordinate, Coordinate>>.toPoints() = this.map { (c1, c2) ->
        when {
            c1.first == c2.first -> {
                createRange(c1.second, c2.second).map {
                    Coordinate(c1.first, it)
                }
            }
            c1.second == c2.second -> {
                createRange(c1.first, c2.first).map {
                    Coordinate(it, c1.second)
                }
            }
            else -> createRange(c1.first, c2.first).zip(createRange(c1.second, c2.second))
        }
    }.flatten()

    private fun createRange(first: Int, second: Int) = when {
        first < second -> (first..second)
        else -> (first downTo second)
    }

}

fun String.toCoordinate(): Coordinate = this.split(",").let { (x, y) -> x.toInt() to y.toInt() }
fun main() {
    Day5().run()
}