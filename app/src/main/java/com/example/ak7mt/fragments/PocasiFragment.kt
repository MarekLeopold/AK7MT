package com.example.ak7mt.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ak7mt.R
import kotlinx.android.synthetic.main.fragment_pocasi.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class PocasiFragment : Fragment() {
    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"


    var CITY: String ? = "praha,cz"
    val API: String = "05f0c276cb9a2294e44993b69ebfcd4f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weatherTask().execute()
    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            //errortext.visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                loadData()
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&lang=cz&units=metric&appid=$API").readText()
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updateAtText = "Aktualizováno: " + SimpleDateFormat("dd/MM/yyy HH:mm", Locale.ENGLISH).format(Date(updatedAt * 1000))
                val temp = main.getString("temp") + "°C"
                val tempMin = "Minimum: " + main.getString("temp_min") + "°C"
                val tempMax = "Maximum: " + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val adresa = jsonObj.getString("name") + ", " + sys.getString("country")

                adress.text = adresa
                updated_at.text = updateAtText
                predpoved.text = weatherDescription.capitalize()
                maintemp.text = temp
                temp_min.text = tempMin
                temp_max.text = tempMax
                vychod.text = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(Date(sunrise))
                zapad.text = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(Date(sunset))
                vitr.text = windSpeed
                tlak.text = pressure
                vlhkost.text = humidity

            } catch (e: Exception) {
                //errortext.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pocasi, container, false)
    }

    fun loadData() {
        val sharedPreferences =
            this.activity?.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
            CITY = sharedPreferences?.getString(TEXT, "zlin,cz")
    }
}