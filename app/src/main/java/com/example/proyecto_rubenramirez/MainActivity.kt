package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyecto_rubenramirez.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // Definición de la clase MainActivity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño de la actividad usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establecer el diseño de la actividad como la vista principal
        setContentView(binding.root)

        // Configurar un listener para el botón de registro
        binding.registrarse.setOnClickListener {
            // Crear una intención para iniciar la actividad de registro
            val intent = Intent(this, Registrarse::class.java)
            // Iniciar la actividad de registro
            startActivity(intent)
        }

        // Configurar un listener para el botón de inicio de sesión
        binding.loguearse.setOnClickListener {
            // Crear una intención para iniciar la actividad de inicio de sesión
            val intent = Intent(this, Loguearse::class.java)
            // Iniciar la actividad de inicio de sesión
            startActivity(intent)
        }
    }
}