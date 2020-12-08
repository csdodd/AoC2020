package day8

import day8.execution.Executor
import day8.program.Program
import loadFileToStringArray
import printAsHeader

class Day8 {

    fun run() {
        "Day8".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day8/example.txt")
        val program = Program(input)
        val result = Executor().runProgram(program)
        println("Accumulator from test is ${result.accAmount}")

        val fixedResult = Executor().fixProgram(program)
        println("Accumulator from fixed test is ${fixedResult.accAmount}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day8/input.txt")
        val program = Program(input)
        val result = Executor().runProgram(program)
        println("Accumulator from real data is ${result.accAmount}")

        val fixedResult = Executor().fixProgram(program)
        println("Accumulator from fixed real data is ${fixedResult.accAmount}")
    }

}