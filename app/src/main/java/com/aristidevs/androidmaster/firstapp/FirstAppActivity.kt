package com.aristidevs.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aristidevs.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    lateinit var etNombre: EditText
    lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        etNombre = findViewById(R.id.etNombre)
        btnEnviar = findViewById(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val nombre = etNombre.text.toString()

            if (nombre.isNotEmpty()){
                val bundle = Bundle()
                bundle.putString("nombre", nombre)

                val intent = Intent(this, ResultActivity::class.java )
//                intent.putExtra("EXTRA_NAME", nombre)
                intent.putExtras(bundle)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Escribe tu nombre", Toast.LENGTH_SHORT).show()
            }

        }


    }
}