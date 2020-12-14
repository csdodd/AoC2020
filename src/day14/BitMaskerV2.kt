package day14

class BitMaskerV2 (
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
        val maskedAddress = applyMaskTo(instruction.address)
        val writeLocations = calculateAllAddresses(maskedAddress)
        writeLocations.forEach { writeLocation ->
            addresses[writeLocation] = instruction.value
        }
    }

    private fun calculateAllAddresses(maskedAddress: String): ArrayList<Long> {
        val floatingCount = maskedAddress.count { it == 'X' }
        if (floatingCount == 0) return arrayListOf(maskedAddress.toLong(2))

        var addresses = arrayListOf<Long>()
        val numIterationsAsBinary = "1".padEnd(floatingCount, '1').toLong(2)

        for (i in 0..numIterationsAsBinary) {
            val replacement = i.toString(2).padStart(floatingCount, '0')
            var newAddress = String(maskedAddress.toCharArray())
            replacement.forEach {
                newAddress = newAddress.replaceFirst('X', it)
            }
            addresses.add(newAddress.toLong(2))
        }

        return addresses
    }

    private fun applyMaskTo(value: Long): String {
        val asBinary = value.toString(2).padStart(36, '0').toCharArray()
        currentMask?.value?.forEachIndexed { i, c->
            when (c) {
                '1' -> asBinary[i] = '1'
                'X' -> asBinary[i] = 'X'
                else -> {}
            }
        }

        return String(asBinary)
    }
}