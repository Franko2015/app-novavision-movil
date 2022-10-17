package com.example.novavision.Principal.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.novavision.Login.Perfil
import com.example.novavision.Principal.Mapa
import com.example.novavision.R
import com.example.novavision.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val prefs = requireActivity().getSharedPreferences("Preferences", 0)
        val usuario = prefs.getString("usuario", "").toString()

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        //Botones principales
        //Perfil
        binding.Perfil.setOnClickListener { Perfil(usuario) }

        //Mapa de oficinas
        binding.OFICINAS.setOnClickListener { Mapas() }
        binding.OFICINAS2.setOnClickListener { Mapas() }
        binding.OFICINAS3.setOnClickListener { Mapas() }
        binding.OFICINAS4.setOnClickListener { Mapas() }

        //Planes página oficial
        val pagina = "http://novavision.cl/"
        binding.PLANES.setOnClickListener { Links(pagina) /*Toast.makeText(context, "En proceso...", Toast.LENGTH_SHORT).show()*/ }
        binding.PLANES2.setOnClickListener { Links(pagina) /*Toast.makeText(context, "En proceso...", Toast.LENGTH_SHORT).show()*/ }
        binding.PLANES3.setOnClickListener { Links(pagina) /*Toast.makeText(context, "En proceso...", Toast.LENGTH_SHORT).show()*/ }
        binding.PLANES4.setOnClickListener { Links(pagina) /*Toast.makeText(context, "En proceso...", Toast.LENGTH_SHORT).show()*/ }

        //Portal de cliente
        val portal = "https://novaspa.novavision.cl/cliente/login"
        binding.PORTALCLIENTE.setOnClickListener { Links(portal) }
        binding.PORTALCLIENTE2.setOnClickListener { Links(portal) }
        binding.PORTALCLIENTE3.setOnClickListener { Links(portal) }
        binding.PORTALCLIENTE4.setOnClickListener { Links(portal) }

        //WhatsApp Soporte
        val soporte = "https://api.whatsapp.com/send?phone=56965753175"
        binding.SOPORTE.setOnClickListener { Links(soporte) }
        binding.SOPORTE2.setOnClickListener { Links(soporte) }
        binding.SOPORTE3.setOnClickListener { Links(soporte) }
        binding.SOPORTE4.setOnClickListener { Links(soporte) }

        //WhatsApp oficinas
        val WhatsApp = "https://api.whatsapp.com/send?phone=569"
        binding.ROMERAL.setOnClickListener { Links("${WhatsApp}44237983") }
        binding.COLBUN.setOnClickListener { Links("${WhatsApp}65752934") }
        binding.LONTUE.setOnClickListener { Links("${WhatsApp}65678936") }
        binding.LOSNICHES.setOnClickListener { Links("${WhatsApp}67035650") }
        binding.MOLINAAGUAFRIA.setOnClickListener { Links("${WhatsApp}32443087") }
        binding.MOLINACENTRO.setOnClickListener { Links("${WhatsApp}65741818") }
        binding.PELARCO.setOnClickListener { Links("${WhatsApp}44237984") }
        binding.RIOCLARO.setOnClickListener { Links("${WhatsApp}65752993") }
        binding.SAGRADAFAMILIA.setOnClickListener { Links("${WhatsApp}57284062") }
        binding.SANCLEMENTE.setOnClickListener { Links("${WhatsApp}57284060") }
        binding.SANRAFAEL.setOnClickListener { Links("${WhatsApp}44237981") }
        binding.SARMIENTO.setOnClickListener { Links("${WhatsApp}65741856") }
        binding.TALCA.setOnClickListener { Links("${WhatsApp}32443085") }
        binding.TENO.setOnClickListener { Links("${WhatsApp}32443086") }

        //Llamar directamente al número de las oficinas oficinas
        binding.LlamarROMERAL.setOnClickListener { Links("tel:944237983") }
        binding.LlamarCOLBUN.setOnClickListener { Links("tel:965752934") }
        binding.LlamarLONTUE.setOnClickListener { Links("tel:965678936") }
        binding.LlamarLOSNICHES.setOnClickListener { Links("tel:967035650") }
        binding.LlamarMOLINAAF.setOnClickListener { Links("tel:932443087") }
        binding.LlamarMOLINAC.setOnClickListener { Links("tel:965741818") }
        binding.LlamarPELARCO.setOnClickListener { Links("tel:944237984") }
        binding.LlamarRIOCLARO.setOnClickListener { Links("tel:965752993") }
        binding.LlamarSAGRADAFAMILIA.setOnClickListener { Links("tel:957284062") }
        binding.LlamarSANCLEMENTE.setOnClickListener { Links("tel:957284060") }
        binding.LlamarSANRAFAEL.setOnClickListener { Links("tel:944237981") }
        binding.LlamarSARMIENTO.setOnClickListener { Links("tel:965741856") }
        binding.LlamarTALCA.setOnClickListener { Links("tel:932443085") }
        binding.LlamarTENO.setOnClickListener { Links("tel:932443086") }

        // Ubicaciones oficinas redirecciona a GoogleMaps
        //No cambiar si las ubicaciones no han cambiado
        val mapas = "https://www.google.com/maps/place/"
        binding.UROMERAL.setOnClickListener { Links("${mapas}34%C2%B057'29.6%22S+71%C2%B007'21.4%22W/@-34.9582158,-71.1247971,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x73e1920dbdeea35c!8m2!3d-34.9582202!4d-71.1226084?hl=es-419") }
        binding.UCOLBUN.setOnClickListener { Links("${mapas}35%C2%B041'43.3%22S+71%C2%B024'23.6%22W/@-35.6953681,-71.4087321,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x5e5b23f52cfe4ae2!8m2!3d-35.6953725!4d-71.4065434?hl=es-419") }
        binding.ULONTUE.setOnClickListener { Links("${mapas}35%C2%B003'24.0%22S+71%C2%B016'12.7%22W/@-35.056673,-71.2723895,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x959ed96cb68d7ebb!8m2!3d-35.0566774!4d-71.2702008?hl=es-419") }
        binding.ULOSNICHES.setOnClickListener { Links("${mapas}35%C2%B003'43.7%22S+71%C2%B010'16.4%22W/@-35.0621358,-71.1734049,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x1deebcdbd935ba52!8m2!3d-35.0621402!4d-71.1712162?hl=es-419") }
        binding.UMOLINAAGUAFRIA.setOnClickListener { Links("${mapas}35%C2%B007'05.7%22S+71%C2%B016'52.3%22W/@-35.1182392,-71.2833743,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0xd40898657b36a84a!8m2!3d-35.1182436!4d-71.2811856?hl=es-419") }
        binding.UMOLINACENTRO.setOnClickListener { Links("${mapas}35%C2%B006'51.1%22S+71%C2%B016'52.2%22W/@-35.1141749,-71.283356,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x50e93f2d7f133ad2!8m2!3d-35.1141793!4d-71.2811673?hl=es-419") }
        binding.UPELARCO.setOnClickListener { Links("${mapas}35%C2%B023'08.6%22S+71%C2%B026'34.1%22W/@-35.3857045,-71.4450012,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x8631c8f2d1bd3210!8m2!3d-35.3857089!4d-71.4428125?hl=es-419") }
        binding.URIOCLARO.setOnClickListener { Links("${mapas}35%C2%B016'57.4%22S+71%C2%B015'21.8%22W/@-35.2826035,-71.2582397,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x583eec18a83dce8!8m2!3d-35.2826079!4d-71.256051?hl=es-419") }
        binding.USAGRADAFAMILIA.setOnClickListener { Links("${mapas}34%C2%B059'49.0%22S+71%C2%B023'01.5%22W/@-34.9969457,-71.3859345,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x6687568fc8a02a97!8m2!3d-34.9969501!4d-71.3837458?hl=es-419") }
        binding.USANCLEMENTE.setOnClickListener { Links("${mapas}35%C2%B032'07.0%22S+71%C2%B029'25.1%22W/@-35.5352731,-71.492486,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x4feefeedfaff929f!8m2!3d-35.5352775!4d-71.4902973?hl=es-419") }
        binding.USANRAFAEL.setOnClickListener { Links("${mapas}35%C2%B018'22.8%22S+71%C2%B030'52.8%22W/@-35.30633,-71.5168572,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x75786cd31c66f33d!8m2!3d-35.3063344!4d-71.5146685?hl=es-419") }
        binding.USARMIENTO.setOnClickListener { Links("${mapas}34%C2%B056'31.5%22S+71%C2%B012'11.2%22W/@-34.942079,-71.2052886,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0xa3f03dc59642be0d!8m2!3d-34.9420834!4d-71.2030999?hl=es-419") }
        binding.UTALCA.setOnClickListener { Links("${mapas}35%C2%B026'38.1%22S+71%C2%B037'36.7%22W/@-35.4439226,-71.6290347,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0xc26dde8c23cd5028!8m2!3d-35.443927!4d-71.626846?hl=es-419") }
        binding.UTENO.setOnClickListener { Links("${mapas}34%C2%B052'11.2%22S+71%C2%B009'42.0%22W/@-34.8697646,-71.163843,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x7b95431c825d8995!8m2!3d-34.869769!4d-71.1616543?hl=es-419") }

        return root

    }

    private fun Links(link: String) {
        val _link = Uri.parse(link)
        val i = Intent(Intent.ACTION_VIEW, _link)
        startActivity(i)
    }
    fun Perfil(usuario:String) {
        val intent = Intent(context, Perfil::class.java)
        intent.putExtra("usuario",usuario)
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.entrar_desde_izquierda, R.anim.salir_derecha)
        if (activity != null){
            activity?.onBackPressed()
        }
    }
    fun Mapas() {
        val intent = Intent(context, Mapa::class.java)
        requireActivity().overridePendingTransition(R.anim.entrar_desde_izquierda, R.anim.salir_derecha)
        if (activity != null){
            activity?.onBackPressed()
        }
        startActivity(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
