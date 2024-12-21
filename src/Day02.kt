
fun main() {
    val input = readInput("Day02")
    Day02.part1(input).println()
    Day02.part2(input).println()
}

object Day02 {
    fun part1(input: List<String>): Int {
        val reports = input.map { line -> line.toIntegerList() }
        val safeReports = reports.filter { it.isSafe(false) }
        return safeReports.size
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line -> line.toIntegerList() }
        val safeReports = reports.filter { it.isSafe(true) }
        return safeReports.size
    }

    fun String.toIntegerList(): List<Int> {
        return this.split(" ").map { it.toInt() }
    }

    fun List<Int>.isSafe(dampened: Boolean): Boolean {
        // Edge Case: Stagnant change in level, Neither Increasing/Decreasing
        if (this[0] == this[1]) {
            return false
        }

        //Normal Case: Changes in level are strictly increasing or decreasing, changing between 1..3 levels
        val increasing: Boolean = this[1] > this[0]
        var prev = this[0]
        for (i in 1..<this.size) {
            val current = this[i]
            val difference = if (increasing) current - prev else prev - current;
            if (difference !in 1..3) {
                if (dampened) {
                    val removalOfPrev = this.toMutableList().also { it.removeAt(i - 1) }.isSafe(false)
                    val removalOfCurrent = this.toMutableList().also { it.removeAt(i) }.isSafe(false)
                    return removalOfPrev || removalOfCurrent
                } else {
                    return false
                }
            }
            prev = current
        }
        return true
    }
}




