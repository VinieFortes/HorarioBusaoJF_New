package com.vinieFortes.horariobusaojf.fav

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vinieFortes.horariobusaojf.R
import com.vinieFortes.horariobusaojf.horarios.HorariosAzul
import com.vinieFortes.horariobusaojf.horarios.HorariosCiano
import com.vinieFortes.horariobusaojf.horarios.HorariosVermelho


@SuppressLint("StaticFieldLeak")
lateinit var rv: RecyclerView
var arrayList_fav = java.util.ArrayList<String>()
@SuppressLint("StaticFieldLeak")
lateinit var adapter: MyRecyclerViewAdapter


class Favoritos : AppCompatActivity(), MyRecyclerViewAdapter.ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        loadData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)


        val toolbar = findViewById<Toolbar>(R.id.toolbarFav)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Favoritos"

        rv = findViewById(R.id.listviewFav)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter(this, arrayList_fav)
        adapter.setClickListener(this)
        rv.adapter = adapter


    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<java.util.ArrayList<String>?>() {}.type
        arrayList_fav = gson.fromJson(json, type)
        if (arrayList_fav == null) {
            arrayList_fav = ArrayList();
        }

    }

    override fun onItemClick(view: View?, position: Int) {

        val compair = arrayList_fav[position].toString()
        val c: Char = compair[0]

        if (c == '1' || c == '2'){
            val linha = arrayList_fav[position].toString()
            val intent = Intent(this@Favoritos, HorariosAzul::class.java)
            intent.putExtra("linha", linha)
            this@Favoritos.startActivity(intent)

        }
        if (c == '3' || c == '4' || c == '5'){
            val linha = arrayList_fav[position].toString()
            val intent = Intent(this@Favoritos, HorariosVermelho::class.java)
            intent.putExtra("linha", linha)
            this@Favoritos.startActivity(intent)

        }
        if (c == '6' || c == '7'){
            val linha = arrayList_fav[position].toString()
            val intent = Intent(this@Favoritos, HorariosCiano::class.java)
            intent.putExtra("linha", linha)
            this@Favoritos.startActivity(intent)
        }
    }

}