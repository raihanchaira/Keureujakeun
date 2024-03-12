package com.example.keureujakeun

import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keureujakeun.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), TodoAdapter.OnDeleteClickListener{

    companion object {
        const val EXTRA_TODO = "extra_todo"
    }

    private lateinit var binding: ActivityMainBinding
    private var todoList = mutableListOf<Todo>()
    private lateinit var adapter: TodoAdapter


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoAdapter(todoList, this)
        binding.rvTodo.adapter = adapter
        binding.rvTodo.layoutManager = LinearLayoutManager(this)

        binding.fabTodo.setOnClickListener{ startActivity(Intent(this, AddTodoActivity::class.java))}

        val todoAdd = intent.getParcelableExtra<TodoAdd>(EXTRA_TODO)

        if (todoAdd != null) {
            todoList.add(Todo(
                false,
                todoAdd.tittle.toString(),
                todoAdd.date.toString()
            ))
            adapter.notifyDataSetChanged()
        }

        var formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM")
        val current = LocalDateTime.now().format(formatter)

        binding.tvDate.text = current

        if (todoList.isEmpty()){
            binding.ivEmptyState.visibility = View.VISIBLE
        }

    }

    override fun onDeleteClick(position: Int) {
        todoList.removeAt(position)
        adapter.notifyDataSetChanged()
        updateEmptyStateVisibility()
    }

    private fun updateEmptyStateVisibility() {
        if (todoList.isEmpty()) {
            binding.ivEmptyState.visibility = View.VISIBLE
        } else {
            binding.ivEmptyState.visibility = View.GONE
        }
    }

}