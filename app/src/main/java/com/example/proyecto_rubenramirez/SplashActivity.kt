package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.proyecto_rubenramirez.databinding.ActivitySplashBinding

// Definición de la clase SplashActivity, que extiende AppCompatActivity
class SplashActivity : AppCompatActivity() {

    // Variable para manejar la vinculación de vistas usando View Binding
    private lateinit var binding: ActivitySplashBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instalación de la pantalla de bienvenida
        val screenSplash = installSplashScreen()

        // Inflar el diseño de la actividad usando View Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        // Establecer el diseño de la actividad como la vista principal
        setContentView(binding.root)

        // Configurar la condición para mantener la pantalla de bienvenida visible
        screenSplash.setKeepOnScreenCondition { true }

        // Hacer que el hilo principal se detenga durante 4000 milisegundos (4 segundos)
        Thread.sleep(4000)

        // Crear una intención para iniciar la actividad principal (MainActivity)
        val intent = Intent(this, MainActivity::class.java)
        // Iniciar la actividad principal
        startActivity(intent)
        // Finalizar la actividad actual (SplashActivity)
        finish()
    }
}
