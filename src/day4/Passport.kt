package day4

data class Passport (
    var byr: Int = 0, // birthYear
    var iyr: Int = 0, // issueYear
    var eyr: Int = 0, // expirationYear
    var hgt: String = "", // height
    var hcl: String = "", // hairColour
    var ecl: String = "", // eyeColour
    var pid: String = "", // passportId
    var cid: String = ""  // countryID
) {
    private val validEyeColours = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    constructor(rawInput: String) : this() {
        val pairs = rawInput.split(" ")
        pairs.forEach { pair ->
            val parts = pair.split(":")
            when (parts[0]) {
                "byr" -> this.byr = parts[1].toIntOrNull() ?: 0
                "iyr" -> this.iyr = parts[1].toIntOrNull() ?: 0
                "eyr" -> this.eyr = parts[1].toIntOrNull() ?: 0
                "hgt" -> this.hgt = parts[1]
                "hcl" -> this.hcl = parts[1]
                "ecl" -> this.ecl = parts[1]
                "pid" -> this.pid = parts[1]
                "cid" -> this.cid = parts[1]
            }
        }
    }

    fun isLooselyValidated(): Boolean {
        return this.byr > 0 &&
            this.iyr > 0 &&
            this.eyr > 0  &&
            this.hgt.isNotEmpty() &&
            this.hcl.isNotEmpty() &&
            this.ecl.isNotEmpty() &&
            this.pid.isNotEmpty()
    }

    fun isStrictlyValidated(): Boolean {
        return isLooselyValidated()
                && byr in 1920..2002
                && iyr in 2010..2020
                && eyr in 2020..2030
                && isValidHeight()
                && isValidHairColour()
                && isValidEyeColour()
                && isValidPassportId()
    }

    private fun isValidHeight(): Boolean {
        if (hgt.endsWith("cm")) {
            val rawHeight = hgt.replace("cm", "").toIntOrNull() ?: 0
            return rawHeight in 150..193
        } else if (hgt.endsWith("in")) {
            val rawHeight = hgt.replace("in", "").toIntOrNull() ?: 0
            return rawHeight in 59..76
        }
        return false
    }

    private fun isValidHairColour(): Boolean {
        val regex = "^#([a-f0-9]{6})$".toRegex()
        return hcl.matches(regex)
    }

    private fun isValidEyeColour(): Boolean {
        return validEyeColours.contains(ecl)
    }

    private fun isValidPassportId(): Boolean {
        val regex = "^([0-9]{9})$".toRegex()
        return pid.matches(regex)
    }
}