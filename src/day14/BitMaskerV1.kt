package day14

class BitMaskerV1 (
        input: ArrayList<String>
) {

    private val instructions = input.map { Instruction.generate(it) }
    private val addresses = mutableMapOf<Long, Long>()
    private var currentMask: Instruction.Mask? = null

    fun run(): Long {
        instructions.forEach {  instruction ->
            when (instruction) {
                is Instruction.Mask -> currentMask = instruction
                is Instruction.MemorySet -> memorySet(instruction)
            }
        }

        return addresses.values.sum()
    }

    private fun memorySet(instruction: Instruction.MemorySet) {
        val maskedValue = applyMaskTo(instruction.value)
        addresses.put(instruction.address, maskedValue)
    }

    private fun applyMaskTo(value: Long): Long {
        val asBinary = value.toString(2).padStart(36, '0').toCharArray()
        currentMask?.value?.forEachIndexed { i, c->
            when (c) {
                '1' -> asBinary[i] = '1'
                '0' -> asBinary[i] = '0'
                else -> {}
            }
        }

        return String(asBinary).toLong(2)
    }
}