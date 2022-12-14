package com.d121201007.task.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.d121201007.task.AppDatabase
import com.d121201007.task.MainActivity
import com.d121201007.task.R
import com.d121201007.task.TaskAdapter
import com.d121201007.task.databinding.TaskAddBinding
import com.d121201007.task.TaskModel
import kotlinx.android.synthetic.main.task_add.bottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_add.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val DB_NAME = "task.db"

class AddTask : AppCompatActivity(), View.OnClickListener {

    val list = arrayListOf<TaskModel>()
    var adapter = TaskAdapter(this,list)

    val db by lazy {
        AppDatabase.getDatabase(this)
    }


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: TaskAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = TaskAddBinding.inflate(layoutInflater)
        setContentView(R.layout.task_add)

        submit.setOnClickListener(this)

        bottomNavigationView.setSelectedItemId(R.id.form);

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }
            true
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.submit -> {
                saveTask()
            }
        }
    }

    private fun saveTask() {

        val title = titleInput.text.toString()

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId
        val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
        val kategori = checkedRadioButton.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext db.taskDao().insertTask(
                    TaskModel(
                        title,
                        kategori
                    )
                )
            }
            finish()
        }
    }

    fun BackHome(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}