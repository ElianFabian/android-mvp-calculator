package com.elian.mvpcalculator.ui.calculator

import com.elian.mvpcalculator.base.IRepositoryCallback
import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.data.repository.OperationStaticRepository

class CalculatorInteractor(private val listener: CalculatorContract.IOnInteractorListener) :
    CalculatorContract.IInteractor,
    IRepositoryCallback
{
    //region Methods

    override fun validateData(operation: Operation)
    {
        when (operation.error)
        {
            Operation.Error.NUMBERS_ARE_NAN  ->
            {
                listener.onNumber1EmptyError()
                listener.onNumber2EmptyError()
            }
            Operation.Error.NUMBER1_IS_NAN   -> listener.onNumber1EmptyError()
            Operation.Error.NUMBER2_IS_NAN   -> listener.onNumber2EmptyError()
            Operation.Error.DIVISION_BY_ZERO -> listener.onResultError("Can't divide by zero.")

            else                             -> Unit
        }

        // If there's any error we're not going to add the operation into the repository
        if (operation.error != Operation.Error.NO_ERROR) return

        OperationStaticRepository.getInstance().add(this ,operation)
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