package com.example.novavision.Principal.ui.speedtest

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.example.novavision.Principal.MainActivity
import com.example.novavision.Principal.ui.home.HomeFragment
import com.example.novavision.databinding.FragmentTestBinding

class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Condiciones de testeo")
        builder.setMessage(
                "1. Cerrar todas los programas y aplicaciones que se conectan a internet:\n" +
                "\n" +
                "* Mensajería instantánea (YahooMessenger, Google Talk, Skype, etc.)\n" +
                "* E-mail (Outlook, Windows Live Mail, etc.)\n" +
                "* Gestor P2P (BitTorrent, Kazaa, Ares, Emule, Edonkey, etc.)\n" +
                "\n" +
                "2. Es importante asegurarte de tener un solo computador conectado a internet, si se tiene más de un PC o dispositivo (Consolas de Juegos, Ipod, etc) conectado por la red LAN o WI-FI, se deben apagar o desconectar del router.\n" +
                "\n" +
                "3. Cerrar todas las ventanas del navegador de Internet (Explorer, Chrome, Safari, Firefox), solo mantener abierta la ventana donde se está haciendo la medición.\n" +
                "\n" +
                "4. Siempre se debe efectuar la prueba con el computador conectado al router directo y no a través de la red Wi-Fi. Cuando se realiza pruebas de velocidad sobre la red Wi-Fi, hay que tener en cuenta la interferencia que puede tener dicha red: hornos microondas, teléfonos inalámbricos, otras redes Wi-Fi." +
                "\n" +
                "5. Desde dispositivo móvil se debe asesorar de incluir tecnología 5G tanto en el dispositivo como la conexión hacia el router contratado por la empresa")

            builder.setPositiveButton("Aceptar condiciones"
            ) { _, _ ->
                webView = binding.webView
                webView.webChromeClient = WebChromeClient()
                webView.webViewClient = WebViewClient()
                webView.loadUrl("https://novavision.speedtestcustom.com")
            }
        builder.setNegativeButton("Cancelar"
        ) { _, _ ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        builder.show()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class WebViewClient : android.webkit.WebViewClient() {

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        @SuppressLint("SetJavaScriptEnabled")
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            view.settings.javaScriptEnabled = true
        }
    }

}