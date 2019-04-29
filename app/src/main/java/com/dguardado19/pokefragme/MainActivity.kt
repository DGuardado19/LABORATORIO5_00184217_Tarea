package com.dguardado19.pokefragme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.mainprincipal, Fragprron()).commit()
        if(fragment_secundario != null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_secundario, Fragperron2()).commit()
        }
    }
}
