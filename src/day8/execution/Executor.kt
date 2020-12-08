package day8.execution

import day8.program.Command
import day8.program.Program

class Executor {

    private var currentLineNumber = 0
    private var accCount = 0
    private var executedLines = mutableSetOf<Int>()
    private var successfulCompletion = false

    fun runProgram(program: Program): Result {
        reset()

        while (true) {
            val currentInstruction = program.getInstruction(currentLineNumber)
            successfulCompletion = currentInstruction == null

            when (currentInstruction?.command) {
                Command.NOP -> currentLineNumber++
                Command.ACC -> {
                    accCount += currentInstruction.value
                    currentLineNumber++
                }
                Command.JMP -> currentLineNumber += currentInstruction.value
            }

            if (successfulCompletion || !executedLines.add(currentLineNumber)) break
        }
        return Result(accCount, successfulCompletion)
    }

    fun fixProgram(program: Program): Result {
        var lineToChange = 0

        while(true) {
            val currentInstruction = program.getInstruction(lineToChange)
            if (currentInstruction?.command != Command.ACC) {
                program.toggleCommandAt(lineToChange)
                val result = runProgram(program)
                if (result.successfulCompletion) return result

                program.toggleCommandAt(lineToChange)
            }
            lineToChange++
        }

    }

    private fun reset() {
        currentLineNumber = 0
        accCount = 0
        executedLines.clear()
    }
}