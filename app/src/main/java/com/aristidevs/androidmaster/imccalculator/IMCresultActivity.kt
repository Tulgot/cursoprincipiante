package com.aristidevs.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.imccalculator.imcCalculatorActivity.Companion.IMC_KEY

class IMCresultActivity : AppCompatActivity() {

    private lateinit var tvIMC: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var tvStatus: TextView
    private lateinit var tvImcStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresult)

        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0


        initComponents()
        initListener()
        initUI(result)

    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 1.0 .. 18.5 -> {
                tvStatus.text = getString(R.string.lowweight)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.low))
                tvImcStatus. text = getString(R.string.lowweightdesc)
            }
            in 18.5 .. 24.9 -> {
                tvStatus.text = getString(R.string.normalweight)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.normal))
                tvImcStatus.text = getString(R.string.normalweightdesc)
            }
            in 25.0 .. 29.9 -> {
                tvStatus.text = getString(R.string.overweight)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.over))
                tvImcStatus.text = getString(R.string.normalweightdesc)
            }
            in 30.0 .. 34.9 -> {
                tvStatus.text = getString(R.string.obeseI)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.obeseI))
                tvImcStatus.text = getString(R.string.obeseIdesc)

            }
            in 35.0 .. 39.9 -> {
                tvStatus.text = getString(R.string.obeseII)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.obeseI))
                tvImcStatus.text = getString(R.string.obeseIdesc)
            }
            in 40.0 .. 60.0 -> {
                tvStatus.text = getString(R.string.obeseIII)
                tvStatus.setTextColor(ContextCompat.getColor(this, R.color.obeseI))
                tvImcStatus.text = getString(R.string.obeseIdesc)
            }else -> {
            tvStatus.text = "Error"
            tvImcStatus.text = "Error"
        }
        }
    }

    private fun initListener() {
        btnRecalculate.setOnClickListener {
            //finish()
            onBackPressedDispatcher.onBackPressed()
        }




    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        tvStatus = findViewById(R.id.tvStatus)
        tvImcStatus = findViewById(R.id.tvImcStatus)
    }


}