package com.d121201007.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    var title:String,
    var kategori: String,
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
)