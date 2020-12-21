import day20.Day20


fun main(args: Array<String>) {
    Day20().run()
}

fun String.printAsHeader() {
    println("")
    println("********************")
    println("   $this ")
    println("********************")
}