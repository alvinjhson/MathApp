package com.example.simplemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var questionView : TextView
    lateinit var answerView: EditText
    var correctAnswer: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionView = findViewById(R.id.questionView)
        answerView = findViewById(R.id.answerView)
        val button = findViewById<Button>(R.id.answerButton)
        button.setOnClickListener {
            handleAnswer()

        }
        setNewQuestion()

    }

    override fun onResume(){
        super.onResume()
        setNewQuestion()
    }
    fun handleAnswer(){
        val answerCorrect = checkAnswer()
        Log.d("!!!","Du svarade $answerCorrect")
        val intent = Intent(this,AnswerActivity::class.java)
        intent.putExtra("answeredCorrect",answerCorrect)
        startActivity(intent)

    }


    fun checkAnswer() : Boolean{
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        return answer == correctAnswer


    }

    fun setNewQuestion() {
        val firstNumber = (1.. 10).random()
        val secondNumber = (1.. 10).random()
        correctAnswer = firstNumber + secondNumber

        questionView.text = "$firstNumber + $secondNumber ="

    }
}