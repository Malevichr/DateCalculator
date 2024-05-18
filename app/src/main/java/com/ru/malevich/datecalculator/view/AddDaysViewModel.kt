package com.ru.malevich.datecalculator.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddDaysViewModel(
) {
    val dateResult: MutableState<AddDaysResult> = mutableStateOf(AddDaysResult(LocalDate.now()))

    fun dateAfterDays(currentDate: String, years: Int, months: Int, weeks: Int, days: Int) {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy") // Создаем форматтер для заданного формата

        val localDate = LocalDate.parse(currentDate, formatter)
        dateResult.value = AddDaysResult(localDate
            .plusYears(years.toLong())
            .plusMonths(months.toLong())
            .minusWeeks(weeks.toLong())
            .plusDays(days.toLong())
        )
    }
}