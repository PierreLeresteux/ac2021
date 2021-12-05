package me.pierre.aoc2021

import java.io.File


class Day4 {


    fun run() {
        val bingoInput: List<String> = File("src/main/resources/day4/input.txt").readLines()

        val draw = bingoInput.extractDrawNumbers()
        var boards = bingoInput.extractBoards()
        println(draw)
        draw.forEach { value ->
            for (board in boards) {
                if (board.check(value)) boards = (boards - board).toMutableList()
            }

        }
    }

    fun List<String>.extractDrawNumbers(): List<Int> = this.first().split(",").map { it.toInt() }
    fun List<String>.extractBoards(): MutableList<Board> = this.drop(1).chunked(6).map { it ->
        it.filter { it.isNotBlank() }
    }.map {
        Board(it.map {
            it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
        })
    }.toMutableList()


    data class Board(
        var score: Int,
        val lines: List<MutableList<Int>>,
    ) {
        fun check(value: Int): Boolean {
            lines.forEach {
                val index = it.indexOf(value)
                if (index >= 0) {
                    this.score -= value
                    it[index] = 0
                    if (this.checkLines()) {
                        println("FOUND LINE")
                        println("=> $value x ${this.score} = ${value * this.score}")
                        this.display()
                        return true
                    }
                    if (this.checkColumn()) {
                        println("FOUND COLUMN")
                        println("=> $value x ${this.score} = ${value * this.score}")
                        this.display()
                        return true
                    }
                }
            }
            return false
        }

        private fun checkColumn(): Boolean {
            for (column in this.lines[0].indices) {
                var columnOk = true
                for (row in lines.indices) {
                    if (lines[row][column] != 0) {
                        columnOk = false
                        continue
                    }
                }
                if (columnOk) return true
            }
            return false
        }

        private fun checkLines(): Boolean = lines.any { row -> row.isNotEmpty() && row.all { it == 0 } }

        fun display() {
            lines.forEach {
                it.forEach { print("+${it.toString().padStart(2, '0')}") }
                println("")
            }
            println()
        }

        constructor(lines: List<MutableList<Int>>) : this(lines.sumOf { it.sum() }, lines)
    }
}


fun main() {
    Day4().run()
}