
fun main() {
    val input = readInput("Day07")
    Day07.part1(input).println()
    Day07.part2(input).println()
}

object Day07 {
    fun part1(input: List<String>): Long {
        val calibrationTotal = input.filter { report ->
            val target = report.extractResult()
            val operands = report.extractOperandList()
            operands.canBeCalibratedTo(target, operands[0].toLong(), 1)
        }.sumOf { it.extractResult() }
        return calibrationTotal
    }

    fun part2(input: List<String>): Long {
        val calibrationTotal = input.filter { report ->
            val target = report.extractResult()
            val operands = report.extractOperandList()
            operands.canBeConcatCalibratedTo(target, operands[0].toLong(), 1)
        }.sumOf { it.extractResult() }
        return calibrationTotal
    }

    fun String.extractResult(): Long {
        val target = this.substring(0, this.indexOf(':')).trim()
        return target.toLong()
    }

    fun String.extractOperandList(): List<Long> {
        val operands = this.substring(this.indexOf(':')+1, this.length).trim()
        return operands.split(' ').map{ it.toLong() }
    }

    fun List<Long>.canBeCalibratedTo(target: Long, runningValue: Long, i:Int): Boolean {
        //Base Case: All operands processed
        if(i >= this.size) {
            return runningValue == target
        }
        //Recursive Case: Evaluate each case
        return this.canBeCalibratedTo(target, runningValue*this[i], i+1)
                || this.canBeCalibratedTo(target, runningValue+this[i], i+1)
    }

    fun List<Long>.canBeConcatCalibratedTo(target: Long, runningValue: Long, i:Int): Boolean {
        //Base Case: All operands processed
        if(i >= this.size) {
            return runningValue == target
        }
        //Recursive Case: Evaluate each case
        return this.canBeConcatCalibratedTo(target, runningValue*this[i], i+1)
                || this.canBeConcatCalibratedTo(target, runningValue+this[i], i+1)
                || this.canBeConcatCalibratedTo(target, (runningValue.toString() + this[i].toString()).toLong(), i+1)
    }



}




