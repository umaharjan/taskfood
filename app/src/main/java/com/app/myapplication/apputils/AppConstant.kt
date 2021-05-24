package com.app.myapplication.apputils

object AppConstant {
    const val DEBITCARD = "####  ####  ####  ####"
    const val MMYY = "##/##"

    fun getCradNUmber(str:String):String{
        return    str.replace(" ", "")
    }
    fun getSubstring(str:String):Int{
        return str.substring(0,1).toInt()
    }
}