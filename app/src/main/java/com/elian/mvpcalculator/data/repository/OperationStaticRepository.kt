package com.elian.mvpcalculator.data.repository

import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.ui.calculator.ICalculatorContract
import com.elian.mvpcalculator.ui.operationlist.IOperationListContract

object OperationStaticRepository :
    ICalculatorContract.IRepository,
    IOperationListContract.IRepository
{
    private var list = mutableListOf<Operation>()

    //region CalculatorContract.IRepository

    override fun add(callback: ICalculatorContract.IRepositoryCallback, operation: Operation)
    {
        list.add(operation)
        operation.id = list.size

        callback.onSuccess(operation)
    }

    //endregion

    //region IOperationListContract.IRepository

    override fun getList(callback: IOperationListContract.IRepositoryCallback) = callback.onSuccess(list)

    //endregion
}