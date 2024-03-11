package com.aristidevs.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperheroBinding

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail = getretrofig().create(ApiService::class.java).getSuperheroDetail(id)
            if (superheroDetail.body() != null){

                runOnUiThread {
                    createUI(superheroDetail.body()!!)
                    powerstatsUI(superheroDetail.body()!!.powerstats!!)
                }
            }else{

            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperheroName.text = superhero.name
        binding.tvSuperheroRealName.text = superhero.biography.fullname
        binding.tvSuperheroPublisher.text = superhero.biography.publisher
    }

    private fun powerstatsUI(powerstats: SuperHeroPowerstats){
//        val params = binding.viewcombat.layoutParams
//        params.height = powerstats.combat.toInt()
//        binding.viewcombat.layoutParams = params
        updateheight(binding.viewcombat, powerstats.combat)
        updateheight(binding.viewdurability, powerstats.durability)
        updateheight(binding.viewintelligence, powerstats.intelligence)
        updateheight(binding.viewstrength, powerstats.strength)
        updateheight(binding.viewspeed, powerstats.speed)
        updateheight(binding.viewpower, powerstats.power)
    }

    private fun updateheight(view: View, stat: String){
        val params = view.layoutParams
        params.height = pxtodp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxtodp(px: Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()

    }

    private fun getretrofig(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}