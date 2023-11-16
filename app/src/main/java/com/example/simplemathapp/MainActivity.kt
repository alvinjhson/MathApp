package com.example.simplemathapp

import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var rightAnswers : TextView
    lateinit var questionView : TextView
    lateinit var answerView: EditText
    var correctAnswer: Int = 0
    var rightAnswerCount = 0
    val math = arrayOf("Minus","Addition","Multiplication","Division")
    val difficulty = arrayOf("Easy","Medium","Hard")
    var mathPosistion = 0
    var mathDifficulty = 0

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val difficultySpiner = findViewById<Spinner>(R.id.spinner2)


        questionView = findViewById(R.id.questionView)
       // answerView = findViewById(R.id.answerView)
        rightAnswers = findViewById(R.id.rigthAnswers)
        //val button = findViewById<Button>(R.id.answerButton)
        val anwBt1 = findViewById<Button>(R.id.anw_bt1)
        val anwBt2 = findViewById<Button>(R.id.anw_bt2)
        val anwBt3 = findViewById<Button>(R.id.anw_bt3)
        val anwBt4 = findViewById<Button>(R.id.anw_bt4)
       

        val arrayAdapterDif = ArrayAdapter<String>(this,R.layout.spinner_dropdown,difficulty)
        difficultySpiner.adapter = arrayAdapterDif
        difficultySpiner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext,"Selected difficulty is "+ difficulty[p2], Toast.LENGTH_SHORT).show()
                if (difficulty[p2] == difficulty[0]){
                    mathDifficulty = 0
                    checkMath()

                }else if (difficulty[p2] == difficulty[1]){
                    mathDifficulty = 1
                    checkMath()

                }
                else if (difficulty[p2] == difficulty[2]){
                    mathDifficulty = 2
                    checkMath()

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val arrayAdapter = ArrayAdapter<String>(this,R.layout.spinner_list,math)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext,"Selected Math is "+ math[p2], Toast.LENGTH_SHORT).show()
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
                    setNewQuestionDivision()
                    mathPosistion = 3
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }



        anwBt1.setOnClickListener {
            anwBt1.isSelected = true
            anwBt2.isSelected = false
            anwBt3.isSelected = false
            anwBt4.isSelected = false

            handleAnswer()
        }
        anwBt2.setOnClickListener {
            anwBt1.isSelected = false
            anwBt2.isSelected = true
            anwBt3.isSelected = false
            anwBt4.isSelected = false

            handleAnswer()
        }

        anwBt3.setOnClickListener {
            anwBt1.isSelected = false
            anwBt2.isSelected = false
            anwBt3.isSelected = true
            anwBt4.isSelected = false

            handleAnswer()
        }

        anwBt4.setOnClickListener {
            anwBt1.isSelected = false
            anwBt2.isSelected = false
            anwBt3.isSelected = false
            anwBt4.isSelected = true
            handleAnswer()

        }

        
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
            setNewQuestionDivision()
        }
    }
    fun handleAnswer(){
        val answerCorrect = checkAnswerAlternative()
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



    fun checkAnswer(answer: String): Boolean {
        Log.d("!!!", "value is ${answer}")
        val answers = answer.toIntOrNull()
        return answers == correctAnswer

    }
    fun checkAnswerAlternative(): Boolean {
        val anwBt1 = findViewById<Button>(R.id.anw_bt1)
        val anwBt2 = findViewById<Button>(R.id.anw_bt2)
        val anwBt3 = findViewById<Button>(R.id.anw_bt3)
        val anwBt4 = findViewById<Button>(R.id.anw_bt4)

        when {
            anwBt1.isSelected -> return checkAnswer(anwBt1.text.toString())
            anwBt2.isSelected-> return checkAnswer(anwBt2.text.toString())
            anwBt3.isSelected -> return checkAnswer(anwBt3.text.toString())
            anwBt4.isSelected -> return checkAnswer(anwBt4.text.toString())
            else -> return false
        }
        
    }

    fun setNewQuestionAddition() {

        if (mathDifficulty == 0){
            val firstNumber = (1.. 10).random()
            val secondNumber = (1.. 10).random()
            correctAnswer = firstNumber + secondNumber
            questionView.text = "$firstNumber + $secondNumber = ?"
        }else if(mathDifficulty == 1){
            val firstNumber = (10.. 50).random()
            val secondNumber = (10.. 50).random()
            correctAnswer = firstNumber + secondNumber
            questionView.text = "$firstNumber + $secondNumber = ?"
        }
        else if(mathDifficulty == 2){
            val firstNumber = (50.. 150).random()
            val secondNumber = (50.. 150).random()
            correctAnswer = firstNumber + secondNumber
            questionView.text = "$firstNumber + $secondNumber = ?"
        }
        alternativeAnswers()
    }
    fun setNewQuestionMinus(){
        if (mathDifficulty == 0){
            val firstNumber = (11.. 25).random()
            val secondNumber = (1.. 10).random()
            correctAnswer = firstNumber - secondNumber
            questionView.text = "$firstNumber - $secondNumber = ?"
        }else if(mathDifficulty == 1){
            val firstNumber = (50.. 100).random()
            val secondNumber = (7.. 30).random()
            correctAnswer = firstNumber - secondNumber
            questionView.text = "$firstNumber - $secondNumber = ?"
        }
        else if(mathDifficulty == 2){
            val firstNumber = (70.. 300).random()
            val secondNumber = (10.. 69).random()
            correctAnswer = firstNumber - secondNumber
            questionView.text = "$firstNumber - $secondNumber = ?"
        }
        alternativeAnswers()
    }
    fun setNewQuestionMultiplication() {
        if (mathDifficulty == 0){
            val firstNumber = (1.. 10).random()
            val secondNumber = (1.. 10).random()
            correctAnswer = firstNumber * secondNumber
            questionView.text = "$firstNumber * $secondNumber = ?"
        }else if(mathDifficulty == 1){
            val firstNumber = (10.. 25).random()
            val secondNumber = (10.. 25).random()
            correctAnswer = firstNumber * secondNumber
            questionView.text = "$firstNumber * $secondNumber = ?"
        }
        else if(mathDifficulty == 2){
            alternativeAnswers()
            checkAnswerAlternative()
            val firstNumber = (10.. 50).random()
            val secondNumber = (10.. 50).random()
            correctAnswer = firstNumber * secondNumber
            questionView.text = "$firstNumber * $secondNumber = ?"
        }
        alternativeAnswers()

    }
    fun setNewQuestionDivision() {

        if (mathDifficulty == 0) {
            var firstNumber = (10..20).random()
            var secondNumber = (1..5).random()
            while ( firstNumber %   secondNumber != 0) {
                 firstNumber = (10..20).random()
                 secondNumber = (1..5).random()
            }
            correctAnswer = firstNumber / secondNumber
            questionView.text = "$firstNumber / $secondNumber = ?"
        }
        else if(mathDifficulty == 1){
            var firstNumber = (20.. 50).random()
            var secondNumber = (5.. 12).random()
            while ( firstNumber % secondNumber != 0) {
                firstNumber = (20..50).random()
                secondNumber = (5..15).random()
            }
            correctAnswer = firstNumber / secondNumber
            questionView.text = "$firstNumber / $secondNumber = ?"

        }
        else if(mathDifficulty == 2){
            var firstNumber = (50.. 100).random()
            var secondNumber = (10.. 25).random()
            while ( firstNumber %   secondNumber != 0) {
                firstNumber = (50..100).random()
                secondNumber = (5..25).random()
            }
            correctAnswer = firstNumber / secondNumber
            questionView.text = "$firstNumber / $secondNumber = ?"
        }
        alternativeAnswers()
    }
    fun alternativeAnswers() {
        val anwBt1 = findViewById<Button>(R.id.anw_bt1)
        val anwBt2 = findViewById<Button>(R.id.anw_bt2)
        val anwBt3 = findViewById<Button>(R.id.anw_bt3)
        val anwBt4 = findViewById<Button>(R.id.anw_bt4)

        val answers = listOf(correctAnswer, generateNearbyNumber(correctAnswer), generateNearbyNumber(correctAnswer), generateNearbyNumber(correctAnswer))

        val shuffledAnswers = answers.shuffled()

        anwBt1.text = shuffledAnswers[0].toString()
        anwBt2.text = shuffledAnswers[1].toString()
        anwBt3.text = shuffledAnswers[2].toString()
        anwBt4.text = shuffledAnswers[3].toString()
    }
    fun generateNearbyNumber(base: Int): Int {
        val range = (base - 5)..(base + 5) // change here how it should diff
        return range.random()
    }

    fun checkMath(){
        if (mathPosistion == 1){
            setNewQuestionAddition()
        }else if (mathPosistion == 0){
            setNewQuestionMinus()
        }else if (mathPosistion == 2){
            setNewQuestionMultiplication()
        }else setNewQuestionDivision()
    }
}