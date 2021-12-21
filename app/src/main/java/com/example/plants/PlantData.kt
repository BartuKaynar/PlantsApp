package com.example.plants

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class PlantData(
    @ColumnInfo
    val plant_name: String,
    @ColumnInfo
    val plant_family: String,
    @ColumnInfo
    val plant_sci: String,
    @ColumnInfo
    val plant_climate: String,
    @ColumnInfo
    val plant_image: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null
}