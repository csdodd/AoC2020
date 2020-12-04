package day3

import loadFileToStringArray
import printAsHeader

class Day3 {
    private var currentPosition = Position()
    private val deltaFirstApproach = Pair(1, 1)
    private val deltaSecondApproach = Pair(3, 1)
    private val deltaThirdApproach = Pair(5, 1)
    private val deltaFourthApproach = Pair(7, 1)
    private val deltaFifthApproach = Pair(1, 2)

    fun run() {
        "Day3".printAsHeader()
        val grid = loadGridFromFile()
        println("Trees encountered from first approach ${part1(grid)}")
        println("Product of trees encountered from multiple attempts is ${part2(grid)}")
    }

    private fun part1(grid: ArrayList<List<MapMarker>>): Int {
        return processGrid(grid, deltaSecondApproach)
    }

    private fun part2(grid: ArrayList<List<MapMarker>>): Int {
        val approach1Count = processGrid(grid, deltaFirstApproach)
        val approach2Count = processGrid(grid, deltaSecondApproach)
        val approach3Count = processGrid(grid, deltaThirdApproach)
        val approach4Count = processGrid(grid, deltaFourthApproach)
        val approach5Count = processGrid(grid, deltaFifthApproach)
        return approach1Count * approach2Count * approach3Count * approach4Count * approach5Count
    }

    private fun loadGridFromFile(): ArrayList<List<MapMarker>> {
        val input = loadFileToStringArray("/day3/input.txt")
        return convertInputToGrid(input)
    }

    private fun test(): Int {
        val input = arrayListOf(
                "..##.......",
                "#...#...#..",
                ".#....#..#.",
                "..#.#...#.#",
                ".#...##..#.",
                "..#.##.....",
                ".#.#.#....#",
                ".#........#",
                "#.##...#...",
                "#...##....#",
                ".#..#...#.#")

        val grid = convertInputToGrid(input)
        return processGrid(grid, deltaSecondApproach)
    }

    private fun processGrid(grid: ArrayList<List<MapMarker>>, delta: Pair<Int, Int>): Int {
        currentPosition.reset()
        currentPosition.wrapSize = grid.first().size - 1

        val mapMarkers = arrayListOf<MapMarker>()
        while (currentPosition.row < grid.size - 1) {
            currentPosition.addDelta(delta)
            mapMarkers.add(grid[currentPosition.row][currentPosition.col])
        }

        return mapMarkers.filter { it.type == Type.Tree }.size
    }

    private fun convertInputToGrid(input: ArrayList<String>): ArrayList<List<MapMarker>> {
        val grid = arrayListOf<List<MapMarker>>()
        input.forEach { line ->
            val row = arrayListOf<MapMarker>()
            line.forEach {
                row.add(MapMarker(it))
            }
            grid.add(row)
        }
        return grid
    }
}