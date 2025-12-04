package com.t3n4dh.expense.splitter.app

import kotlin.math.pow

fun Number.addDecimals(maxDecimals: Int = 2): String {
    return if (this.toString().contains(".")) {
        ((this.toDouble() * (10.0.pow(maxDecimals))).toInt() / 10.0.pow(maxDecimals)).toString()
    } else {
        var result = this.toString()
        repeat(maxDecimals) {
            result += "0"
        }
        result
    }
}