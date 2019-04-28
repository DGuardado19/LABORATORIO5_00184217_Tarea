package com.dguardado19.pokefragme.models

import java.util.ArrayList

data class PokemonAll (
    var count : Int,
    var next : String,
    var previous : String,
    var results : ArrayList<PokemonOne>
)