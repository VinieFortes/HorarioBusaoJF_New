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
import java.util.*


class ListViewAdapter(private var activity: Activity, private var items: ArrayList<Model>) :
    BaseAdapter() {

    private var modelist = java.util.ArrayList(items)


    private class ViewHolder(row: View?) {
        var txtTitulo: TextView? = null
        var imgIcon: ImageView? = null

        init {
            this.txtTitulo = row?.findViewById<TextView>(R.id.linha)
            this.imgIcon = row?.findViewById<ImageView>(R.id.icon)
        }
    }


    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
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
        val model = items[position]
        viewHolder.txtTitulo?.text = model.title
        viewHolder.imgIcon?.setImageResource(model.icon)

        view?.setOnClickListener {

            if (modelist[position].title.equals("100 - Filgueiras")) {
                val intent = Intent(activity, HorariosAzul::class.java)
                val linha = "100 - Filgueiras"
                intent.putExtra("linha", linha)
                activity.startActivity(intent)
            }
        }

        return view as View
    }


    fun filter(texto: String) {
        val texto = texto.toLowerCase(Locale.getDefault())
            items.clear()

        if (texto.isEmpty()) {
            items.addAll(modelist)
            } else {
                for (i in 0 until modelist.size) {
                    if (modelist[i].title!!.toLowerCase(Locale.getDefault()).contains(texto)) {
                        items.add(modelist[i])
                    }
                }
            }
        notifyDataSetChanged()
    }
}
