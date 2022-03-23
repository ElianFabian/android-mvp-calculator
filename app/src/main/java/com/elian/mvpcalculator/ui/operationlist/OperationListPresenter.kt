package com.elian.mvpcalculator.ui.operationlist

import com.elian.mvpcalculator.data.model.Operation

class OperationListPresenter(private var view: IOperationListContract.IView?) :

    IOperationListContract.IPresenter,
    IOperationListContract.IOnInteractorListener
{
    private var interactor: IOperationListContract.IInteractor? = OperationListInteractor(this)

    //region OperationListContract.IPresenter

    override fun load()
    {
        interactor?.load()
    }

    override fun onDestroy()
    {
        view = null
        interactor = null
    }

    //endregion
    
    //region IOperationListContract.IOnInteractorListener

    override fun onSuccess(list: List<Operation>)
    {
        view?.onSuccess(list)
    }

    //endregion
}