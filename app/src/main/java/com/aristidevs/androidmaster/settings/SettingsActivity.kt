package com.aristidevs.androidmaster.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aristidevs.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val TAG = "Settings Value"
    private var firstTime: Boolean = true

    companion object{
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect{settingsModel->
                //datos SettingsModel()
                runOnUiThread{
                    if (settingsModel != null){
                        binding.switchVibration.isChecked = settingsModel.vibration
                        binding.switchDarkMode.isChecked = settingsModel.darkmode
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            Log.i(TAG, "El valor es: $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }

            binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveChecks(KEY_BLUETOOTH,value)
                }
            }
            binding.switchDarkMode.setOnCheckedChangeListener { _, value ->

                if (value){
                    enabledarkmode()
                }else{
                    disabledarkmode()
                }

                CoroutineScope(Dispatchers.IO).launch {
                    saveChecks(KEY_DARK_MODE,value)
                }
            }
            binding.switchVibration.setOnCheckedChangeListener { _, value ->
                CoroutineScope(Dispatchers.IO).launch {
                    saveChecks(KEY_VIBRATION,value)
                }
            }
        }

    }

    private suspend fun saveVolume(value: Int){
        dataStore.edit{
            it[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveChecks(key:String, value: Boolean){
        dataStore.edit {preferences->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map {preferences ->
            SettingsModel(volume = preferences[intPreferencesKey(VOLUME_LVL)]?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)]?: true,
            darkmode = preferences[booleanPreferencesKey(KEY_DARK_MODE)]?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)]?: true
            )
        }
    }

    private fun enabledarkmode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disabledarkmode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

}