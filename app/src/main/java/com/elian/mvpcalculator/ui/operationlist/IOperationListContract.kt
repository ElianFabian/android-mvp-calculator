package com.elian.mvpcalculator.ui.operationlist

import com.elian.mvpcalculator.base.IBasePresenter
import com.elian.mvpcalculator.base.IRepositoryListCallback

interface IOperationListContract
{
    interface IView : IRepositoryListCallback

    interface IPresenter : IBasePresenter
    {
        fun load()
    }

    interface IRepository
    {
        fun getList(callback: IRepositoryListCallback)
    }

    interface IOnInteractorListener : IRepositoryListCallback
}