package com.example.keureujakeun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Raihan Chaira on 3/4/2024
 * raihanchaira21@gmail.com
 */
class TodoAdapter(val listTodo: MutableList<Todo>, private val onDeleteListener : OnDeleteClickListener): RecyclerView.Adapter<TodoAdapter.ListViewHolder> () {

    interface OnDeleteClickListener{
        fun onDeleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_todos, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTodo.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (checkDone, todoTittle, todoDate) = listTodo[position]
        holder.checkBoxDone.isChecked = checkDone
        holder.tittleTodo.text = todoTittle
        holder.dateTodo.text = todoDate
        holder.deleteTodo.setOnClickListener {
            onDeleteListener.onDeleteClick(position)
        }
    }

    class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val checkBoxDone : CheckBox = view.findViewById(R.id.checkbox)
        val tittleTodo : TextView = view.findViewById(R.id.tv_tittle)
        val dateTodo : TextView = view.findViewById(R.id.tv_date)
        val deleteTodo : ImageButton = view.findViewById(R.id.btn_delete)
    }
}
