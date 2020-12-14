package day14

sealed class Instruction {

    companion object {
        fun generate(input: String): Instruction {
            if (input.startsWith("mask = ")) {
                return Mask(input.substring(7))
            }

            val parts = input.split("=")
            val address = parts[0].replace("[^0-9]".toRegex(), "").toLong()
            val value = parts[1].replace("[^0-9]".toRegex(), "").toLong()
            return MemorySet(value, address)
        }
    }

    data class Mask(val value: String) : Instruction()
    data class MemorySet(val value: Long, val address: Long) : Instruction()
}