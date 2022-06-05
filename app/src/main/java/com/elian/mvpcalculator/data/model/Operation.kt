package com.elian.mvpcalculator.data.model

import com.elian.mvpcalculator.data.model.OperationError.*
import com.elian.mvpcalculator.data.model.OperationType.*

data class Operation(val type: OperationType)
{
    constructor(number1: String, number2: String, type: OperationType) : this(type)
    {
        if (number1.isEmpty())
        {
            error = NUMBER1_IS_NAN
        }
        else this.number1 = number1.toFloat()

        if (number2.isEmpty())
        {
            error = if (error == NUMBER1_IS_NAN) NUMBERS_ARE_NAN
            else NUMBER2_IS_NAN
        }
        else this.number2 = number2.toFloat()

        // The NUMBER2_IS_NAN error has a greater priority than DIVISION_BY_ZERO error
        if (type == DIVISION && this.number2 == 0f && error != NUMBER1_IS_NAN)
        {
            error = DIVISION_BY_ZERO
        }
    }

    var id = 0

    var error = NO_ERROR

    var number1: Float = Float.NaN
    var number2: Float = Float.NaN

    val result: Float
        get()
        {
            if (error != NO_ERROR) return Float.NaN

            return when (type)
            {
                ADDITION       -> number1 + number2
                SUBTRACTION    -> number1 - number2
                MULTIPLICATION -> number1 * number2
                DIVISION       -> return if (number2 == 0f) Float.NaN else number1 / number2
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
