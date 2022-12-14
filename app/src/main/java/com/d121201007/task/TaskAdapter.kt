package com.d121201007.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_card.view.*
import java.util.*

class TaskAdapter(val c: Context, val list: ArrayList<TaskModel>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_card, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position]) // we are passing the object of the list that we made in the ToDoModel.kt
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }


    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        fun MyViewHolder( @NonNull itemView: View ) {
//            var deletebutton = itemView.findViewById<ImageButton>(R.id.imageButton)
//            deletebutton.setOnClickListener(this)
//        }
//
//        override
//        fun onClick(itemView: View) {
//            val task = Task()
//            val ID: Int = list.get(adapterPosition).id.toInt()
//            task.setId(ID)
//        }

        fun bind(taskModel: TaskModel) {
            with(itemView) {
                TaskTitle.text = taskModel.title
                Kategori.text = taskModel.kategori
                val colors = resources.getIntArray(R.array.colorCodes)
                val randomColor = colors[Random().nextInt(colors.size)]
                ColorCodes.setBackgroundColor(randomColor)
            }
        }
    }

}