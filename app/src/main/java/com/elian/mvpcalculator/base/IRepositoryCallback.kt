package com.elian.mvpcalculator.base

import com.elian.mvpcalculator.data.model.Operation

interface IRepositoryCallback
{
    fun onSuccess(operation: Operation)
    fun onFailure()
}