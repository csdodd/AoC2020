package day7

import day7.graph.Graph
import day7.graph.Node
import loadFileToStringArray
import printAsHeader
import java.util.ArrayList

class Day7 {

    fun run() {
        "Day7".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day7/example.txt")
        val graph = Graph(input)
        println("Number of potential gold containers in test data is ${graph.countNumberOfGoldContainers()}")
        println("Number of gold contents in test data is ${graph.countNumberOfGoldContents()}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day7/input.txt")
        val graph = Graph(input)
        println("Number of potential gold containers in real data is ${graph.countNumberOfGoldContainers()}")
        println("Number of gold contents in real data is ${graph.countNumberOfGoldContents()}")
    }

}