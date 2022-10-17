package com.example.novavision.Principal.ui.notifications

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.novavision.databinding.TarjetaNotificacionesBinding

class NotificacionViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = TarjetaNotificacionesBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(notificaciones : NotificacionModel){
        binding.fecha.text = "Fecha: ${notificaciones.Fecha} \n\nHora: ${notificaciones.Hora}"
        binding.Detalles.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(binding.Detalles.context)
            builder.setTitle(notificaciones.Fecha)
            builder.setMessage(notificaciones.Notificacion)
            builder.setPositiveButton("Aceptar"
            ) { dialog, which ->
                //Hacer cosas aqui al hacer clic en el botón de aceptar
            }
            builder.show()
        }
    }
}