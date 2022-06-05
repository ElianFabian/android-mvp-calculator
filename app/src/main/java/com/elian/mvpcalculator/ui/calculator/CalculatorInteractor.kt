package com.elian.mvpcalculator.ui.calculator

import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.data.model.OperationError
import com.elian.mvpcalculator.data.repository.OperationStaticRepository

class CalculatorInteractor(private val listener: ICalculatorContract.IOnInteractorListener) :
    ICalculatorContract.IInteractor,
    ICalculatorContract.IRepositoryCallback
{
    //region Methods

    override fun validateData(operation: Operation)
    {
        when (operation.error)
        {
            OperationError.NUMBERS_ARE_NAN  ->
            {
                listener.onNumber1EmptyError()
                listener.onNumber2EmptyError()
            }
            OperationError.NUMBER1_IS_NAN   -> listener.onNumber1EmptyError()
            OperationError.NUMBER2_IS_NAN   -> listener.onNumber2EmptyError()
            OperationError.DIVISION_BY_ZERO -> listener.onResultError("Can't divide by zero.")

            else                            -> Unit
        }

        // If there's any error we're not going to add the operation into the repository
        if (operation.error != OperationError.NO_ERROR) return

        OperationStaticRepository.add(this, operation)
    }

    //endregion

    //region IRepositoryCallBack

    override fun onSuccess(operation: Operation)
    {
        listener.onSuccess(operation)
    }

    override fun onFailure()
    {
    }

    //endregion
}