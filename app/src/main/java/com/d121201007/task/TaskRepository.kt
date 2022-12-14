package com.d121201007.task

import com.d121201007.task.AppDatabase
import com.d121201007.task.TaskModel

class TaskRepository(private val db: AppDatabase) {

    suspend fun insertTask(note: TaskModel) = db.taskDao().insertTask(note)

    fun getAllTask() = db.taskDao().getTask()

}