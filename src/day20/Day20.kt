package day20

import loadFileToStringArray
import printAsHeader
import kotlin.collections.ArrayList

class Day20 {

    fun run() {
        "Day20".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day20/example.txt")
        val result = sumOfFourCorners(input)
        println("Sum of 4 corners for test data is ${result.first}")
        println("Water roughness for test data is ${result.second}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day20/input.txt")
        val result = sumOfFourCorners(input)
        println("Sum of 4 corners for real data is ${result.first}")
        println("Water roughness for real data is ${result.second}")
    }

    private fun sumOfFourCorners(input: ArrayList<String>): Pair<Long, Int> {
        val tileManager = TileManager(input)
        val image = tileManager.createImage()
        val part1 = image.first().first().id.toLong() * image.first().last().id * image.last().first().id * image.last().last().id
        val part2 = tileManager.applySeaMonsterMask(image)

        return Pair(part1, part2)
    }
}