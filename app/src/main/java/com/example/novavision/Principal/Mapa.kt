package com.example.novavision.Principal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.novavision.R
import com.example.novavision.databinding.ActivityMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Mapa : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        Oficinas()

        binding.Volver.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.overridePendingTransition(R.anim.entrar_desde_izquierda, R.anim.salir_derecha)
            finish()
        }
    }

    private fun Oficinas(){
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.oficinas,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun Marcadores(){

        //Coordenadas de oficinas
        val Romeral = LatLng(-34.95822019970425, -71.12260835327383)
        val Molina_Centro = LatLng(-35.11417931774851, -71.28116731905473)
        val Los_niches = LatLng(-35.06214024547406, -71.17121621176265)
        val Rio_claro = LatLng(-35.28260789983392, -71.25605096976884)
        val Sarmiento = LatLng(-34.942083375764525, -71.20309987309201)
        val Teno = LatLng(-34.86976903267409, -71.16165426134334)
        val SagradaFamilia = LatLng(-34.99695009435611, -71.38374578965134)
        val SanRafael = LatLng(-35.306334437924406, -71.51466846029354)
        val Lontue = LatLng(-35.056677422245706, -71.27020081244078)
        val Colbun = LatLng(-35.69537252366689, -71.40654338554523)
        val Pelarco = LatLng(-35.38570886741166, -71.44281250372283)
        val MolinaAguaFria = LatLng(-35.11824363607991, -71.28118562460664)
        val SanClemente = LatLng(-35.53527748974247, -71.49029729435368)
        val Talca = LatLng(-35.443927029639305, -71.62684604652296)

        //Marcadores de las oficinas
        mMap.addMarker( MarkerOptions().position(Romeral).title("OFICINA ROMERAL") )
        mMap.addMarker( MarkerOptions().position(Molina_Centro).title("OFICINA MOLINA CENTRO") )
        mMap.addMarker( MarkerOptions().position(Los_niches).title("OFICINA LOS NICHES") )
        mMap.addMarker( MarkerOptions().position(Rio_claro).title("OFICINA RIO CLARO") )
        mMap.addMarker( MarkerOptions().position(Sarmiento).title("OFICINA SARMIENTO") )
        mMap.addMarker( MarkerOptions().position(Teno).title("OFICINA TENO") )
        mMap.addMarker( MarkerOptions().position(SagradaFamilia).title("OFICINA SAGRADA FAMILIA") )
        mMap.addMarker( MarkerOptions().position(SanRafael).title("OFICINA SAN RAFAEL") )
        mMap.addMarker( MarkerOptions().position(Lontue).title("OFICINA LONTUE") )
        mMap.addMarker( MarkerOptions().position(Colbun).title("OFICINA COLBUN") )
        mMap.addMarker( MarkerOptions().position(Pelarco).title("OFICINA PELARCO") )
        mMap.addMarker( MarkerOptions().position(MolinaAguaFria).title("OFICINA MOLINA AGUA FRIA 'CASA CENTRAL' ") )
        mMap.addMarker( MarkerOptions().position(SanClemente).title("OFICINA SAN CLEMENTE") )
        mMap.addMarker( MarkerOptions().position(Talca).title("OFICINA TALCA") )

        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(Rio_claro,9f),3000,null
        )

        //Spinner para selección de sucursal
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Rio_claro,9f),1500,null
                        )
                    }
                    1 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Romeral,16f),1500,null
                        )
                    }
                    2 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Colbun,16f),1500,null
                        )
                    }
                    3 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Lontue,16f),1500,null
                        )
                    }
                    4 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Los_niches,16f),1500,null
                        )
                    }
                    5 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(MolinaAguaFria,16f),1500,null
                        )
                    }
                    6 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Molina_Centro,16f),1500,null
                        )
                    }
                    7 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Pelarco,16f),1500,null
                        )
                    }
                    8 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Rio_claro,16f),1500,null
                        )
                    }
                    9 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(SagradaFamilia,16f),1500,null
                        )
                    }
                    10 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(SanClemente,16f),1500,null
                        )
                    }
                    11 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(SanRafael,16f),1500,null
                        )
                    }
                    12 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Talca,16f),1500,null
                        )
                    }
                    13 -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Teno,16f),1500,null
                        )
                    }
                    else -> {
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(Rio_claro,9f),3000,null
                        )
                    }
                }

            }


            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Marcadores()
    }
}