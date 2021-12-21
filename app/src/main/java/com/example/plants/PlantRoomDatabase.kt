package com.example.plants

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.BufferedReader
import java.io.InputStream
import java.lang.StringBuilder

@Database(entities = [PlantData::class], version = 1)
abstract class PlantRoomDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        private var plantRoomInstance: PlantRoomDatabase? = null

        fun getDatabase(context: Context): PlantRoomDatabase? {
            if (plantRoomInstance == null) {
                synchronized(PlantRoomDatabase::class.java) {
                    if (plantRoomInstance == null) {
                        plantRoomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            PlantRoomDatabase::class.java, "plant_database"
                        ).build()
                    }
                }
            }
            return plantRoomInstance
        }

    }
}