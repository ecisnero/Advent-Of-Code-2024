
fun main() {
    //val input = readInput("test11")
    val input = readInput("Day11")
    //Day11.part1(input).println()
    Day11.part2(input).println()
}

object Day11 {
    fun part1(input: List<String>): Int {
        val stones  = input[0].split(" ")
        return stones.blinkFor(25).size
    }

    fun part2(input: List<String>): Int {
        val stones  = input[0].split(" ").toMutableList()
        repeat(75) {
            stones.processStones(stones.size)
        }
        return stones.size
    }

    fun List<String>.blinkFor(times: Int): List<String> {
        var newStones = this
        for(i in 0..<times) {
            newStones = newStones.blink()
        }
        return newStones
    }

    fun List<String>.blink(): List<String> {
        return this.map { stone ->
            if(stone == "0")
                stone.applyRule1()
            else if(stone.length.isEven())
                stone.appleRule2()
            else
                stone.applyRule3()
        }.flatten()
    }

    fun String.applyRule1(): List<String> = listOf<String>("1")

    fun String.appleRule2(): List<String> {
        val left = (this.substring(0, this.length/2).toLong()).toString()
        val right = (this.substring(this.length/2, this.length).toLong()).toString()
        return listOf<String>(left, right)
    }

    fun String.applyRule3(): List<String> = listOf<String>((this.toLong() * 2024).toString())

    fun Int.isEven(): Boolean = (this.and(1) == 0)

    fun MutableList<String>.processStones(count: Int): MutableList<String> {
        for(i in 0..<count) {
            if(this[i] == "0") {
                this[i] = "1"
            }
            else if(this[i].length.isEven()) {
                val current = this[i]
                this[i] = (current.substring(0, current.length/2).toLong()).toString()
                this.add((current.substring(current.length/2, current.length).toLong()).toString())
            }
            else {
                this[i] = (this[i].toLong() * 2024).toString()
            }
        }
        return this
    }
}




