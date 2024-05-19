package com.ru.malevich.datecalculator.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate
import java.time.Period

class CountDaysViewModel{
    val dateResult: MutableState<CountDaysResult> = mutableStateOf(CountDaysResult())
    fun evaluateDays(date1: LocalDate, date2: LocalDate) {

        val period = Period.between(date1, date2)
        dateResult.value = CountDaysResult(
            period.days,
            period.months,
            period.years
        )
    }
}