package day15

import loadFileToIntArrayFromSingleLineCSV
import printAsHeader

class Day15 {

    fun run() {
        "Day15".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToIntArrayFromSingleLineCSV("/day15/example.txt")
        val result2020 = runGameToPosition(2020, input)
        val result30000000 = runGameToPosition(30000000, input)
        println("Result of game at pos 2020 for real data is $result2020")
        println("Result of game at pos 30000000 for real data is $result30000000")
    }

    private fun realData() {
        val input = loadFileToIntArrayFromSingleLineCSV("/day15/input.txt")
        val result2020 = runGameToPosition(2020, input)
        val result30000000 = runGameToPosition(30000000, input)
        println("Result of game at pos 2020 for real data is $result2020")
        println("Result of game at pos 30000000 for real data is $result30000000")
    }

    private fun runGameToPosition(position: Int, input: List<Int>): Int {
        val chain = input.toMutableList()
        val indexes = ingestInput(input).toMutableMap()

        for (x in (chain.size - 1) until position) {
            if (indexes[chain[x]] == null){
                indexes[chain[x]] = chain.lastIndex
                chain.add(0)

            } else {
                chain.add(chain.lastIndex - (indexes[chain[x]] ?: -1))
                indexes[chain[x]] = chain.lastIndex - 1
            }
        }
        return chain[position - 1]
    }

    private fun ingestInput(input: List<Int>): Map<Int, Int> {
        val indexes = mutableMapOf<Int, Int>()
        input.forEachIndexed { i, n ->
            indexes[n] = i
        }
        return indexes
    }
}