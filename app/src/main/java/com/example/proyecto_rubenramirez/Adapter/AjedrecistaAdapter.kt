package com.example.proyecto_rubenramirez.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_rubenramirez.Ajedrecista
import com.example.proyecto_rubenramirez.R

// Definición de la clase AjedrecistaAdapter, que extiende RecyclerView.Adapter<AjedrecistaViewHolder>
class AjedrecistaAdapter(private var ajedrecistaList: List<Ajedrecista>, private val listener: OnItemClickListener) : RecyclerView.Adapter<AjedrecistaViewHolder>() {

    // Interfaz para manejar eventos de clic en los elementos del RecyclerView
    interface OnItemClickListener {
        fun onItemClick(ajedrecista: Ajedrecista)
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AjedrecistaViewHolder {
        // Inflar el diseño de la vista del elemento de RecyclerView
        val layoutInflater = LayoutInflater.from(parent.context)
        // Devolver un nuevo ViewHolder con la vista inflada
        return AjedrecistaViewHolder(layoutInflater.inflate(R.layout.item_ajedrecista, parent, false))
    }

    // Método llamado para actualizar el contenido de un ViewHolder con datos específicos
    override fun onBindViewHolder(holder: AjedrecistaViewHolder, position: Int) {
        // Obtener el objeto Ajedrecista en la posición 'position' de la lista
        val item = ajedrecistaList[position]
        // Llamar al método 'render' del ViewHolder para mostrar los datos del Ajedrecista
        holder.render(item)
        // Configurar el evento de clic en la vista del elemento
        holder.itemView.setOnClickListener{
            // Llamar al método 'onItemClick' del listener cuando se hace clic en el elemento
            listener.onItemClick(item)
        }
    }

    // Método llamado para obtener la cantidad de elementos en la lista
    override fun getItemCount(): Int {
        return ajedrecistaList.size
    }

    // Método para actualizar la lista de Ajedrecistas
    fun actualizarAjedrecista(ajedrecistaList: List<Ajedrecista>) {
        // Actualizar la lista de Ajedrecistas con la nueva lista proporcionada
        this.ajedrecistaList = ajedrecistaList
        // Notificar al adaptador que los datos han cambiado
        notifyDataSetChanged()
    }

}