package com.example.feedback2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentDetallesNovela : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detalles_novela, container, false)

        val tituloTextView = view.findViewById<TextView>(R.id.tituloTextView)
        val autorTextView = view.findViewById<TextView>(R.id.autorTextView)
        val sinopsisTextView = view.findViewById<TextView>(R.id.sinopsisTextView)
        val direccionTextView = view.findViewById<TextView>(R.id.direccionTextView) // Nuevo TextView para dirección

        arguments?.getParcelable<Novela>("novela")?.let { novela ->
            tituloTextView.text = novela.titulo
            autorTextView.text = novela.autor
            sinopsisTextView.text = novela.sinopsis
            direccionTextView.text = novela.direccion // Mostrar la dirección
        }

        return view
    }
}
