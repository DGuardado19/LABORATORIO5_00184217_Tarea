package com.dguardado19.pokefragme


import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dguardado19.pokefragme.utilities.NetworkUtils
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_fragperron2.view.*
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragperron2 : Fragment() {
    var url = ""
    var name = ""
    lateinit var viewG : View

    companion object {
        fun newIntance(url: String, name: String) : Fragperron2{
            var instance = Fragperron2()
            instance.url = url
            instance.name = name
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewG = inflater.inflate(R.layout.fragment_fragperron2, container, false)
        viewG.tv_name.text = "Name: ${name}"

        if (url != "") FetchPokemonTask().execute()

        return viewG
    }

    inner class FetchPokemonTask : AsyncTask<Void, Void, String>(){
        var peso = 0.0
        var alto = 0; var base = 0; var cantTipo = ""; var image = ""
        override fun doInBackground(vararg params: Void?): String {
            var url = NetworkUtils().getUrl(url)
            var pokeArray: JsonArray
            var pokeParse = JsonParser()
            var pokeOb: JsonObject
            try {
                var result = NetworkUtils().getResponseFromHttpUrl(url)
                result = "[$result]"
                pokeArray = pokeParse.parse(result).asJsonArray
                var pokeElement = pokeArray.get(0)
                pokeOb = pokeElement.asJsonObject
                peso = (pokeOb.get("weight").asDouble/10)
                alto = (pokeOb.get("height").asInt*10)
                base = (pokeOb.get("base_experience").asInt)

                var type = pokeOb.get("types").asJsonArray
                for (i in 0 .. (type.size()-1)){
                    var typeOb : JsonObject; var typeObjI : JsonObject
                    var typeIS : String; var pokePa = JsonParser();

                    typeIS = type.get(i).toString()
                    typeOb = pokePa.parse(typeIS).asJsonObject
                    typeObjI = typeOb.get("type").asJsonObject

                    if (pokeOb.get("sprites").asJsonObject.get("front_default").asString != null){
                        image = pokeOb.get("sprites").asJsonObject.get("front_default").asString
                    } else{
                        image = pokeOb.get("sprites").asJsonObject.get("front_female").asString
                    }

                    if (type.size() == i + 1) {
                        cantTipo += typeObjI.get("name").asString
                    } else {
                        cantTipo += typeObjI.get("name").asString + "/"
                    }
                }

            } catch (e : IOException){
                e.printStackTrace()
            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            viewG.tv_tipo.text = "Type: $cantTipo"
            viewG.tv_base.text = "Experience: $base"
            viewG.tv_height.text = "Height: $alto cm"
            viewG.tv_weight.text = "Weight: $peso Kg"
            Glide.with(viewG.context).load(image).into(viewG.iv_pokeimage)
        }
    }
}
