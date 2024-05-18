package com.ru.malevich.datecalculator

import android.app.Application
import androidx.lifecycle.ViewModel
import com.ru.malevich.datecalculator.view.AddDaysViewModel
import com.ru.malevich.datecalculator.view.CountDaysViewModel

class DateCalcApp: Application() {
    lateinit var countDaysViewModel: CountDaysViewModel
    lateinit var addDaysViewModel: AddDaysViewModel
    override fun onCreate() {
        super.onCreate()

        countDaysViewModel = CountDaysViewModel()
        addDaysViewModel = AddDaysViewModel()
    }
}