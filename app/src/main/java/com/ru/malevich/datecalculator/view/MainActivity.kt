package com.ru.malevich.datecalculator.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ru.malevich.datecalculator.DateCalcApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val addDaysViewModel = (application as DateCalcApp).addDaysViewModel
            val countDaysViewModel = (application as DateCalcApp).countDaysViewModel
            MainView(addDaysViewModel, countDaysViewModel)
        }
    }
}

@Composable
fun MainView(
    addDaysViewModel: AddDaysViewModel,
    countDaysViewModel: CountDaysViewModel
) {
    val navController = rememberNavController()
    Column()
    {
        NavBar(navController = navController)
        NavHost(navController = navController, startDestination = "addDaysScreen") {
            composable("addDaysScreen") {
                AddDaysScreen(
                    addDaysViewModel
                )
            }
            composable("countDaysScreen") {
                CountDaysScreen(countDaysViewModel)
            }
        }
    }
}

@Composable
fun NavBar(navController: NavController) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            "Home",
            Modifier
                .weight(0.33f)
                .clickable { navController.navigate("addDaysScreen") },
            fontSize = 22.sp,
            color = Color(0xFF6650a4)
        )
        Text(
            "Contacts",
            Modifier
                .weight(0.33f)
                .clickable { navController.navigate("countDaysScreen") },
            fontSize = 22.sp,
            color = Color(0xFF6650a4)
        )
    }
}

@Composable
fun AddDaysScreen(
    addDaysViewModel: AddDaysViewModel,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val dateResult = remember { addDaysViewModel.dateResult }
    val startDate = rememberSaveable() {
        mutableStateOf("14.05.2024")
    }
    val years = rememberSaveable() {
        mutableStateOf(0)
    }
    val month = rememberSaveable() {
        mutableStateOf(0)
    }
    val weeks = rememberSaveable() {
        mutableStateOf(0)
    }
    val days = rememberSaveable() {
        mutableStateOf(0)
    }
    Column {
        TextField(
            value = startDate.value,
            onValueChange = { newText -> startDate.value = newText },
            modifier = modifier,
            label = {
                Text("Start Date")
            }
        )
        TextField(
            value = years.value.toString(),
            onValueChange = { newText -> years.value = newText.toInt() },
            modifier = modifier,
            label = {
                Text("Years")
            }
        )
        TextField(
            value = month.value.toString(),
            onValueChange = { newText -> month.value = newText.toInt() },
            modifier = modifier,
            label = {
                Text("Month")
            }
        )
        TextField(
            value = weeks.value.toString(),
            onValueChange = { newText -> weeks.value = newText.toInt() },
            modifier = modifier,
            label = {
                Text("Weeks")
            }
        )
        TextField(
            value = days.value.toString(),
            onValueChange = { newText -> days.value = newText.toInt() },
            modifier = modifier,
            label = {
                Text("days")
            }
        )
        Text(
            text = "Result: ${dateResult.value.date}",
            fontSize = 24.sp
        )
        Button(
            onClick = {
                addDaysViewModel.dateAfterDays(
                    startDate.value,
                    years.value,
                    month.value,
                    weeks.value,
                    days.value
                )
            }, modifier = modifier
        ) {
            Text("Add days")
        }

    }
}

@Composable
fun CountDaysScreen(countDaysViewModel: CountDaysViewModel) {
    val dateResult = remember {
        countDaysViewModel.dateResult
    }
    val startDate = rememberSaveable() {
        mutableStateOf("14.05.2024")
    }
    val endDate = rememberSaveable() {
        mutableStateOf("14.05.2024")
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = startDate.value,
            onValueChange = { newText -> startDate.value = newText },
            label = {
                Text("Start Date")
            }

        )
        TextField(
            value = endDate.value,
            onValueChange = { newText -> endDate.value = newText },
            label = {
                Text("End Date")
            }
        )
        Text(
            text = "Result: ${dateResult.value.days} days, \n" +
                    "${dateResult.value.month} month, \n" +
                    "${dateResult.value.years} years",
            fontSize = 24.sp
        )
        Button(
            onClick = {
                countDaysViewModel.evaluateDays(
                    startDate.value,
                    endDate.value
                )
            }
        ) {
            Text("Counts")
        }
    }

}