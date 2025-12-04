package com.t3n4dh.expense.splitter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.t3n4dh.expense.splitter.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ExpenseSplitter",
    ) {
        App()
    }
}