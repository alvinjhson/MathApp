package com.example.simplemathapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView = findViewById(R.id.resultView)
        val button = findViewById<Button>(R.id.backButton)
        button.setOnClickListener {
            finish()
        }

        val answer = intent.getBooleanExtra("answeredCorrect", false)

        if (answer){
            resultView.text = "Rätt svar"

        }else
            resultView.text = "fel svar"

    }

}