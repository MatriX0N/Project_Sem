package com.example.project_sem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarsManagerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManagerAdapter
    private val dataSet = arrayListOf<ManagerModel>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cars_manager)

        val add=findViewById<Button>(R.id.button_add)
        val finish=findViewById<Button>(R.id.button_finish)

        finish.setOnClickListener{
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ManagerAdapter(dataSet){position->
            showUpdateDialog(position = position, managerModel = dataSet[position] )
        }
        recyclerView.adapter = adapter

        dataSet.addAll(arrayListOf(
            ManagerModel("Олександр Іванович", "8:00", "18:00"),
            ManagerModel("Ірина Петрівна", "9:00", "17:00"),
            ManagerModel("Олег Сергійович", "8:30", "17:30"),
            ManagerModel("Наталія Василівна", "8:00", "16:00"),
            ManagerModel("Марія Олександрівна", "10:00", "19:00"),
            ManagerModel("Віталій Миколайович", "9:00", "18:00"),
            ManagerModel("Анна Володимирівна", "8:00", "17:00"),
            ManagerModel("Павло Анатолійович", "9:30", "18:30"),
            ManagerModel("Олена Михайлівна", "8:00", "18:00"),
            ManagerModel("Ігор Вікторович", "8:00", "17:00")


            ))
        add.setOnClickListener(){
            showAddTeacherDialog()
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddTeacherDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_manager, null)
        val sEditText = dialogView.findViewById<EditText>(R.id.surnameEditText)
        val nEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val pEditText = dialogView.findViewById<EditText>(R.id.patronimicEditText)

        builder.setView(dialogView)
            .setTitle("Додати нового менеджера")
            .setPositiveButton("Додати") { dialog, which ->
                // Отримуємо дані з полів вводу та додаємо новий елемент у список
                val close = sEditText.text.toString()
                val name = nEditText.text.toString()
                val open = pEditText.text.toString()


                dataSet.add(ManagerModel(name, close, open))
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Скасувати") { dialog, which ->
                dialog.cancel()
            }
            .show()
    }

    private fun showUpdateDialog(position: Int, managerModel: ManagerModel) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_manager, null)

        val sEditText = dialogView.findViewById<EditText>(R.id.surnameEditText)
        val nEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val pEditText = dialogView.findViewById<EditText>(R.id.patronimicEditText)

        // Встановлюємо існуючі значення в поля для вводу
        sEditText.setText(managerModel.open)
        nEditText.setText(managerModel.name)
        pEditText.setText(managerModel.close)

        builder.setView(dialogView)
            .setTitle("Редагувати")
            .setPositiveButton("Змінити") { dialog, which ->
                // Отримуємо оновлені дані з полів вводу
                val open = sEditText.text.toString()
                val name = nEditText.text.toString()
                val close = pEditText.text.toString()

                // Оновлюємо елемент у списку
                val updatedTeacher = ManagerModel(name, open, close)
                dataSet[position] = updatedTeacher
                adapter.notifyItemChanged(position)
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Відміна оновлення
                dialog.cancel()
            }
            .show()
    }

}