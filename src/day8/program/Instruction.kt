package day8.program

data class Instruction (
        val lineNumber: Int,
        val command: Command,
        val value: Int
)

enum class Command {
    NOP,
    ACC,
    JMP
}