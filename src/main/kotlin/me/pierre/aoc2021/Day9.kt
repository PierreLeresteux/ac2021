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
                    riskSum += currentChar.digitToInt() + 1
                }
            }
        }
        println("Step 1 : $riskSum")
    }

    private fun step2(grid: Grid) {
        val lowPoints = ArrayList<Point>()
        grid.forEachIndexed { x, line ->
            line.forEachIndexed { y, currentChar ->
                if (currentChar < grid.getAdjacentValues(x, y).minOrNull()!!) {
                    lowPoints.add(Point(x, y, currentChar.digitToInt()))
                }
            }
        }
        val size = lowPoints
            .asSequence()
            .map { getBasin(grid, setOf(it)) }
            .distinct()
            .sortedByDescending { it.size }
            .take(3)
            .map { it.size }
            .reduce { a, b -> a * b }
        println("Step 2 : $size")
    }

    private fun getBasin(grid: Grid, lowPoint: Set<Point>): Set<Point> {
        val adjacentPointsLowerThan9 =
            lowPoint.flatMap { it.getAdjacentPointsGreaterThanValue(grid) }.distinct().filter { it.value < 9 }
        if (adjacentPointsLowerThan9.size == lowPoint.size - 1) {
            return lowPoint + adjacentPointsLowerThan9
        }
        return getBasin(grid, lowPoint + adjacentPointsLowerThan9)
    }


    private fun Point.getAdjacentPointsGreaterThanValue(grid: Grid): List<Point> {
        val above = grid.getPoint(x, y - 1)?.checkValue(this.value)
        val below = grid.getPoint(x, y + 1)?.checkValue(this.value)
        val left = grid.getPoint(x - 1, y)?.checkValue(this.value)
        val right = grid.getPoint(x + 1, y)?.checkValue(this.value)
        return listOfNotNull(above, below, left, right)
    }

    private fun Point.checkValue(value: Int): Point? = if (this.value > value) this else null

    private fun Grid.getPoint(x: Int, y: Int): Point? =
        if (x >= 0 && x < this.size && y >= 0 && y < this[0].size ) Point(x, y, this[x][y].digitToInt())
        else null

    private fun Grid.getAdjacentValues(x: Int, y: Int) = listOfNotNull(
        this[x].getOrNull(y + 1),
        this[x].getOrNull(y - 1),
        this.getOrNull(x - 1)?.get(y),
        this.getOrNull(x + 1)?.get(y),
    )
}

data class Point(val x: Int, val y: Int, val value: Int)

fun main() {
    Day9().run()
}
