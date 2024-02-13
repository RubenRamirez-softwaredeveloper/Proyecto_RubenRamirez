package com.example.proyecto_rubenramirez

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.proyecto_rubenramirez.databinding.ActivityInsertarAjedrecistaBinding
import com.google.firebase.firestore.FirebaseFirestore


class InsertarAjedrecista : ActivityConMenus() {

    lateinit var binding: ActivityInsertarAjedrecistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertarAjedrecistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bGuardar.setOnClickListener{
            // Comprobar que no hay campos vacios
            if(binding.editTextNombre.text.isNotEmpty() && binding.editTextPrimerApellido.text.isNotEmpty() &&
                binding.editTextSegundoApellido.text.isNotEmpty() && binding.editTextAno.text.isNotEmpty() &&
                binding.editTextSexo.text.isNotEmpty() && binding.editTextFIDEID.text.isNotEmpty() &&
                binding.editTextNacionalidad.text.isNotEmpty() && binding.spinnerTituloFide.toString().isNotEmpty() &&
                binding.editTextStandard.text.isNotEmpty() && binding.editTextRapid.text.isNotEmpty() &&
                binding.editTextBlitz.text.isNotEmpty()){

                db.collection("Ajedrecista").document(binding.editTextFIDEID.text.toString())
                    .set(mapOf(
                        //"imagenAjedrecista"
                        "nombre" to binding.editTextNombre.text.toString(),
                        "apellido1" to binding.editTextPrimerApellido.text.toString(),
                        "apellido2" to binding.editTextSegundoApellido.text.toString(),
                        "anyo" to binding.editTextAno.text.toString(),
                        "sexo" to binding.editTextSexo.text.toString(),
                        "federacion" to binding.editTextNacionalidad.text.toString(),
                        "tituloFIDE" to binding.spinnerTituloFide.toString(),
                        "std" to binding.editTextStandard.text.toString(),
                        "rapid" to binding.editTextRapid.text.toString(),
                        "blitz" to binding.editTextBlitz.text.toString()
                    ))
                    Toast.makeText(this, "Se han guardado los datos correctamente",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ListadoActivity::class.java)
                    startActivity(intent)
            }
            else {
                Toast.makeText(this, "Algún campo esta vacio", Toast.LENGTH_SHORT).show()
            }
        }
        // Creo un objeto de la clase spinner
        val titulos:Spinner = findViewById(R.id.spinnerTituloFide)
        val lista = listOf("Gran Maestro", "Maestro Internacional", "Maestro FIDE", "Candidato a Maestro")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        titulos.adapter = adaptador

        // Establece el mensaje predeterminado para que el usuario seleccione una opción
        titulos.prompt = "Seleccione un título"

        titulos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                vista: View?,
                posicion: Int,
                id: Long
            ) {
                val tituloSeleccionado = lista[posicion]
                Toast.makeText(this@InsertarAjedrecista, "Ha seleccionado: $tituloSeleccionado",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}