package com.suhaozdemir.agetimecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var txtSelectedDate : TextView? = null
    private var txtMinutes : TextView? = null
    private var txtSeconds : TextView? = null
    private var txtYears : TextView? = null
    private var txtDays : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btn_date)
        txtSelectedDate = findViewById(R.id.txt_selected)
        txtMinutes = findViewById(R.id.txt_minutes)
        txtSeconds = findViewById(R.id.txt_seconds)
        txtDays = findViewById(R.id.txt_days)
        txtYears = findViewById(R.id.txt_years)


        btnDatePicker.setOnClickListener{
           openDatePicker()
        }
    }

    private fun openDatePicker(){

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                txtSelectedDate?.text = selectedDate

                Toast.makeText(this, "Selected Day: $selectedDayOfMonth, Month: ${selectedMonth+1}, Year: $selectedYear", Toast.LENGTH_LONG).show()

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate)

                val selectedDateInMinutes = date.time / 60000
                val currentDateInMinutes = System.currentTimeMillis() / 60000

                val diffInMinutes = currentDateInMinutes - selectedDateInMinutes
                val diffInYears = year - selectedYear

                txtYears?.text = diffInYears.toString()
                txtMinutes?.text = diffInMinutes.toString()
                txtSeconds?.text = (diffInMinutes * 60).toString()
                txtDays?.text = (diffInMinutes / 1440).toString()
            },
        year,
        month,
        day).show()



    }
}