package com.aristidevs.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aristidevs.androidmaster.R

class ResultActivity : AppCompatActivity() {
    lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvResultado = findViewById(R.id.tvResultado)

        val nombre: String = intent.extras?.getString("nombre").orEmpty()
        if (nombre != null) {
            tvResultado.text = "Hola ${nombre} gracias por utilizar la aplicacion"
        }else{
            tvResultado.text = "No se econtro nombre"
        }


    }
}