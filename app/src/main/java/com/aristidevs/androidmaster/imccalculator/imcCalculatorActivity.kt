package com.aristidevs.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.aristidevs.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class imcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    lateinit private var tvHeight: TextView
    lateinit private var rsHeight: RangeSlider
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubWeight: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubAge: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private var currentWeight: Int = 80
    private var currentAge: Int = 30
    private lateinit var btnCalular: Button
    private var currentHeight: Int = 120

    companion object{
        const val IMC_KEY = "IMC_Result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponents()
        initListener()
        initUI()

    }

    private fun initComponents() {
        viewMale = findViewById(R.id.cardMale)
        viewFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubAge = findViewById(R.id.btnSubAge)
        btnSubWeight = findViewById(R.id.btnSubWeight)
        tvAge = findViewById(R.id.tvAge)
        tvWeight = findViewById(R.id.tvWeight)
        btnCalular = findViewById(R.id.btnCalcular)

    }


    private fun initListener() {
        viewMale.setOnClickListener {
            isMaleSelected = true
            isFemaleSelected = false
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            isFemaleSelected = true
            isMaleSelected = false
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
//            tvHeight.text = value.toString()
        }
        btnPlusAge.setOnClickListener {
            if (currentAge >9 && currentAge <100){
                currentAge +=1
                setAge()
            }
        }
        btnSubAge.setOnClickListener {
            if (currentAge >10 && currentAge <101) {
                currentAge -= 1
                setAge()

            }
        }
        btnPlusWeight.setOnClickListener {
            if (currentWeight < 100 && currentWeight > 9) {
                currentWeight += 1
                setWeight()
            }
        }
        btnSubWeight.setOnClickListener {
            if (currentWeight < 101 && currentWeight > 10) {
                currentWeight -= 1
                setWeight()
            }
        }
        btnCalular.setOnClickListener {
            val result = calulateIMC()
            navigatetoresult(result)
        }

    }

    private fun navigatetoresult(result:Double) {
        val bundle = Bundle()
        bundle.putDouble(IMC_KEY, result)

        val intent = Intent(this, IMCresultActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun calulateIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
        //Log.i("imcdev", "el imc es $result")



    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }


    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int{
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
        setWeight()
        setAge()
    }

}