package model

data class Person(
    val name: String = "",
    val expenses: MutableList<Double> = mutableListOf(),
    var total: Double = 0.0,
    var moneyOwed: Double = 0.0
)
