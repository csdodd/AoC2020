package day10

import loadFileToIntArray
import printAsHeader

class Day10 {

    fun run() {
        "Day10".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToIntArray("/day10/example.txt")
        println("Number for largest configuration in test data is: ${findLargestConfiguration(input)}")
        println("Number of total configurations in test data is: ${findTotalNumberOfConfigurations(input)}")
    }

    private fun realData() {
        val input = loadFileToIntArray("/day10/input.txt")
        println("Number for largest configuration in real data is: ${findLargestConfiguration(input)}")
        println("Number of total configurations in real data is: ${findTotalNumberOfConfigurations(input)}")
    }

    private fun findLargestConfiguration(input: ArrayList<Int>): Int {
        input.sort()
        input.add(0, 0)
        input.add(input.last() + 3)
        val deltas = input.zipWithNext { a, b -> b - a }
        return deltas.count { it == 1 } * deltas.count { it == 3 }
    }

    private fun findTotalNumberOfConfigurations(input: ArrayList<Int>): Long {
        val pathCounts = LongArray(input.size) { 0L }
        pathCounts[input.size - 1] = 1
        for (i in input.size - 2 downTo 0) {
            pathCounts[i] = pathCounts[i + 1]
            if (i + 3 < input.size && input[i + 3] <= input[i] + 3) {
                pathCounts[i] += pathCounts[i + 3]
            }
            if (i + 2 < input.size && input[i + 2] <= input[i] + 3) {
                pathCounts[i] += pathCounts[i + 2]
            }
        }
        return pathCounts[0]
    }

}