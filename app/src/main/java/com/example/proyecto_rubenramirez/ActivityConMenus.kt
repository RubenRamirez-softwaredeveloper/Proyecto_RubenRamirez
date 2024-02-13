package com.example.proyecto_rubenramirez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

// Definición de la clase ActivityConMenus, que extiende AppCompatActivity
open class ActivityConMenus : AppCompatActivity() {

    // Companion object para tener una variable estática compartida entre instancias de la clase
    companion object {
        var actividadActual = 0 // Variable para almacenar la actividad actualmente seleccionada
    }

    // Método llamado para crear el menú de opciones en la barra de acciones
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Relacionamos la clase con el layout del menú que hemos creado:
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)
        // Desactivar la opción de la actividad en la que ya estamos:
        for (i in 0 until menu.size()) {
            if (i == actividadActual) menu.getItem(i).isEnabled = false // Deshabilitar la opción de menú de la actividad actual
            else menu.getItem(i).isEnabled = true // Habilitar la opción de menú de otras actividades
        }
        return true
    }

    // Método llamado cuando se selecciona un elemento del menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Inicio -> {
                actividadActual = 0
                // Hacemos que se abra la pantalla del Inicio:
                val intent = Intent(this, ListadoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.insertar_ajedrecista -> {
                actividadActual = 1
                // Hacemos que se abra la pantalla del Insertar Ajedrecista:
                val intent = Intent(this, InsertarAjedrecista::class.java)
                startActivity(intent)
                true
            }
            R.id.borrar_ajedrecista-> {
                actividadActual = 2
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, BorrarAjedrecista::class.java)
                startActivity(intent)
                true
            }
            R.id.actualizar_ajedrecista-> {
                actividadActual = 3
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, ActualizarAjedrecista::class.java)
                startActivity(intent)
                true
            }
            R.id.salir -> {
                actividadActual = 4
                FirebaseAuth.getInstance().signOut()
                //volver a la loguearse activity
                val intent = Intent(this, Loguearse::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}