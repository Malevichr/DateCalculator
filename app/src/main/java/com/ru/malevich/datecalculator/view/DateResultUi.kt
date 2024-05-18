package com.ru.malevich.datecalculator.view

import java.time.LocalDate
import java.time.Month

data class AddDaysResult(
    val date: LocalDate
)
data class CountDaysResult(
    val days: Int = 0,
    val month: Int = 0,
    val years: Int = 0
)