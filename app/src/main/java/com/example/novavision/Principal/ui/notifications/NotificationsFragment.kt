package com.example.novavision.Principal.ui.notifications

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
import com.example.novavision.databinding.FragmentNotificationsBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val notificacionModel : MutableList<NotificacionModel> = ArrayList()
    private var url = ""
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        ViewModelProvider(this)[NotificationsViewModel::class.java]
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs = requireContext().getSharedPreferences("Preferences", 0)
        val usuario = prefs.getString("usuario", "").toString()

        initRecyclerView(usuario)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val loading = CargandoDialogoNotifications(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed({
            loading.isDismiss()
        },2000)

        return root
    }

    fun initRecyclerView(usuario:String){

        val queue = Volley.newRequestQueue(context)

        //url = "http://192.168.74.59/notificaciones.php?id_cliente=$usuario"
        url = "http://192.168.1.5/App/BD-NOVA/notificaciones.php?id_cliente=$usuario"

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

                val notificacionID = jsonObject.get("id").toString()
                val notificacion = jsonObject.get("mensaje").toString()
                val numeroTelefono = jsonObject.get("ndestino").toString()

                //Formato de fecha
                val arrayFecha = jsonObject.get("enviado").toString().split(":","-"," ")
                val anio = arrayFecha[0]
                val mes = arrayFecha[1]
                val dia = arrayFecha[2]
                val hora = arrayFecha[3]
                val minutos = arrayFecha[4]
                val segundos = arrayFecha[5]
                val fecha = "$dia/$mes/$anio"
                val horas = "$hora:$minutos:$segundos"

                notificacionModel.add(NotificacionModel(notificacionID,notificacion,fecha,horas,usuario,numeroTelefono))

                val recyclerView = binding.Recycler
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = ReciclerViewAdapter(notificacionModel)

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

