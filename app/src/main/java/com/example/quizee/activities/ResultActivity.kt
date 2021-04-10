package com.example.quizee.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizee.adapters.ResultAnswerAdapter
import com.example.quizee.databinding.ActivityResultBinding
import com.example.quizee.models.Quiz
import com.example.quizee.models.ResultAnswer
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    lateinit var quiz: Quiz
    lateinit var resultAnswerAdapter: ResultAnswerAdapter

    private var resultAnswerList = mutableListOf<ResultAnswer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)
        calculateScore()
        //setAnswerView()
        setAnswerRecyclerView()
    }

    /*private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color'#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.txtAnswer.text = Html.fromHtml(builder.toString())
        }
    }*/

    private fun setAnswerRecyclerView() {
        //val questions: MutableMap<String, Question> = quiz.questions.entries
        for (entry in quiz.questions.entries) {
            val question = entry.value
            resultAnswerList.add(ResultAnswer(question.description,question.userAnswer,question.answer))
        }
        resultAnswerAdapter = ResultAnswerAdapter(this, resultAnswerList)
        binding.rvAnswer.layoutManager = LinearLayoutManager(this)
        binding.rvAnswer.adapter = resultAnswerAdapter

    }
    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        binding.txtScore.text = "Your Score : $score / ${(quiz.questions.size)*10}"

    }

}