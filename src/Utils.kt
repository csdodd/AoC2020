import java.io.File
import java.net.URI

val clz: Class<*> = object {}.javaClass

private fun getResource(name: String) = clz.getResource(name)

fun loadFileToStringArray(name: String): ArrayList<String> {
    val lines = arrayListOf<String>()
    val uri = URI(getResource(name).toString())
    File(uri).readLines().forEach {
        lines.add(it)
    }
    return lines
}

fun loadFileToIntArray(name: String): ArrayList<Int> {
    val lines = arrayListOf<Int>()
    val uri = URI(getResource(name).toString())
    File(uri).readLines().forEach {
        lines.add(it.toInt())
    }
    return lines
}

fun loadFileToLongArray(name: String): ArrayList<Long> {
    val lines = arrayListOf<Long>()
    val uri = URI(getResource(name).toString())
    File(uri).readLines().forEach {
        lines.add(it.toLong())
    }
    return lines
}