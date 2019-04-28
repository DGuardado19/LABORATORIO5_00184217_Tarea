package com.dguardado19.pokefragme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dguardado19.pokefragme.R
import com.dguardado19.pokefragme.models.Pokemon
import com.dguardado19.pokefragme.models.PokemonOne
import kotlinx.android.synthetic.main.nombrespoke.view.*

class Adapter(val items: List<PokemonOne>, val clickListener: (PokemonOne) -> Unit): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nombrespoke, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: PokemonOne, clickListener: (PokemonOne) -> Unit) = with(itemView) {

            colocarnombre.text = item.name

            colocarnombre.setOnClickListener { clickListener(item) }
        }
    }
}