package com.example.proyecto_rubenramirez.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_rubenramirez.Ajedrecista
import com.bumptech.glide.Glide
import com.example.proyecto_rubenramirez.databinding.ItemAjedrecistaBinding

// Definición de la clase AjedrecistaViewHolder, que extiende RecyclerView.ViewHolder
class AjedrecistaViewHolder (view:View): RecyclerView.ViewHolder(view) {

    // Vincular la vista del elemento con el archivo de diseño utilizando View Binding
    val binding = ItemAjedrecistaBinding.bind(view)

    // Método para asignar los datos de un Ajedrecista a la vista
    fun render(filModel: Ajedrecista){
        // Asignar el nombre del Ajedrecista al TextView correspondiente en el diseño
        binding.AjedrecistaNombre.text = filModel.nombre
        // Asignar el primer apellido del Ajedrecista al TextView correspondiente en el diseño
        binding.AjedrecistaApellido1.text = filModel.apellido1
        // Asignar el segundo apellido del Ajedrecista al TextView correspondiente en el diseño
        binding.AjedrecistaApellido2.text = filModel.apellido2
        // Asignar el año del Ajedrecista al TextView correspondiente en el diseño
        binding.AjedrecistaAnyo.text = filModel.anyo
        // Asignar la federación del Ajedrecista al TextView correspondiente en el diseño
        binding.AjedrecistaFederacion.text = filModel.federacion

        // Falta acceder a la imagen del ajedrecista
        //*Glide.with(binding.AjedrecistaPhoto.context).load(filModel.photo).into(binding.AjedrecistaPhoto)

        // Evento de hacer clic sobre la imagen del ajedrecista
        /*binding.AjedrecistaPhoto.setOnClickListener {
            Toast.makeText(binding.AjedrecistaPhoto.context, filModel.federacion,
                Toast.LENGTH_LONG).show()
        }*/

        // Evento de hacer clic sobre el ajedrecista:
        /*itemView.setOnClickListener {
            Toast.makeText(binding.AjedrecistaPhoto.context, filModel.nombre,
                Toast.LENGTH_LONG).show()
        }*/

    }
}