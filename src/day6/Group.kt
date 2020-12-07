package day6

class Group {
    val people = arrayListOf<Person>()

    fun getUniqueCount(): Int {
        val uniqueResponses = mutableSetOf<Char>()
        people.forEach {
            uniqueResponses.addAll(it.affirmativeResponses)
        }
        return uniqueResponses.count()
    }

    fun getCommonCount(): Int {
        val responseCount = mutableMapOf<Char, Int>()
        people.forEach {
            it.affirmativeResponses.forEach {response ->
                if (responseCount.containsKey(response)) {
                    responseCount[response] = responseCount.getValue(response) + 1
                } else {
                    responseCount[response] = 1
                }
            }
        }

        return responseCount.filter {
            it.value == people.size
        }.count()
    }
}