package com.example.simplemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var rightAnswers : TextView
    lateinit var questionView : TextView
    lateinit var answerView: EditText
    var correctAnswer: Int = 0
    var rightAnswerCount = 0
    val math = arrayOf("Minus","Addition","Multiplication","Divided")
    var mathPosistion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)

        questionView = findViewById(R.id.questionView)
        answerView = findViewById(R.id.answerView)
        rightAnswers = findViewById(R.id.rigthAnswers)
        val button = findViewById<Button>(R.id.answerButton)


        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,math)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext,"selected Math language is = "+ math[p2], Toast.LENGTH_SHORT).show()
                Log.d("!!!","this item ${math[p2]} ")
                if (math[p2] == math[0]){
                    setNewQuestionMinus()
                    mathPosistion = 0
                }
                else if (math[p2] == math[1]){
                    setNewQuestionAddition()
                    mathPosistion = 1
                }
                else if (math[p2] == math[2]){
                    setNewQuestionMultiplication()
                    mathPosistion = 2
                }
                else if (math[p2] == math[3]){
                    setNewQuestionDivided()
                    mathPosistion = 3
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }


        button.setOnClickListener {
            handleAnswer()

        }



           // setNewQuestion()



    }

    override fun onResume(){
        super.onResume()
        if (mathPosistion == 0){
            setNewQuestionMinus()
        }
        else if (mathPosistion == 1){
            setNewQuestionAddition()
        }
        else if (mathPosistion == 2){
            setNewQuestionMultiplication()
        }
        else if (mathPosistion == 3){
            setNewQuestionDivided()
        }
    }
    fun handleAnswer(){
        val answerCorrect = checkAnswer()
        Log.d("!!!","Du svarade $answerCorrect")
        val intent = Intent(this,AnswerActivity::class.java)
        intent.putExtra("answeredCorrect",answerCorrect)
        startActivity(intent)
        if (answerCorrect == true){
            rightAnswers()
        }

    }
    fun rightAnswers() {
        rightAnswerCount += 1
        rightAnswers.text ="$rightAnswerCount"
    }



    fun checkAnswer() : Boolean{
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        return answer == correctAnswer


    }

    fun setNewQuestionAddition() {
        val firstNumber = (1.. 10).random()
        val secondNumber = (1.. 10).random()
        correctAnswer = firstNumber + secondNumber

        questionView.text = "$firstNumber + $secondNumber ="

    }
    fun setNewQuestionMinus(){
        val firstNumber = (20..50).random()
        val secondNumber = (1.. 19).random()
        correctAnswer = firstNumber - secondNumber
        questionView.text = "$firstNumber - $secondNumber ="
    }
    fun setNewQuestionMultiplication() {
        val firstNumber = (1.. 10).random()
        val secondNumber = (1.. 10).random()
        correctAnswer = firstNumber * secondNumber

        questionView.text = "$firstNumber * $secondNumber ="

    }
    fun setNewQuestionDivided() {
        val firstNumber = (20.. 100).random()
        val secondNumber = (1.. 19).random()
        correctAnswer = firstNumber / secondNumber

        questionView.text = "$firstNumber / $secondNumber ="

    }
}