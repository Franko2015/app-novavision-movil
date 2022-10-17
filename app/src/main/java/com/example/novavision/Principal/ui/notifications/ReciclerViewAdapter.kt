package com.example.novavision.Principal.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novavision.R


class ReciclerViewAdapter(private val notificaciones: List<NotificacionModel>) : RecyclerView.Adapter<NotificacionViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotificacionViewHolder(layoutInflater.inflate(R.layout.tarjeta_notificaciones,parent,false))
    }

    override fun onBindViewHolder(holder: NotificacionViewHolder, position: Int) {
        val item = notificaciones[position]
        holder.render(item)
    }

    override fun getItemCount() = notificaciones.size

}