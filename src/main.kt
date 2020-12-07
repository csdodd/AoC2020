import day7.Day7


fun main(args: Array<String>) {
    Day7().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}