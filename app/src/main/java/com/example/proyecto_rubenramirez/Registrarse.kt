package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_rubenramirez.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registrarse : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bRegistro.setOnClickListener {

            //comprobar que ningún campo está vacío
            if(binding.RNombreUsuario.text.isNotEmpty() && binding.RContrasenia.text.isNotEmpty()
                && binding.REmail.text.isNotEmpty()){

                //acceder a firebase con el método createUser... y le pasamos el correo y el password
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.REmail.text.toString(), binding.RContrasenia.text.toString()
                )
                    .addOnCompleteListener{
                        //si el usuario se ha registrado correctamente muestra la pantalla de bienvenida
                        if(it.isSuccessful){

                            // registrar en la base de datos los datos del usuario

                            db.collection("Usuarios").document(binding.REmail.text.toString())
                                .set(mapOf(
                                    "nombre" to binding.RNombreUsuario.text.toString(),

                                ))

                            Toast.makeText(this, "Se han registrado los datos con éxito", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Loguearse::class.java))
                        }
                        //si no, nos avisa del error
                        else{
                            Toast.makeText(this, "No se han podido registrar los datos", Toast.LENGTH_SHORT).show()}
                    }

            }else{
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()}
        }
    }
}