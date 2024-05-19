package com.ru.malevich.datecalculator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun CountDaysScreen(
    countDaysViewModel: CountDaysViewModel,
    modifier: Modifier = Modifier
) {
    val dateResult = remember {
        countDaysViewModel.dateResult
    }
    val startDate = rememberSaveable() {
        mutableStateOf(LocalDate.now())
    }
    val endDate = rememberSaveable() {
        mutableStateOf(LocalDate.now())
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DateSelect(dateState = startDate, "Start date", modifier)
        DateSelect(dateState = endDate, "End date", modifier)

        Text(
            text = "Result: ${dateResult.value.days} days, \n" +
                    "${dateResult.value.month} month, \n" +
                    "${dateResult.value.years} years",
            fontSize = 24.sp,
            modifier = modifier
        )
        Button(
            onClick = {
                countDaysViewModel.evaluateDays(
                    startDate.value,
                    endDate.value
                )
            },
            modifier = modifier
        ) {
            Text("Counts")
        }
    }
}