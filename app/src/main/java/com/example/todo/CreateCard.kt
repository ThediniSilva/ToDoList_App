package com.example.todo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.android.synthetic.main.activity_create_card.create_deadline
import kotlinx.android.synthetic.main.activity_create_card.create_description
import kotlinx.android.synthetic.main.activity_create_card.create_priority
import kotlinx.android.synthetic.main.activity_create_card.create_title
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        imageButton3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_priority.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_description.text.toString().trim { it <= ' ' }.isNotEmpty()
                && create_deadline.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.getText().toString()
                var priority = create_priority.getText().toString()
                var description = create_description.getText().toString()
                var deadline = create_deadline.getText().toString()

                DataObject.setData(title, priority,description, deadline)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, description, deadline))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }
        }
    }
}

