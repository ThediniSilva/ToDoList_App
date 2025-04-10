package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            var task = DataObject.getData(pos)

            // Pre-fill form with task details
            create_title.setText(task.title)
            create_priority.setText(task.priority)
            create_description.setText(task.description)
            create_deadline.setText(task.deadline)

            task = DataObject.getData(pos)
            val taskId = task.id

            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(taskId, task.title, task.priority, task.description, task.deadline)
                    )
                }
                navigateToMainActivity()
            }

            update_button.setOnClickListener {
                val updatedTitle = create_title.text.toString()
                val updatedPriority = create_priority.text.toString()
                val updatedDescription = create_description.text.toString()
                val updatedDeadline = create_deadline.text.toString()

                DataObject.updateData(pos, taskId, updatedTitle, updatedPriority, updatedDescription, updatedDeadline)
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(taskId, updatedTitle, updatedPriority, updatedDescription, updatedDeadline)
                    )
                }
                navigateToMainActivity()
            }

        }

        // Navigate back to MainActivity
        imageButton2.setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
