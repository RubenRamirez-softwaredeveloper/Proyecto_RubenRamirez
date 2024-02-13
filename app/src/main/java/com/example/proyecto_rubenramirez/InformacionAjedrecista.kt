package com.example.proyecto_rubenramirez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.proyecto_rubenramirez.databinding.ActivityInformacionAjedrecistaBinding
import com.google.firebase.firestore.FirebaseFirestore

// Definición de la clase InformacionAjedrecista, que extiende AppCompatActivity
class InformacionAjedrecista : AppCompatActivity() {

    // Variable para manejar la vinculación de vistas usando View Binding
    lateinit var binding: ActivityInformacionAjedrecistaBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño de la actividad usando View Binding
        binding = ActivityInformacionAjedrecistaBinding.inflate(layoutInflater)
        // Establecer el diseño de la actividad como la vista principal
        setContentView(binding.root)

        // Inicializar una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Obtener el ID del ajedrecista de los extras de la intención
        val ajedrecistaid = intent.getStringExtra("fide_id")

        // Llamar al método para cargar los datos del ajedrecista
        cargarDatosAjedrecista(ajedrecistaid)
    }

    // Método para cargar los datos del ajedrecista desde Firestore
    private fun cargarDatosAjedrecista(ajedrecistaid: String?) {
        // Verificar que el ID del ajedrecista no sea nulo
        if (ajedrecistaid != null) {
            // Obtener una instancia de Firebase Firestore
            val db = FirebaseFirestore.getInstance()

            // Obtener los datos del ajedrecista con el ID proporcionado
            db.collection("Ajedrecista")
                .document(ajedrecistaid)
                .get()
                .addOnSuccessListener { document ->
                    // Extraer los datos del documento del ajedrecista
                    val fideid = document.id
                    val nombre = document.getString("nombre")
                    val apellido1 = document.getString("apellido1")
                    val apellido2 = document.getString("apellido2")
                    val anyo = document.getString("anyo")
                    val sexo = document.getString("sexo")
                    val federacion = document.getString("federacion")
                    val std = document.getString("std")
                    val rapid = document.getString("rapid")
                    val blitz = document.getString("blitz")

                    // Mostrar los datos del ajedrecista en los TextViews de la actividad
                    binding.nombre.text = nombre
                    binding.apellido1.text = apellido1
                    binding.apellido2.text = apellido2
                    binding.anyo.text = anyo
                    binding.sexo.text = sexo
                    binding.federacion.text = federacion
                    binding.fideid.text = fideid
                    binding.std.text = std
                    binding.rapid.text = rapid
                    binding.blitz.text = blitz
                }
                .addOnFailureListener { exception ->
                    // Manejar el caso de error al cargar los datos del ajedrecista
                    Log.e("Cargar", "Error al cargar los datos", exception)
                }
        }
    }
}
