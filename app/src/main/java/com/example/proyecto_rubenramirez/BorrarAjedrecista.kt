package com.example.proyecto_rubenramirez


import android.os.Bundle
import com.example.proyecto_rubenramirez.databinding.ActivityBorrarAjedrecistaBinding
import com.google.firebase.firestore.FirebaseFirestore

class BorrarAjedrecista : ActivityConMenus() {

    lateinit var binding: ActivityBorrarAjedrecistaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityBorrarAjedrecistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bEliminarAjedrecista.setOnClickListener {
            db.collection("Ajedrecista")
                .document(binding.BeditTextFIDEID.text.toString())
                .delete()
        }
    }
}