package com.example.project_sem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarsInfoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarAdapter
    private val dataSet = arrayListOf<CarModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars_info)
        val finish=findViewById<Button>(R.id.button_finish)
        finish.setOnClickListener{
            finish()
        }
        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CarAdapter(dataSet){ position->
            showUpdateDialog(position = position, carModel = dataSet[position] )
        }
        recyclerView.adapter = adapter
        dataSet.addAll(arrayListOf(
            CarModel("BMW", "4.4L", "280KM", "65L"),
            CarModel("Mercedes", "3.0L", "320KM", "70L"),
            CarModel("Audi", "3.2L", "300KM", "60L"),
            CarModel("Toyota", "2.5L", "200KM", "50L"),
            CarModel("Honda", "2.0L", "180KM", "45L"),
            CarModel("Ford", "2.7L", "250KM", "55L"),
            CarModel("Chevrolet", "5.7L", "350KM", "80L"),
            CarModel("Nissan", "3.5L", "270KM", "65L"),
            CarModel("Volkswagen", "2.0L", "220KM", "50L"),
            CarModel("Hyundai", "2.4L", "190KM", "45L")
        ))
    }
    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun showUpdateDialog(position: Int, carModel: CarModel) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.car_info_dialog, null)
        val start = dialogView.findViewById<TextView>(R.id.start_month)
        val end = dialogView.findViewById<TextView>(R.id.end_month)
        val time = dialogView.findViewById<TextView>(R.id.time)
        start.text="Об'єм двигута та макс швидкість:"
        end.text=carModel.engineVolume+"-"+carModel.maxSpeed
        time.text="Об'єм баку: "+carModel.tankVolume
        builder.setView(dialogView)
            .setTitle(carModel.name)
            .setNegativeButton("Назад") { dialog, which ->
                dialog.cancel()
            }
            .show()
    }
}