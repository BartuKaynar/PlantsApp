package com.example.plants

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import getJsonDataFromAsset
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: PlantRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(this, PlantRoomDatabase::class.java, "database")
            .build()
        val jsonFileString = getJsonDataFromAsset(this, "MOCK_DATA.json")
        Log.i("data", "MOCK_DATA.json")

        val gson = Gson()
        val listPlantType = object : TypeToken<List<PlantData>>() {}.type
        val plants: List<PlantData> = gson.fromJson(jsonFileString, listPlantType)
        plants.forEachIndexed { idx, plant -> Log.i("data", "> Item $idx:\n$plant") }

        val data = ArrayList<PlantData>()
        for (i in plants) {
            val img = getImage()
            val plant = PlantData(i.plant_name, i.plant_family, i.plant_sci, i.plant_climate, img)
            GlobalScope.launch {
                db.plantDao().insert(plant)
                data.add(plant)
            }
        }
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(data, supportFragmentManager)
        recyclerview.adapter = adapter
    }

    fun getImage(): Int {
        var img = R.drawable.bir
        var x = (1..32).random()
        when (x) {
            1 -> img = R.drawable.bir
            2 -> img = R.drawable.iki
            3 -> img = R.drawable.uc
            4 -> img = R.drawable.dort
            5 -> img = R.drawable.bes
            6 -> img = R.drawable.alti
            7 -> img = R.drawable.yedi
            8 -> img = R.drawable.sekiz
            9 -> img = R.drawable.dokuz
            10 -> img = R.drawable.on
            11 -> img = R.drawable.onbir
            12 -> img = R.drawable.oniki
            13 -> img = R.drawable.onuc
            14 -> img = R.drawable.ondort
            15 -> img = R.drawable.onbes
            16 -> img = R.drawable.onalti
            17 -> img = R.drawable.onyedi
            18 -> img = R.drawable.onsekiz
            19 -> img = R.drawable.ondokuz
            20 -> img = R.drawable.yirmi
            21 -> img = R.drawable.yirmibir
            22 -> img = R.drawable.yirmiiki
            23 -> img = R.drawable.yirmiuc
            24 -> img = R.drawable.yirmidort
            25 -> img = R.drawable.yirmibes
            26 -> img = R.drawable.yirmialti
            27 -> img = R.drawable.yirmiyedi
            28 -> img = R.drawable.yirmisekiz
            29 -> img = R.drawable.yirmidokuz
            30 -> img = R.drawable.otuz
            31 -> img = R.drawable.otuzbir
            32 -> img = R.drawable.otuziki
        }
        return img
    }
}
