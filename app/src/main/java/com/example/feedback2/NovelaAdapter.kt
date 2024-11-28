package com.example.feedback2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

class NovelaAdapter(
    private var novelas: List<Novela>,
    private val clickListener: (Novela) -> Unit
) : RecyclerView.Adapter<NovelaAdapter.NovelaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovelaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_novela, parent, false)
        return NovelaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NovelaViewHolder, position: Int) {
        val novela = novelas[position]
        holder.bind(novela)
        holder.itemView.setOnClickListener {
            WeakReference(clickListener).get()?.invoke(novela)
        }
    }

    override fun getItemCount(): Int = novelas.size

    // Método para actualizar la lista de novelas
    fun updateData(newNovelas: List<Novela>) {
        novelas = newNovelas
        notifyDataSetChanged()
    }

    class NovelaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        private val autorTextView: TextView = itemView.findViewById(R.id.autorTextView)

        fun bind(novela: Novela) {
            tituloTextView.text = novela.titulo
            autorTextView.text = novela.autor
        }
    }
}
