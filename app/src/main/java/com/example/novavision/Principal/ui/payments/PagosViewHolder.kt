package com.example.novavision.Principal.ui.payments

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.novavision.databinding.TarjetaPagosBinding

class PagosViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = TarjetaPagosBinding.bind(view)

    fun render(pagos : PagosModel){

        binding.Descripcion.text = pagos.Descripcion
        binding.Estado.text = pagos.Estado

        binding.detalles.setOnClickListener{

            val builder: AlertDialog.Builder = AlertDialog.Builder(binding.detalles.context)
            if (pagos.Estado.equals("No pagado")){

                builder.setTitle("Fecha boleta emitida: ${pagos.FechaEmitido}")
                builder.setMessage(
                        "\nEstado del pago: ${pagos.Estado} " +
                        "\n\nTotal de pago: ${pagos.Total} " +
                        "\n\nFecha vencimiento: ${pagos.FechaVencimiento}")

                builder.setPositiveButton("Pagar"
                ) { _, _ ->
                    Toast.makeText(
                        this.binding.detalles.context,
                        "Sistema de pago no está listo aún",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                builder.setNegativeButton("Cancelar"
                ) { _, _ ->
                    //Hacer cosas aqui al hacer clic en el boton de aceptar
                }

                builder.show()
            }else if (pagos.Estado.equals("pagado")){

                builder.setTitle("Fecha boleta emitida: ${pagos.FechaEmitido}")
                builder.setMessage(
                        "\nN° Factura: ${pagos.BoletaId}" +
                        "\n\nImpuesto: ${pagos.Impuesto}%" +
                        "\n\nEstado del pago: ${pagos.Estado} " +
                        "\n\nTotal de pago: ${pagos.Total} " +
                        "\n\nFecha vencimiento: ${pagos.FechaVencimiento} " +
                        "\n\nFecha pagado: ${pagos.FechaPagado} " +
                        "\n\nMétodo de pago: ${pagos.Metodo}")

                builder.setPositiveButton("Aceptar"
                ) { _, _ ->
                    //Toast.makeText(this.binding.detalles.context,"Sistema de pago no está listo aún",Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }

        }
    }
}
