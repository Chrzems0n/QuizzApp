package com.example.quizzapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "Co oznacza skrót IT?",
        "Co to jest program open source?",
        "Jakie są trzy główne rodzaje pamięci RAM?",
        "Co oznacza skrót HTML w kontekście tworzenia stron internetowych?",
        "Jaka jest rola systemu operacyjnego?",
        "Co to jest jednostka centralna przetwarzania (CPU)?",
        "Co to jest algorytm?",
        "Jakie są trzy popularne systemy operacyjne dla komputerów osobistych?",
        "Co to jest aplikacja mobilna",
        "Co to jest chmura obliczeniowa")

    private  val options = arrayOf(
        arrayOf("Information Technology","Informatyka Techniczna","Internet Things","Inteligentna Technologia"),
        arrayOf("Program open source to oprogramowanie dostępne tylko dla firm.","Program open source to oprogramowanie płatne.","Program open source to oprogramowanie, którego kod źródłowy jest dostępny publicznie i może być modyfikowany oraz rozpowszechniany przez innych użytkowników.","Odpowiedź: Program open source to oprogramowanie jedynie dla komputerów Mac"),
        arrayOf("Pamięć VRAM, PRAM i SRAM","SRAM, DRAM i SDRAM","Pamięć CDR, DVD-RAM i Blu-ray RAM","Pamięć DDR3, DDR4 i DDR5."),
        arrayOf("\"HTML\" to skrót od \"High-Throughput Markup Language\""," \"HTML\" to skrót od \"HyperText Markup Language\"","\"HTML\" to skrót od \"Hyperlink Text Markup Language\"","\"HTML\" to skrót od \"Hypnotic Text Manipulation Language\""),
        arrayOf("Odpowiedź: System operacyjny to rodzaj gry komputerowej.","Odpowiedź: System operacyjny to rodzaj przeglądarki internetowej.","Odpowiedź: System operacyjny to rodzaj antywirusa."," System operacyjny to oprogramowanie zarządzające zasobami komputera i umożliwiające uruchamianie innych programów"),
        arrayOf("Jednostka centralna przetwarzania (CPU) to główny procesor komputera, odpowiedzialny za wykonywanie instrukcji i obliczenia","CPU to skrót od \"Central Processing Unit","CPU to rodzaj programu antywirusowego","CPU to rodzaj gry komputerowej"),
        arrayOf("Algorytm to rodzaj kawy","lgorytm to sekwencja kroków lub instrukcji, która określa sposób rozwiązania konkretnego problemu","Algorytm to rodzaj komputera","Algorytm to rodzaj samochodu"),
        arrayOf("Systemy operacyjne to tylko dostępne w Chinach","Systemy operacyjne to tylko dostępne w kosmosie","Systemy operacyjne to tylko dostępne w przeszłości","Trzy popularne systemy operacyjne to: Windows, macOS i Linux"),
        arrayOf("Aplikacja mobilna to rodzaj konsoli do gier","Aplikacja mobilna to rodzaj telewizora","Aplikacja mobilna to oprogramowanie zaprojektowane do działania na urządzeniach przenośnych, takich jak smartfony i tablety","Aplikacja mobilna to rodzaj komputera stacjonarnego"),
        arrayOf("Chmura obliczeniowa to rodzaj meteorologicznej chmury.","Chmura obliczeniowa to usługa, która umożliwia dostęp do zasobów komputerowych, takich jak serwery i przechowywanie danych, za pośrednictwem internetu"," Chmura obliczeniowa to rodzaj aparatu fotograficznego","Chmura obliczeniowa to rodzaj instrumentu muzycznego"))


    private val correctAnswers = arrayOf(0,1,1,2,3,3,0,1,1,1)

    private var currentQuestionIndex =0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()
        binding.optionButton1.setOnClickListener {
            checkAnswer(0)
        }
        displayQuestion()
        binding.optionButton2.setOnClickListener {
            checkAnswer(1)
        }
        displayQuestion()
        binding.optionButton3.setOnClickListener {
            checkAnswer(2)
        }
        displayQuestion()
        binding.optionButton4.setOnClickListener {
            checkAnswer(3)
        }

        binding.restartButton.setOnClickListener {
            restartQuiz()
        }

    }

    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.optionButton1.setBackgroundColor(Color.GREEN)
            1 -> binding.optionButton2.setBackgroundColor(Color.GREEN)
            2 -> binding.optionButton3.setBackgroundColor(Color.GREEN)
            3 -> binding.optionButton4.setBackgroundColor(Color.GREEN)
        }
    }

    private fun incorrectButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0->binding.optionButton1.setBackgroundColor(Color.RED)
            1->binding.optionButton2.setBackgroundColor(Color.RED)
            2->binding.optionButton3.setBackgroundColor(Color.RED)
            3->binding.optionButton4.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.optionButton1.setBackgroundColor(Color.rgb(50,59,96))
        binding.optionButton2.setBackgroundColor(Color.rgb(50,59,96))
        binding.optionButton3.setBackgroundColor(Color.rgb(50,59,96))
        binding.optionButton4.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this,"Twój wynik to: $score prawidłowych odpowiedzi na ${questions.size} pytań", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled=true

    }

    private fun displayQuestion(){
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.questionText.startAnimation(fadeIn)
        binding.optionButton1.startAnimation(fadeIn)
        binding.optionButton2.startAnimation(fadeIn)
        binding.optionButton3.startAnimation(fadeIn)
        binding.optionButton4.startAnimation(fadeIn)
        binding.questionText.text = questions[currentQuestionIndex]
        binding.optionButton1.text = options[currentQuestionIndex][0]
        binding.optionButton2.text = options[currentQuestionIndex][1]
        binding.optionButton3.text = options[currentQuestionIndex][2]
        binding.optionButton4.text = options[currentQuestionIndex][3]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            incorrectButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    displayQuestion()
                } else {
                    showResults()
                }
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.questionText.startAnimation(fadeOut)
        binding.optionButton1.startAnimation(fadeOut)
        binding.optionButton2.startAnimation(fadeOut)
        binding.optionButton3.startAnimation(fadeOut)
        binding.optionButton4.startAnimation(fadeOut)
    }


    //restartowanie quizu - powrot do pierwszego pytania resetowanie score
    private fun restartQuiz(){
        currentQuestionIndex =0
        score =0
        displayQuestion()
       // resetButtonColors()
        binding.restartButton.isEnabled = false
    }

    //zapis score do state by przy obracaniu ekranu nie tracic postępów
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestionIndex", currentQuestionIndex)
        outState.putInt("score", score)
    }
    //zapis wyświetlonych pytań do state by przy obracaniu ekranu nie tracic postępów
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex")
        score = savedInstanceState.getInt("score")
        displayQuestion()
    }

}
