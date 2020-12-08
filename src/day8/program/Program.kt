package day8.program

class Program(
        input: ArrayList<String>
) {

    private var instructions = arrayListOf<Instruction>()

    init {
        input.forEachIndexed { i, line ->
            val parts = parseInstructionLine(line)
            val instruction = Instruction(i, parts.first, parts.second)
            instructions.add(instruction)
        }
    }

    private fun parseInstructionLine(line: String): Pair<Command, Int> {
        val parts = line.split(" ")
        val command = when(parts[0]) {
            "jmp" -> Command.JMP
            "acc" -> Command.ACC
            "nop" -> Command.NOP
            else -> Command.NOP
        }

        return Pair(command, parts[1].toIntOrNull() ?: 0)
    }

    fun getInstruction(index: Int): Instruction? {
        if (index >= instructions.size) return null
        return instructions[index]
    }

    fun toggleCommandAt(lineToChange: Int) {
        val instruction = instructions[lineToChange]
        val newCommand = if (instruction.command == Command.NOP) Command.JMP else Command.NOP
        val newInstruction = Instruction(instruction.lineNumber, newCommand, instruction.value)
        instructions[lineToChange] = newInstruction
    }
}