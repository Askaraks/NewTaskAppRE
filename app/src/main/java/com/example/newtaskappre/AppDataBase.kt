package com.example.newtaskappre

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newtaskappre.data.SessionDao
import com.example.newtaskappre.data.SessionEntity
import com.example.newtaskappre.data.TaskDao
import com.example.newtaskappre.data.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        SessionEntity::class
    ],
    version = 1,

    )
abstract class AppDataBase : RoomDatabase() {

    abstract val taskDao: TaskDao
    abstract val sessionDao: SessionDao
}