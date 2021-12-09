package me.pierre.aoc2021

import java.io.File
typealias Grid = List<List<Char>>

class Day9 {

    fun run() {
        val grid: Grid = File("src/main/resources/day9/input.txt").readLines().map { it.toList() }
        step1(grid)
        step2(grid)
    }

    private fun step1(grid: Grid) {
        var riskSum = 0
        grid.forEachIndexed { x, line ->
            line.forEachIndexed { y, currentChar ->
                if (currentChar < grid.getAdjacentValues(x, y).minOrNull()!!) {
                    riskSum += currentChar.toString().toInt() + 1
                }
            }
        }
        println("Step 1 : $riskSum")
    }

    private fun Grid.getAdjacentValues(x: Int, y: Int) = listOfNotNull(
        this[x].getOrNull(y + 1),
        this[x].getOrNull(y - 1),
        this.getOrNull(x - 1)?.get(y),
        this.getOrNull(x + 1)?.get(y),
    )



    private fun step2(grid: Grid) {

    }

}

fun main() {
    Day9().run()
}
