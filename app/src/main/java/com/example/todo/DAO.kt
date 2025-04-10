package com.example.todo

import androidx.room.*
//Data Access Object
@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity): Long


    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from to_do")
    suspend fun deleteAll()

    @Query("Select * from to_do")
    suspend fun getTasks():List<CardInfo>

}