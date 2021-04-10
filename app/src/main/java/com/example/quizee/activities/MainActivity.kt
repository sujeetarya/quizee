package com.example.quizee.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizee.R
import com.example.quizee.adapters.QuizAdapter
import com.example.quizee.databinding.ActivityMainBinding
import com.example.quizee.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var quizAdapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore

    private var quizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //populateDummyData()
        setUpViews()
    }

    private fun setUpViews() {
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpFireStore()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        binding.btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date: String = dateFormatter.format(Date(it))

                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)
            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER", "Date picker Canceled")
            }
        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("Data", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            quizAdapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        quizAdapter = QuizAdapter(this, quizList)
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.quizRecyclerView.adapter = quizAdapter
    }

    private fun populateDummyData() {
        quizList.add(Quiz("2012", "1122"))
        quizList.add(Quiz("2151", "112as2"))
        quizList.add(Quiz("sd", "112sa2"))
        quizList.add(Quiz("sd", "1122as"))
        quizList.add(Quiz("2012", "1122"))
        quizList.add(Quiz("2151", "112as2"))
        quizList.add(Quiz("sd", "112sa2"))
        quizList.add(Quiz("sd", "1122as"))
        quizList.add(Quiz("2012", "1122"))
        quizList.add(Quiz("2151", "112as2"))
        quizList.add(Quiz("sd", "112sa2"))
        quizList.add(Quiz("sd", "1122as"))
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.appToolBar)

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.mainDrawer, R.string.app_name, R.string.app_name)
        binding.mainDrawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.btnProfile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnFollowUs -> {
                    //val intent = Intent(this, ProfileActivity::class.java)
                    //startActivity(intent)
                }
                R.id.btnRateUs -> {
                    //val intent = Intent(this, ProfileActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}