package com.elian.mvpcalculator.data.model

data class Operation(val type: Type)
{
    constructor(number1: String, number2: String, type: Type) : this(type)
    {
        if (number1.isEmpty())
        {
            error = Error.NUMBER1_IS_NAN
        }
        else this.number1 = number1.toFloat()

        if (number2.isEmpty())
        {
            error = if (error == Error.NUMBER1_IS_NAN) Error.NUMBERS_ARE_NAN
            else Error.NUMBER2_IS_NAN
        }
        else this.number2 = number2.toFloat()

        // The NUMBER2_IS_NAN error has a greater priority than DIVISION_BY_ZERO error
        if (type == Type.DIVISION &&
            this.number2 == 0f &&
            error != Error.NUMBER1_IS_NAN
        )
        {
            error = Error.DIVISION_BY_ZERO
        }
    }

    enum class Type(private val toStringValue: String)
    {
        ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("×"), DIVISION("÷");

        override fun toString(): String = toStringValue
    }

    enum class Error
    {
        NO_ERROR, DIVISION_BY_ZERO, NUMBER1_IS_NAN, NUMBER2_IS_NAN, NUMBERS_ARE_NAN
    }

    var id = 0

    var error = Error.NO_ERROR

    var number1: Float = Float.NaN
    var number2: Float = Float.NaN

    val result: Float
        get()
        {
            if (error != Error.NO_ERROR) return Float.NaN

            return when (type)
            {
                Type.ADDITION       -> number1 + number2
                Type.SUBTRACTION    -> number1 - number2
                Type.MULTIPLICATION -> number1 * number2
                Type.DIVISION       -> return if (number2 == 0f) Float.NaN else number1 / number2
            }
        }

    override fun toString(): String = "$number1 $type $number2 = $result"
}
