package com.example.novavision.Login

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.novavision.Principal.MainActivity
import com.example.novavision.R
import com.example.novavision.databinding.ActivityLoginBinding
import org.json.JSONArray
import org.json.JSONObject
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.*

class Login : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        const val INTERNET = 200
        const val REQUEST_INTERNET_PERMISSION = 300
        const val PHONE = 200
        const val REQUEST_PHONE_PERMISSION = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Ingresar.setOnClickListener {
            binding.Validacion.setTextColor(Color.parseColor("#00BB86FC"))
            binding.Validacion.setBackgroundColor(Color.parseColor("#00BB86FC"))
            binding.Validacion.text = ""
            hideKeyboard(this.binding.root)
            IniciarSesion() }

        binding.LinkPortal.setOnClickListener { Link("https://novaspa.novavision.cl/cliente/login") }

        requestPermissions()

    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Link(link: String){
        val _link = Uri.parse(link)
        val i = Intent(Intent.ACTION_VIEW, _link)
        startActivity(i)
    }

    private fun IniciarSesion(){

        val usuario = binding.User.text.toString()
        val contrasena = binding.Password.text.toString()

        if(usuario.isBlank()){
            Toast.makeText(this,"Debe llenar el campo de RUT o N° de Cliente",Toast.LENGTH_SHORT).show()
        }else if(contrasena.isBlank()){
            Toast.makeText(this,"Debe llenar el campo de Contraseña",Toast.LENGTH_SHORT).show()
        }else{

        val loading = CargandoDialogoLogin(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed({
            loading.isDismiss()
        },2000)

        //val url = "http://192.168.74.59/login.php"
        val url = "http://192.168.1.5/App/BD-NOVA/login.php"

        val queue = Volley.newRequestQueue(this)

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

        val user = jsonObject.get("id").toString()
        val pass = jsonObject.get("codigo").toString()

        if ( binding.User.text.toString() == user && binding.Password.text.toString() == pass) {

                Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                val prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("usuario", user)
                editor.apply()
                startActivity(intent)
                overridePendingTransition(R.anim.entrar_desde_izquierda, R.anim.salir_derecha)
                finish()
                binding.Validacion.text = ""

        }else{

            binding.Validacion.text = "USUARIO NO ENCONTRADO\nReintente nuevamente"
            binding.Validacion.setBackgroundColor(Color.parseColor("#FFF000"))
            binding.Validacion.setTextColor(Color.parseColor("#000000"))

            }
        }}, { _ ->
                Toast.makeText(this, "No fue posible la conexión", Toast.LENGTH_SHORT).show()
            })
            queue.add(stringRequest)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //
    }
    private fun requestPermissions() {
        EasyPermissions.requestPermissions(
            this,
            "DEBE DAR ACCESO A TELÉFONO PARA UNA MEJOR EXPERCIENCIA EN LA APP",
            REQUEST_PHONE_PERMISSION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        EasyPermissions.requestPermissions(
            this,
            "DEBE DAR ACCESO A INTERNET PARA UNA MEJOR EXPERCIENCIA EN LA APP",
            REQUEST_INTERNET_PERMISSION,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            requestPermissions()
        }
    }
    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }
}