package com.example.plants

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDao {

    @Insert
    fun insert(vararg plant: PlantData)

    @get:Query("SELECT * FROM plants")
    val allPlants: LiveData<List<PlantData>>

    @Query("DELETE FROM plants")
    fun deleteAllPlants()
}