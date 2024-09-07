package com.example.spinner

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al Spinner
        val spinnerLanguages: Spinner = findViewById(R.id.spinnerLanguages)

        // Opciones para el spinner
        val languages = arrayOf(
            "Select a language", "Kotlin", "Java", "Python", "JavaScript",
            "C++", "C#", "Ruby", "PHP", "SQL", "Swift"
        )

        // Íconos correspondientes a los lenguajes
        val icons = arrayOf(
            R.drawable.ic_placeholder, // Para "Select a language"
            R.drawable.ic_kotlin,
            R.drawable.ic_java,
            R.drawable.ic_python,
            R.drawable.ic_javascript,
            R.drawable.ic_cpp,
            R.drawable.ic_csharp,
            R.drawable.ic_ruby,
            R.drawable.ic_php,
            R.drawable.ic_sql,
            R.drawable.ic_swift
        )

        // Crear un ArrayAdapter personalizado
        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createCustomView(position, parent)
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createCustomView(position, parent)
            }

            private fun createCustomView(position: Int, parent: ViewGroup): View {
                // Usamos el diseño predeterminado del spinner
                val view = layoutInflater.inflate(android.R.layout.simple_spinner_item, parent, false)

                // Encontrar el TextView predeterminado en el layout del spinner
                val text = view.findViewById<TextView>(android.R.id.text1)
                text.text = languages[position]
                text.textSize = 18f

                // Crear un LinearLayout para contener el ícono y el texto
                val layout = LinearLayout(this@MainActivity)
                layout.orientation = LinearLayout.HORIZONTAL

                // Crear ImageView para el ícono
                val icon = ImageView(this@MainActivity)
                val params = LinearLayout.LayoutParams(40, 40)  // Tamaño del ícono
                icon.layoutParams = params

                // Asignar ícono correspondiente
                icon.setImageDrawable(ContextCompat.getDrawable(this@MainActivity, icons[position]))

                // Añadir ícono y texto al layout
                layout.addView(icon)
                layout.addView(text)

                return layout
            }
        }

        // Aplicar el adaptador personalizado al spinner
        spinnerLanguages.adapter = adapter

        // Gestionar la selección del spinner
        spinnerLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 0) {
                    // Si se selecciona "Select a language", no hacer nada
                    return
                }
                // Mostrar el lenguaje seleccionado con un Toast
                Toast.makeText(this@MainActivity, "Selected: ${languages[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acción cuando no se selecciona nada
            }
        }
    }
}
