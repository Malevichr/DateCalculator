package com.ru.malevich.datecalculator.view

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

class AddDaysViewModel(
) {
    val dateResult: MutableState<AddDaysResult> = mutableStateOf(AddDaysResult(LocalDate.now()))

    fun dateAfterDays(localDate: LocalDate, years: Int, months: Int, weeks: Int, days: Int) {

        dateResult.value = AddDaysResult(localDate
            .plusYears(years.toLong())
            .plusMonths(months.toLong())
            .minusWeeks(weeks.toLong())
            .plusDays(days.toLong())
        )
    }
}