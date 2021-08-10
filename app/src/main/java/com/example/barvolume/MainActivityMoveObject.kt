package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivityMoveObject : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_move_object)

        val tvObject: TextView = findViewById(R.id.tv_object_recived)
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
        val text = "Name : ${person.name.toString()},\nEmail : ${person.email},\nAge : ${person.age},\nLocation : ${person.city}"
        tvObject.text = text

    }
}