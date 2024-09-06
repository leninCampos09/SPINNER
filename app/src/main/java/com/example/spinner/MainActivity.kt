package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Reference to Spinner
        val spinnerLanguages: Spinner = findViewById(R.id.spinnerLanguages)

        // Options for the spinner (with the option initial for selection a language)
        val languages = arrayOf("Select a language", "Kotlin", "Java", "Python", "JavaScript", "C++", "C#", "Ruby", "PHP", "SQL", "Swift")

        // Create an ArrayAdapter using Android's default layout and language list
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        // Specifying the layout to use when the options appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinnerLanguages.adapter = adapter

        // Manage spinner selection
        spinnerLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 0) {
                    // If "Select a language" is selected, do nothing
                    return
                }
                // Display the selected language with a Toast
                Toast.makeText(this@MainActivity, "Selected: ${languages[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Action when nothing is selected
            }
        }
    }
}
