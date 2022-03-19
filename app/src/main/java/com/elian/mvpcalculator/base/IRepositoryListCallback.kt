package com.elian.mvpcalculator.base

import android.media.VolumeShaper
import com.elian.mvpcalculator.data.model.Operation

interface IRepositoryListCallback
{
    fun onSuccess(list: List<Operation>)
}