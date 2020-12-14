import day14.Day14


fun main(args: Array<String>) {
    Day14().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}