package com.vinieFortes.horariobusaojf.horarios


import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.vinieFortes.horariobusaojf.R
import com.vinieFortes.horariobusaojf.Teste
import com.vinieFortes.horariobusaojf.fav.*
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

    private lateinit var sbairroSemana: String
    private lateinit var sbairroSabado: String
    private lateinit var sbairroDomingo: String

    private lateinit var scentroSemana: String
    private lateinit var scentroSabado: String
    private lateinit var scentroDomingo: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horarios)

        val bundle = intent.extras
        if (bundle != null) {
            linha = bundle.getString("linha").toString()
        }

        try {
            val inputStream: InputStream = assets.open("BusaoJfBdAzul.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(json)

            val jsonobj = jsonObject.getJSONObject("Linhas")
                .getJSONObject(linha)

            sbairroSemana = jsonobj.getJSONObject("SBairro").getString("semana")
            sbairroSabado = jsonobj.getJSONObject("SBairro").getString("sabado")
            sbairroDomingo = jsonobj.getJSONObject("SBairro").getString("domingo")
            scentroSemana = jsonobj.getJSONObject("SCentro").getString("semana")
            scentroSabado = jsonobj.getJSONObject("SCentro").getString("sabado")
            scentroDomingo = jsonobj.getJSONObject("SCentro").getString("domingo")
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
        models.add(HorariosModel("Saída Bairro", "Saída Centro" , sbairroSemana, scentroSemana))
        models.add(HorariosModel("Saída Bairro", "Saída Centro", sbairroSabado, scentroSabado))
        models.add(HorariosModel("Saída Bairro", "Saída Centro", sbairroDomingo, scentroDomingo))

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_horarios, menu)
        val prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        val favNO = menu.findItem(R.id.favNO)
        val favSI = menu.findItem(R.id.favSI)
        val inti = menu.findItem(R.id.inti)


        favNO.isVisible = prefs.getBoolean("favoritado $linha", true)
        favSI.isVisible = prefs.getBoolean("Nfavoritado $linha", false)


        favNO.setOnMenuItemClickListener {
            prefs.edit().putBoolean("favoritado $linha", false).apply()
            prefs.edit().putBoolean("Nfavoritado $linha", true).apply()
            favNO.isVisible = false
            favSI.isVisible = true
            arrayList_fav.add(linha)
            saveData()
            Toast.makeText(this, "$linha foi adicionada aos favoritos !", Toast.LENGTH_SHORT).show()
            true
        }

        favSI.setOnMenuItemClickListener {
            prefs.edit().putBoolean("favoritado $linha", true).apply()
            prefs.edit().putBoolean("Nfavoritado $linha", false).apply()
            favNO.isVisible = true
            favSI.isVisible = false
            arrayList_fav.remove(linha)
            saveData()
            Toast.makeText(this, "$linha foi removido dos favoritos !", Toast.LENGTH_SHORT).show()
            true
        }

        inti.setOnMenuItemClickListener {
            val intent = Intent(this, Teste::class.java)
            startActivity(intent)
            true
        }

        return true
    }
    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(arrayList_fav)
        editor.putString("task list", json)
        editor.apply()
    }
}