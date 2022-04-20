package com.elian.mvpcalculator.ui.operationlist

import com.elian.mvpcalculator.base.IBasePresenter
import com.elian.mvpcalculator.data.model.Operation

interface IOperationListContract
{
    interface IView : IRepositoryCallback

    interface IPresenter : IBasePresenter
    {
        fun load()
    }

    interface IInteractor
    {
        fun load()
    }

    interface IRepository
    {
        fun getList(callback: IRepositoryCallback)
    }

    interface IRepositoryCallback
    {
        fun onSuccess(list: List<Operation>)
    }

    interface IOnInteractorListener : IRepositoryCallback
}