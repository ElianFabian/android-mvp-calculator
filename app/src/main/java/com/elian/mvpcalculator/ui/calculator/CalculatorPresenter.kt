package com.elian.mvpcalculator.ui.calculator

import com.elian.mvpcalculator.data.model.Operation

class CalculatorPresenter(private var view: ICalculatorContract.IView?) :
    ICalculatorContract.IPresenter,
    ICalculatorContract.IOnInteractorListener
{
    //private var interactor: CalculatorContract.IInteractor? = CalculatorInteractor(this)
    private var interactor: ICalculatorContract.IInteractor? = CalculatorInteractor(this)

    //region CalculatorContract.IPresenter

    override fun onValidateData(operation: Operation)
    {
        view?.cleanInputFieldsErrors()
        interactor?.validateData(operation)
    }

    override fun onDestroy()
    {
        view = null
        interactor = null
    }

    //endregion

    //region CalculatorContract.IOnInteractorListener

    override fun onNumber1EmptyError()
    {
        view?.setNumber1EmptyError()
    }

    override fun onNumber2EmptyError()
    {
        view?.setNumber2EmptyError()
    }

    override fun onResultError(message: String)
    {
        view?.setResultError(message)
    }

    override fun onSuccess(operation: Operation)
    {
        view?.onSuccess(operation)
    }

    override fun onFailure()
    {

    }

    //endregion
}