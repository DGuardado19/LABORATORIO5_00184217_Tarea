package com.dguardado19.pokefragme


import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dguardado19.pokefragme.adapter.Adapter
import com.dguardado19.pokefragme.models.PokemonAll
import com.dguardado19.pokefragme.models.PokemonOne
import com.dguardado19.pokefragme.utilities.NetworkUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_fragprron.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragprron : Fragment() {

    var listaAll : ArrayList<PokemonOne> = ArrayList()
    lateinit var viewG : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewG = inflater.inflate(R.layout.fragment_fragprron, container, false)
        FetchPokemonTask().execute()
        return viewG
    }

    private fun portClickListener(item : PokemonOne){

    }

    private fun landClickListener(){

    }

    private fun initRecycler(){
        var adapter = Adapter(listaAll, {pokemon: PokemonOne -> portClickListener(pokemon)})
        viewG.rv_pokemon_list.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(viewG.context)
            this.adapter = adapter
        }
    }

    private inner class FetchPokemonTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg query: String): String {

            var url = NetworkUtils().buildUrl()
            var result = NetworkUtils().getResponseFromHttpUrl(url)
            var gson = Gson()
            var lista = gson.fromJson(result, PokemonAll::class.java)
            listaAll = lista.results
            return ""
        }

        override fun onPostExecute(pokemonInfo: String) {
            initRecycler()
        }
    }


}
