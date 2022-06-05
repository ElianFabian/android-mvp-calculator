package com.elian.mvpcalculator.data.model

data class Operation(val type: OperationType)
{
    constructor(number1: String, number2: String, type: OperationType) : this(type)
    {
        if (number1.isEmpty())
        {
            error = OperationError.NUMBER1_IS_NAN
        }
        else this.number1 = number1.toFloat()

        if (number2.isEmpty())
        {
            error = if (error == OperationError.NUMBER1_IS_NAN) OperationError.NUMBERS_ARE_NAN
            else OperationError.NUMBER2_IS_NAN
        }
        else this.number2 = number2.toFloat()

        // The NUMBER2_IS_NAN error has a greater priority than DIVISION_BY_ZERO error
        if (type == OperationType.DIVISION && this.number2 == 0f &&
            error != OperationError.NUMBER1_IS_NAN
        )
        {
            error = OperationError.DIVISION_BY_ZERO
        }
    }

    var id = 0

    var error = OperationError.NO_ERROR

    var number1: Float = Float.NaN
    var number2: Float = Float.NaN

    val result: Float
        get()
        {
            if (error != OperationError.NO_ERROR) return Float.NaN

            return when (type)
            {
                OperationType.ADDITION       -> number1 + number2
                OperationType.SUBTRACTION    -> number1 - number2
                OperationType.MULTIPLICATION -> number1 * number2
                OperationType.DIVISION       -> return if (number2 == 0f) Float.NaN else number1 / number2
            }
        }

    override fun toString(): String = "$number1 $type $number2 = $result"
}

enum class OperationType(private val toStringValue: String)
{
    ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("×"), DIVISION("÷");

    override fun toString(): String = toStringValue
}

enum class OperationError
{
    NO_ERROR, DIVISION_BY_ZERO, NUMBER1_IS_NAN, NUMBER2_IS_NAN, NUMBERS_ARE_NAN
}
