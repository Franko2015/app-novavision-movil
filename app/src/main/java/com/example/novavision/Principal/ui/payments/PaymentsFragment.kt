package com.example.novavision.Principal.ui.payments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.novavision.databinding.FragmentDashboardBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class PaymentsFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val pagosModel : MutableList<PagosModel> = ArrayList()
    private val binding get() = _binding!!
    private var url = ""
    //private val args: PaymentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val paymentsViewModel = ViewModelProvider(this)[PaymentsViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs = requireContext().getSharedPreferences("Preferences", 0)
        val usuario = prefs.getString("usuario", "").toString()

        initRecyclerView(usuario)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val loading = CargandoDialogoPayments(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed({
            loading.isDismiss()
        },2000)

        return root
    }

    fun initRecyclerView(usuario:String){

        val queue = Volley.newRequestQueue(context)

        //url = "http://192.168.74.59/boletas.php?id_cliente=$usuario"
        url = "http://192.168.1.5/App/BD-NOVA/boletas.php?id_cliente=$usuario"

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->

            val decoder1: Base64.Decoder = Base64.getDecoder()
            val decoded1 = String(decoder1.decode(response))

            val decoder2: Base64.Decoder = Base64.getDecoder()
            val decoded2 = String(decoder2.decode(decoded1))

            val decoder3: Base64.Decoder = Base64.getDecoder()
            val decoded3 = String(decoder3.decode(decoded2))

            val jsonArray = JSONArray(decoded3)

            for (i in 0 until jsonArray.length()) {

                val jsonObject = JSONObject(jsonArray.getString(i))

                val pagoID = jsonObject.get("id").toString()
                val estado = jsonObject.get("estado").toString()
                val total = jsonObject.get("total").toString()
                val metodo = jsonObject.get("forma").toString()
                val descripcion = jsonObject.get("descripcion").toString()
                val BoletaId = jsonObject.get("boletaId").toString()
                val Impuesto = jsonObject.get("impuesto").toString()

                val arrayFechaEmitido = jsonObject.get("f_emitido").toString().split(":","-"," ")
                val anioEmitido = arrayFechaEmitido[0]
                val mesEmitido = arrayFechaEmitido[1]
                val diaEmitido = arrayFechaEmitido[2]
                val fecha_emitido = "$diaEmitido-$mesEmitido-$anioEmitido"

                val arrayFechaVencimiento = jsonObject.get("f_vencimiento").toString().split(":","-"," ")
                val anio = arrayFechaVencimiento[0]
                val mes = arrayFechaVencimiento[1]
                val dia = arrayFechaVencimiento[2]
                val fecha_vencimiento = "$dia-$mes-$anio"

                val arrayFechaPagado = jsonObject.get("f_pagado").toString().split(":","-"," ")
                val anioPagado = arrayFechaPagado[0]
                val mesPagado = arrayFechaPagado[1]
                val diaPagado = arrayFechaPagado[2]
                val fecha_pagado = "$diaPagado-$mesPagado-$anioPagado"

                pagosModel.add(PagosModel(pagoID,estado,fecha_emitido,fecha_vencimiento,fecha_pagado,total,metodo,descripcion,BoletaId,Impuesto))

                val recyclerViewPagadas = binding.Pagados
                recyclerViewPagadas.layoutManager = LinearLayoutManager(this.context)
                recyclerViewPagadas.adapter = RecyclerViewAdapter(pagosModel)
            }
        }, {
            Toast.makeText(context, "No fue posible la conexión", Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}