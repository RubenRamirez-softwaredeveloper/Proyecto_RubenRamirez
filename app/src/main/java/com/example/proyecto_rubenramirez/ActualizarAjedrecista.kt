package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_rubenramirez.databinding.ActivityActualizarAjedrecistaBinding
import com.google.firebase.firestore.FirebaseFirestore

// Definición de la clase ActualizarAjedrecista, que extiende ActivityConMenus
class ActualizarAjedrecista : ActivityConMenus() {

    // Variable para manejar la vinculación de vistas usando View Binding
    lateinit var binding: ActivityActualizarAjedrecistaBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el diseño de la actividad usando View Binding
        binding = ActivityActualizarAjedrecistaBinding.inflate(layoutInflater)
        // Establecer el diseño de la actividad como la vista principal
        setContentView(binding.root)

        // Inicializar una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Configurar un listener para el botón de actualización
        binding.bActualizarAjedrecista.setOnClickListener {
            // Obtener el FIDE ID y el nombre del EditText
            val fideID = binding.editTextFIDEID.text.toString()
            val nombre = binding.editTextNombre.text.toString()

            // Verificar que los campos no estén vacíos
            if (fideID.isNotEmpty() && nombre.isNotEmpty() && nombre.isNotEmpty()) {
                // Referencia al documento del ajedrecista en Firestore
                val ajedrecistaRef = db.collection("Ajedrecista").document(fideID)
                // Actualizar el nombre del ajedrecista en Firestore
                ajedrecistaRef.update("nombre", nombre)
                    .addOnSuccessListener {
                        // Mostrar un mensaje de éxito y redirigir a la actividad de listado
                        Toast.makeText(this, "Se ha actualizado correctamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ListadoActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        // Mostrar un mensaje de error en caso de fallo
                        Toast.makeText(this, "Error al actualizar: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Mostrar un mensaje si algún campo está vacío
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

        /*binding.bActualizarAjedrecista.setOnClickListener {
            db.collection("Ajedrecista")
                .whereEqualTo("editTextFIDEID", binding.editTextFIDEID.text.toString())
                .get().addOnSuccessListener {
                    it.forEach {
                        binding.editTextNombre.setText(it.get("nombre") as String?)
                        binding.editTextPrimerApellido.setText(it.get("apellido1") as String?)
                        binding.editTextSegundoApellido.setText(it.get("apellido2") as String?)
                        binding.editTextAno.setText(it.get("anyo") as String?)
                        binding.editTextSexo.setText(it.get("sexo") as String?)
                        binding.editTextNacionalidad.setText(it.get("federacion") as String?)
                        /*binding.spinnerTituloFide.toString(it.get("Blitz") as String?)*/
                        binding.editTextStandard.setText(it.get("std") as String?)
                        binding.editTextRapid.setText(it.get("rapid") as String?)
                        binding.editTextBlitz.setText(it.get("blitz") as String?)

                    }
                }
        }*/

