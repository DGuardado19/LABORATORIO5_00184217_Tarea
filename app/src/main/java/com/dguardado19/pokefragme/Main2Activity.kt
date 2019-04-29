package com.dguardado19.pokefragme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Main2Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var intent = intent
        var instance = Fragperron2.newIntance(intent.getStringExtra("Url"), intent.getStringExtra("Name"))
        supportFragmentManager.beginTransaction().replace(R.id.fragment_secundario, instance).commit()
    }
}