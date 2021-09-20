package com.example.leaguecraft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val get_started = findViewById<Button>(R.id.homeButton)

        get_started.setOnClickListener {
            val i = Intent(this, ItemBuild::class.java)
            this.startActivity(i)
        }
    }
}