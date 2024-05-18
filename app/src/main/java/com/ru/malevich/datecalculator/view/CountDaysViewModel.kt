package com.ru.malevich.datecalculator.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class CountDaysViewModel{
    val dateResult: MutableState<CountDaysResult> = mutableStateOf(CountDaysResult())
    fun evaluateDays(firstDate: String, endDate: String) {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy") // Создаем форматтер для заданного формата

        val date1 = LocalDate.parse(firstDate, formatter)
        val date2 = LocalDate.parse(endDate, formatter)
        val period = Period.between(date1, date2)
        dateResult.value = CountDaysResult(
            period.days,
            period.months,
            period.years
        )
    }
}