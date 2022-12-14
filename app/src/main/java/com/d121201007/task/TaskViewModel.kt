package com.d121201007.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.d121201007.task.TaskRepository
import com.d121201007.task.TaskModel
import kotlinx.coroutines.launch

class TaskViewModel(
    app: Application,
    private val taskRepository: TaskRepository
) : AndroidViewModel(app) {


    fun addTask(task: TaskModel) =
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }

    fun getAllTask() = taskRepository.getAllTask()


}