package com.example.keureujakeun

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import com.example.keureujakeun.databinding.ActivityAddTodoBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding
    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnSubmit.setOnClickListener{
            val todo = TodoAdd(
                binding.etTittle.text.toString(),
                binding.etDate.text.toString(),
                binding.etDesc.text.toString()
            )
            val moveWithNewTodo = Intent(this@AddTodoActivity, MainActivity::class.java)
            moveWithNewTodo.putExtra(MainActivity.EXTRA_TODO, todo)
            startActivity(moveWithNewTodo)
        }

        binding.etDate.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year : Int, monthOfYear : Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.etDate.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}