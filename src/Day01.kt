import kotlin.math.abs

fun main() {
    val seperator = "   "
    fun part1(input: List<String>): Int {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        input.forEach{ line  ->
            val numbers = line.split(seperator)
            list1.add(numbers[0].toInt())
            list2.add(numbers[1].toInt())
        }
        list1.sort()
        list2.sort()

        var result = 0;
        for(i in list1.indices) {
            result += abs(list1[i] - list2[i])
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        input.forEach{ line  ->
            val numbers = line.split(seperator)
            list1.add(numbers[0].toInt())
            list2.add(numbers[1].toInt())
        }
        var result = 0;
        list1.forEach { number ->
            val count = list2.count{ it == number }
            result += (number * count)
        }
        return result;
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
