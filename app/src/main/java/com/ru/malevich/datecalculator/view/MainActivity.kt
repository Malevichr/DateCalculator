package com.ru.malevich.datecalculator.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            MainView(
                addDaysViewModel,
                countDaysViewModel,
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun MainView(
    addDaysViewModel: AddDaysViewModel,
    countDaysViewModel: CountDaysViewModel,
    modifier: Modifier = Modifier

) {
    val navController = rememberNavController()
    Column(

    )
    {
        NavBar(navController = navController)
        NavHost(
            navController = navController,
            startDestination = "addDaysScreen",
            modifier = Modifier.weight(1F)
        ) {
            composable("addDaysScreen") {
                AddDaysScreen(
                    addDaysViewModel,
                    modifier
                )
            }
            composable("countDaysScreen") {
                CountDaysScreen(
                    countDaysViewModel,
                    modifier
                )
            }
        }

    }
}

@Composable
fun NavBar(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Add",
            Modifier
                .clickable { navController.navigate("addDaysScreen") }
                .padding(8.dp),
            fontSize = 22.sp,
            color = Color(0xFF6650a4),

        )
        Text(
            "Count",
            Modifier
                .clickable { navController.navigate("countDaysScreen") }
                .padding(8.dp),
            fontSize = 22.sp,
            color = Color(0xFF6650a4)
        )
    }
}



