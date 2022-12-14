package com.d121201007.task

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskViewModelFactoryProvider(
    val app: Application,
    private val taskRepository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(app, taskRepository) as T
    }
}