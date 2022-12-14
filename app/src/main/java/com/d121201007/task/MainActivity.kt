package com.d121201007.task

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.d121201007.task.databinding.ActivityMainBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.task_add.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DB_NAME = "task.db"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var taskViewModel: TaskViewModel

    val list = arrayListOf<TaskModel>()
    var adapter = TaskAdapter(this, list)
    val AppDatabase = com.d121201007.task.AppDatabase

    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        setUpViewModel()

        bottomNavigationView.menu.getItem(0).isEnabled = false

        TaskView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        db.taskDao().getTask().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            } else {
                list.clear()
                adapter.notifyDataSetChanged()
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.form -> startActivity(
                    Intent(
                        this,
                        com.d121201007.task.view.AddTask::class.java
                    )
                )
            }
            true
        }
    }

    private fun setUpViewModel() {
        val taskRepository = TaskRepository(
            AppDatabase(this)
        )

        val viewModelProviderFactory =
            TaskViewModelFactoryProvider(
                application, taskRepository
            )

        taskViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(TaskViewModel::class.java)
    }

    fun AddTask(view: View) {
        startActivity(Intent(this, com.d121201007.task.view.AddTask::class.java))
    }

    fun InputPage(view: View) {
        startActivity(Intent(this, com.d121201007.task.view.AddTask::class.java))
    }

//    fun deleteItem(item : TaskModel, position : Int) {
//        TaskViewModel.deleteTask(item)
//        }

//    fun deleteall() {
//        GlobalScope.launch(Dispatchers.Main) {
//                db.taskDao().deleteAll()
//            }
//        }
    }


