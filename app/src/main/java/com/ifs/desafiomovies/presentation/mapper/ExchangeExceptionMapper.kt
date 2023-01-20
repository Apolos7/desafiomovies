package com.ifs.desafiomovies.presentation.mapper

import android.app.Activity
import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.data.exception.ResponseError

fun Activity.exception(exception: Exception):String{
    return when(exception){
        // TODO: Finalizar mensagem de error
        is ResponseError.IOErrorException -> getString(R.string.ioError)
        else -> String()
    }
}