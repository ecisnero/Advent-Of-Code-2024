
fun main() {
    val input = readInput("Day11")
    Day11.part1(input).println()
    Day11.part2(input).println()
}

object Day11 {
    fun part1(input: List<String>): Long {
        val stones  = input[0].split(" ")
        var count = stones.getCounts()
        repeat(25) {
            count = count.processStep()
        }
        return count.values.sum()
    }

    fun part2(input: List<String>): Long {
        val stones  = input[0].split(" ")
        var count = stones.getCounts()
        repeat(75) {
            count = count.processStep()
        }
        return count.values.sum()
    }

    fun Int.isEven(): Boolean = (this.and(1) == 0)

    fun List<String>.getCounts(): Map<String,Long> {
        val map = HashMap<String, Long>()
        this.forEach { stone ->
            map[stone] = map.getOrDefault(stone, 0) + 1
        }
        return map
    }

    fun Map<String, Long>.processStep(): Map<String, Long> {
        val map = HashMap<String, Long>()
        this.forEach { stone, count ->
            if(stone == "0") {
                val result = "1"
                map[result] = map.getOrDefault(result, 0) + count
            }
            else if(stone.length.isEven()) {
                val result1 = (stone.substring(0, stone.length/2).toLong()).toString()
                val result2 = (stone.substring(stone.length/2, stone.length).toLong()).toString()
                map[result1] = map.getOrDefault(result1, 0) + count
                map[result2] = map.getOrDefault(result2, 0) + count
            }
            else {
                val result = (stone.toLong() * 2024).toString()
                map[result] = map.getOrDefault(result, 0) + count
            }
        }
        return map
    }
}




