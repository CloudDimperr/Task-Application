package com.d121201007.task

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert()
    suspend fun insertTask(TaskModel: TaskModel):Long

    @Query("Select * from TaskModel")
    fun getTask():LiveData<List<TaskModel>>

    @Query("Delete from TaskModel")
    suspend fun deleteAll()

//    @Query("Delete from TaskModel where id=:uid")
//    fun deleteTask(uid:Long)

}