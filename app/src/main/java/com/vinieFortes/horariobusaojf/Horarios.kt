package com.vinieFortes.horariobusaojf


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.vinieFortes.horariobusaojf.itens.azul.HorariosAdapter
import com.vinieFortes.horariobusaojf.itens.azul.HorariosModel
import java.util.ArrayList

class Horarios : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: HorariosAdapter
    private lateinit var models: List<HorariosModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.horarios)

        models = ArrayList()
        models = listOf(HorariosModel("Saida bairro","Saida centro", "Bola", "teste"))
        models = listOf(HorariosModel("Saida bairro","Saida centro", "Bola", "teste"))
        models = listOf(HorariosModel("Saida bairro","Saida centro", "Bola", "teste"))

        adapter = HorariosAdapter(models, this@Horarios)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = adapter
        viewPager.setPadding(5, 0, 5, 0)

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

}