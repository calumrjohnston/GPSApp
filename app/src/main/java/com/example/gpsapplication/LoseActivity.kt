package com.example.gpsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoseActivity : AppCompatActivity() {

    //Simple function to create form, on button press bring user to main menu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose)
    }

    fun launchMainMenuLose(view: View){
        val exit = Intent(this, MainActivity::class.java)
        startActivity(exit)
    }
}
