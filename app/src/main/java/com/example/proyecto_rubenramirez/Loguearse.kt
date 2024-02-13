package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_rubenramirez.databinding.ActivityLoguearseBinding
import com.google.firebase.auth.FirebaseAuth

class Loguearse : AppCompatActivity() {
    lateinit var binding: ActivityLoguearseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoguearseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bLogin.setOnClickListener{
            if (binding.LEmail.text.isNotEmpty() && binding.LContrasenia.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.LEmail.text.toString(),
                    binding.LContrasenia.text.toString()
                )

                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, ListadoActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "Correo o contraseña incorrecto", Toast.LENGTH_LONG).show()
                        }
                    }

            } else {
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }


}