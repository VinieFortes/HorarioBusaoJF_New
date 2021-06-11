package com.vinieFortes.horariobusaojf

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class Teste : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemazul)


    }
    private fun readJson(){

        var json : String? = null

        try {
            val inputStream: InputStream = assets.open("BusaoJfBd.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(json)

            val jsonobj = jsonObject.getJSONObject("Linhas")
                .getJSONObject("101 - Filgueiras")
                .getJSONObject("SCentro")

            val textView: TextView? = null
            textView?.text = jsonobj.getString("semana")
        }
        catch (e : IOException){

        }

    }
}