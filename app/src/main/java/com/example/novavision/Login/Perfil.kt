package com.example.novavision.Login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.novavision.Principal.MainActivity
import com.example.novavision.R
import com.example.novavision.databinding.ActivityPerfilBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = this.getSharedPreferences("Preferences", 0)
        val usuario = prefs.getString("usuario", "").toString()

        binding.Volver.setOnClickListener { volver() }

        binding.Cerrar.setOnClickListener { CerrarSesion() }

        cargarDatos(usuario)

        binding.GuardarCambios.setOnClickListener() {
                ActualizarInfoUsuarioNuevaContraseña(usuario)
        }

    }

    private fun CerrarSesion() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.anim.entrar_desde_izquierda, R.anim.salir_derecha)
        finish()
    }
    private fun ActualizarInfoUsuarioNuevaContraseña(usuario:String) {

        //Se realiza consulta por medio de Volley (complemento de android studio)
        val queue = Volley.newRequestQueue(this)

        val contrasenia = binding.ContrasenaNueva.text

        //URL de donde se realiza la consulta sql
        //val url = "http://192.168.74.59/actualizarUsuario.php?id_cliente=$usuario&codigo=$contrasenia"
        val url = "http://192.168.191.251/App/BD-NOVA/actualizarUsuario.php?id_cliente=$usuario&codigo=$contrasenia"

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->

            Toast.makeText(this,"Datos actualizados",Toast.LENGTH_SHORT).show()
            binding.ContrasenaNueva.text.clear()

        }, {
            Toast.makeText(this, "No fue posible la conexión", Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)
    }
    private fun volver() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.anim.entrar_desde_derecha, R.anim.salir_izquierda)
        finish()
    }
    private fun cargarDatos(usuario:String){

        val queue = Volley.newRequestQueue(this)

        //val url = "http://192.168.74.59/usuario.php?id_cliente=$usuario"
        val url = "http://192.168.191.251/App/BD-NOVA/usuario.php?id_cliente=$usuario"

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

                //Se obtienen los datos del cliente
                val ID = jsonObject.get("id").toString()
                val nombre = jsonObject.get("nombre").toString()
                val estado = jsonObject.get("estado").toString()
                val correo = jsonObject.get("correo").toString()
                val movil = jsonObject.get("movil").toString()
                val codigo = jsonObject.get("codigo").toString()
                val direccion_principal = jsonObject.get("direccion_principal").toString()

                //Los datos del cliente al obtenerlos se ingresan en los Input designados
                binding.IdCliente.setText("Número de cliente: $ID")
                binding.ContrasenaAntigua.setText(codigo)
                binding.Correo.setText(correo)
                binding.NombreCompleto.setText(nombre)
                binding.Direccion.setText(direccion_principal)
                binding.Telefono.setText(movil)
                binding.EstadoCuenta.setText(estado)

                if (estado.equals("ACTIVO")){
                    binding.EstadoCuenta.setBackgroundColor(Color.parseColor("#008000"))
                    binding.EstadoCuenta.setTextColor(Color.parseColor("#ffffff"))
                }else if(estado.equals("SUSPENDIDO")){
                    binding.EstadoCuenta.setBackgroundColor(Color.parseColor("#FFF000"))
                    binding.EstadoCuenta.setTextColor(Color.parseColor("#000000"))
                }
            }
        }, {
            Toast.makeText(this, "No fue posible la conexión", Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)

    }

}