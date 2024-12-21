fun main() {
    val opening = "mul("
    val closing = ")"

    val enableCall = "do()"
    val disableCall = "don't()"

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            line.split(opening)
                .map { arguments ->
                    val endIdx = arguments.indexOf(closing).coerceAtLeast(0)
                    arguments.substring(0, endIdx).split(",")
                }
                .filter { pair ->
                    pair.size == 2
                }
                .forEach { pair ->
                    val operand1 = pair[0].toIntOrNull()?: 0
                    val operand2 = pair[1].toIntOrNull()?: 0
                    result += (operand1 * operand2)
                }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val text = input.joinToString("" )
            .split(enableCall)
            .map { enabledText ->
                val endIdx = enabledText.indexOf(disableCall)
                if(endIdx != -1) {
                    enabledText.substring(0, endIdx)
                }
                else {
                    enabledText
                }
            }

        return part1(text)
    }

    val input = readInput("Day03")

    part1(input).println()
    part2(input).println()
}
