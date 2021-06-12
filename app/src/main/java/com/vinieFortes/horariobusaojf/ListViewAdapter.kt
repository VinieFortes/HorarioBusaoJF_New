package com.vinieFortes.horariobusaojf

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vinieFortes.horariobusaojf.horarios.HorariosAzul
import com.vinieFortes.horariobusaojf.horarios.HorariosCiano
import com.vinieFortes.horariobusaojf.horarios.HorariosVermelho
import java.util.*
import kotlin.collections.ArrayList



class ListViewAdapter(
    private var activity: Activity,
    private var modellist: ArrayList<Model>) :
    BaseAdapter() {

    private var arrayList = ArrayList(modellist)

    private class ViewHolder(row: View?) {
        var txtTitulo: TextView? = null
        var imgIcon: ImageView? = null

        init {
            this.txtTitulo = row?.findViewById<TextView>(R.id.linha)
            this.imgIcon = row?.findViewById<ImageView>(R.id.icon)
        }
    }


    override fun getCount(): Int {
        return modellist.size
    }

    override fun getItem(position: Int): Any {
        return modellist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.layout_main, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val model = modellist[position]
        viewHolder.txtTitulo?.text = model.title
        viewHolder.imgIcon?.setImageResource(model.icon)

        view?.setOnClickListener {

            val compair = modellist[position].title.toString()
            val c: Char = compair[0]

            if (c == '1' || c == '2'){
                val linha = modellist[position].title.toString()
                val intent = Intent(activity, HorariosAzul::class.java)
                intent.putExtra("linha", linha)
                activity.startActivity(intent)

            }
            if (c == '3' || c == '4' || c == '5'){
                val linha = modellist[position].title.toString()
                val intent = Intent(activity, HorariosVermelho::class.java)
                intent.putExtra("linha", linha)
                activity.startActivity(intent)

            }
            if (c == '6' || c == '7'){
                val linha = modellist[position].title.toString()
                val intent = Intent(activity, HorariosCiano::class.java)
                intent.putExtra("linha", linha)
                activity.startActivity(intent)
            }
        }

        return view as View
    }


    fun filter(text: String) {

        val text = text.toLowerCase(Locale.getDefault())
        modellist.clear()

        if (text.isEmpty()) {
            modellist.addAll(arrayList)
        } else {
            for (i in 0 until arrayList.size) {
                if (arrayList[i].title.toLowerCase(Locale.getDefault()).contains(text)) {
                    modellist.add(arrayList[i])
                }
            }
        }
        notifyDataSetChanged()
    }

}
