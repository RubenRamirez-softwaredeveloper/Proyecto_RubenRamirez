package com.example.proyecto_rubenramirez


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_rubenramirez.Adapter.AjedrecistaAdapter
import com.example.proyecto_rubenramirez.databinding.ActivityListadoBinding
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity : ActivityConMenus(), AjedrecistaAdapter.OnItemClickListener {

    private lateinit var listaAjedrecista: ArrayList<Ajedrecista>
    private lateinit var Recycler: RecyclerView
    private lateinit var adapter: AjedrecistaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Añadimos una linea de separacion entre ajedrecista y ajedrecista
        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.Recycler.addItemDecoration(decoration)

        // Creamos una editText para hacer un buscador para filtrar por nombre
        binding.filtro.addTextChangedListener { filtro ->
            val filtroAjedrecista = listaAjedrecista.filter{ Ajedrecista ->
                Ajedrecista.nombre.lowercase().contains(filtro.toString().lowercase())

            }
            adapter.actualizarAjedrecista(filtroAjedrecista)
        }

        // Inicializa la lista de Ajedrecista y el RecyclerView
        listaAjedrecista = ArrayList()
        Recycler = binding.Recycler
        Recycler.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador
        adapter = AjedrecistaAdapter(listaAjedrecista, this)
        Recycler.adapter = adapter

        // Llamada al método para cargar los datos de personal
        cargarDatos()


    }

    override fun onItemClick(ajedrecista: Ajedrecista) {
        // Cuando se hace clic en un evento, iniciamos la nueva actividad aquí
        val intent = Intent(this, InformacionAjedrecista::class.java)
        intent.putExtra("fide_id", ajedrecista.fideid)
        startActivity(intent)
    }

    private fun cargarDatos() {
        // Obtiene una instancia de la base de datos Firestore y el email del usuario actual
        val db = FirebaseFirestore.getInstance()

        // Filtrar documentos por el campo "Nombre"
        db.collection("Ajedrecista")
            .get()
            .addOnSuccessListener { cargar ->
                cargar.forEach { document ->
                    val persona = document.toObject(Ajedrecista::class.java)
                    persona.fideid = document.id
                    listaAjedrecista.add(persona)
                }
                // Notifica al adaptador de los cambios en los datos después de cargarlos
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Cargar", "Error en la obtención de ajedrecista", exception)
            }

    }
}