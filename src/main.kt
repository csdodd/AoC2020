import day19.Day19


fun main(args: Array<String>) {
    Day19().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}