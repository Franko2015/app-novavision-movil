package com.example.novavision.Principal.ui.payments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novavision.R

class RecyclerViewAdapter(private val pagos: List<PagosModel>) : RecyclerView.Adapter<PagosViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagosViewHolder(layoutInflater.inflate(R.layout.tarjeta_pagos,parent,false))
    }

    override fun onBindViewHolder(holder: PagosViewHolder, position: Int) {
        val item = pagos[position]
        holder.render(item)
    }

    override fun getItemCount() = pagos.size
}