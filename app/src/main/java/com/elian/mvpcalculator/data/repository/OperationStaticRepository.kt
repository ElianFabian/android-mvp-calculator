package com.elian.mvpcalculator.data.repository

import com.elian.mvpcalculator.base.IRepositoryCallback
import com.elian.mvpcalculator.base.IRepositoryListCallback
import com.elian.mvpcalculator.data.model.Operation
import com.elian.mvpcalculator.ui.calculator.CalculatorContract
import com.elian.mvpcalculator.ui.operationlist.IOperationListContract

open class OperationStaticRepository :

    CalculatorContract.IRepository,
    IOperationListContract.IRepository
{
    private var list = mutableListOf<Operation>()

    companion object
    {
        private var instance = OperationStaticRepository()

        fun getInstance(): OperationStaticRepository = instance
    }

    //region CalculatorContract.IRepository

    override fun add(callback: IRepositoryCallback, operation: Operation)
    {
        list.add(operation)
        operation.id = list.size

        callback.onSuccess(operation)
    }

    //endregion


    //region IOperationListContract.IRepository

    override fun getList(callback: IRepositoryListCallback) = callback.onSuccess(list)

    //endregion
}