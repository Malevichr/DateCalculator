package com.ru.malevich.datecalculator.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDaysScreen(
    addDaysViewModel: AddDaysViewModel,
    modifier: Modifier = Modifier
) {

    val dateResult = remember { addDaysViewModel.dateResult }

    val startDate = rememberSaveable() {
        mutableStateOf(LocalDate.now())
    }
    val years = rememberSaveable() {
        mutableIntStateOf(0)
    }
    val month = rememberSaveable() {
        mutableIntStateOf(0)
    }
    val weeks = rememberSaveable() {
        mutableIntStateOf(0)
    }
    val days = rememberSaveable() {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        DateSelect(
            startDate,
            "Start Date",
            modifier
        )

        IntPicker(years, "Years", modifier)
        IntPicker(month, "Month", modifier)
        IntPicker(weeks, "Weeks", modifier)
        IntPicker(days, "Days", modifier)

        Text(
            text = "Result: ${dateResult.value.date}",
            fontSize = 24.sp,
            modifier = modifier
        )
        Button(
            onClick = {
                addDaysViewModel.dateAfterDays(
                    startDate.value,
                    years.intValue,
                    month.intValue,
                    weeks.intValue,
                    days.intValue
                )
            }, modifier = modifier
        ) {
            Text("Add days")
        }

    }
}

@Composable
fun IntPicker(intState: MutableIntState, text: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = intState.intValue.toString(),
        onValueChange = { newText -> intState.intValue = newText.toIntOrNull() ?: 0 },
        modifier = modifier,
        label = {
            Text(text)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelect(
    dateState: MutableState<LocalDate>,
    label: String,
    modifier: Modifier = Modifier) {
    val openStartDatePickerDialog = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = dateState.value.toString(),
        onValueChange = {},
        enabled = false,
        modifier = modifier.clickable {
            openStartDatePickerDialog.value = true
        },
        label = {
            Text(label)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color(30, 30, 30),
            disabledLabelColor = Color(30, 30, 30),
            disabledTextColor = Color(30, 30, 30)
        )
    )
    if (openStartDatePickerDialog.value) {
        val startDatePickerState = rememberDatePickerState(LocalDate.now().toEpochDay() * 86400000)
        DatePickerDialog(
            onDismissRequest = { openStartDatePickerDialog.value = false },
            confirmButton = {
                Button(onClick = {
                    dateState.value = LocalDate.ofEpochDay(
                        startDatePickerState.selectedDateMillis!! / 86400000L
                    )
                    openStartDatePickerDialog.value = false
                }) {
                    Text(text = "OK")
                }
            }) {
            DatePicker(state = startDatePickerState)
        }
    }
}