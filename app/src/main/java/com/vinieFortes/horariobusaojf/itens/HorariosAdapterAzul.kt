package com.vinieFortes.horariobusaojf.itens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager.widget.PagerAdapter
import com.vinieFortes.horariobusaojf.R

class HorariosAdapterAzul(private var models: List<HorariosModel>, private var context: Context) : PagerAdapter() {


    private lateinit var layoutInflater: LayoutInflater

    @StringRes
    private var TAB_TITLES =
        intArrayOf(R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3)

    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(R.layout.itemazul, container, false)

        val sbairro: TextView = view.findViewById(R.id.sbairro)
        val horariobairro: TextView = view.findViewById(R.id.horariobairro)
        val scentro: TextView = view.findViewById(R.id.scentro)
        val horaiocentro: TextView = view.findViewById(R.id.horariocentro)

        sbairro.text = models[position].sbairo
        horariobairro.text = models[position].hbairro
        scentro.text = models[position].scentro
        horaiocentro.text = models[position].hcentro

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }
}