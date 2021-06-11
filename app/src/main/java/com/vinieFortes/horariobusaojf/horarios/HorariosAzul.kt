package com.vinieFortes.horariobusaojf.horarios


import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.vinieFortes.horariobusaojf.R
import com.vinieFortes.horariobusaojf.itens.HorariosAdapterAzul
import com.vinieFortes.horariobusaojf.itens.HorariosModel
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class HorariosAzul : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: HorariosAdapterAzul
    private lateinit var models: ArrayList<HorariosModel>
    private lateinit var toolbar: Toolbar
    private lateinit var linha: String
    private lateinit var json : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horarios)

        val bundle = intent.extras
        if (bundle != null) {
            linha = bundle.getString("linha").toString()
        }

        try {
            val inputStream: InputStream = assets.open("BusaoJfBd.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(json)

            val jsonobj = jsonObject.getJSONObject("Linhas")
                .getJSONObject(linha)
                .getJSONObject("SCentro")

            val textView: TextView? = null
            textView?.text = jsonobj.getString("semana")
        }
        catch (e : IOException){

        }


        toolbar = findViewById(R.id.toolbarHorarios)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        setSupportActionBar(toolbar)
        supportActionBar?.title = linha
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.azul))
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, R.color.white))

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        tabs.setBackgroundColor(ContextCompat.getColor(this, R.color.azul))
        tabs.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white))
        tabs.tabTextColors = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))

        if (Build.VERSION.SDK_INT >= 21) {
            window.navigationBarColor = (ContextCompat.getColor(this, R.color.azul))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.azul)
        }


        models = ArrayList()
        models.add(HorariosModel("bola", "bola" , "teste 1", "teste 1"))
        models.add(HorariosModel("bola", "bola", "teste 2", "teste 2"))
        models.add(HorariosModel("bola", "bola", "teste 3", "teste 3"))

        adapter = HorariosAdapterAzul(models, this@HorariosAzul)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = adapter
        viewPager.setPadding(5, 0, 5, 0)


        tabs.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {}
        })

    }

}