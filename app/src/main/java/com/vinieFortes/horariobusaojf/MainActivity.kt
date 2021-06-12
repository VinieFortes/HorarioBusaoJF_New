package com.vinieFortes.horariobusaojf

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import java.util.*

private lateinit var linhas: Array<String>
private lateinit var icon: IntArray
@SuppressLint("StaticFieldLeak")
private lateinit var lv: ListView
@SuppressLint("StaticFieldLeak")
private lateinit var adapter: ListViewAdapter
private var arrayList: ArrayList<Model> = ArrayList<Model>()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList.clear()


        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Horário Busão JF"



        linhas = arrayOf(

            //Linhas 100
            "100 - Filgueiras",
            "101 - Grama",
            "102 - Grama Via Garganta do Dilermando",
            "103 - Filgueiras",
            "104 - Granjas Betânia / Santa Luzia",
            "105 - Filgueiras",
            "106 - Parque Independência / Jardim Gaúcho",
            "107 - Vila Montanhesa",
            "108 - Granjas Betânia / Santa Luzia",
            "109 - Granjas Triunfo",
            "110 - Recanto dos Lagos",
            "111 - Mundo Novo / Santa Terezinha",
            "112  - Mundo Novo / Santa Terezinha",
            "113 - Vila Montanhesa / Jardim Gaúcho",
            "114 - Sagrado Coração de Jesus / Granjas Betânia",
            "115 - Mundo Novo / Quintas da Avenida",
            "116 - Vivendas da Serra / Via Garganta do Dilermando",
            "117 - Vivendas da Serra / Via Garganta do Dilermando",
            "118 - Parque Independência / Cidade Nova / Vale Verde",
            "119 - Nossa Senhora das Graças / Santa Luzia",
            "120 - Vista Alegre / Santa Luzia",
            "121 - Nossa Senhora das Graças / Santa Luzia",
            "122 - Eldorado / Jardim de Alá",
            "123 - Alto Eldorado / Santa Luzia",
            "124 - Eldorado / Alto Jardim de Alá",
            "125 - Eldorado / Jardim América",
            "126 - Eldorado / Jardim América",
            "129 - Previdenciários",
            "130 - Bandeirantes / Via Garganta do Dilermando / Alto São Geraldo",
            "131 - Bandeirantes / São Geraldo",
            "132 - Bandeirantes / Ipiranga",
            "133 - Bandeirantes / Ipiranga / Arco-irís",
            "134 - Bandeirantes / Santa Efigênia",
            "135 - Parque Guarani / Sagrado Coração de Jesus",
            "136 - Bandeirantes / Via Garganta do Dilermando / Santa Efigênia",
            "137 - Sagrado Coração de Jesus / Manoel Honório",
            "140 - Santa Efigênia / Manoel Honório",
            "141 - Bandeirantes / Bela Aurora",
            "142 - Bandeirantes / Bela Aurora",
            "143 - Bela Aurora / Centro",
            "145  - Centenário / Santa Efigênia",
            "150 - Grama",
            "155 - Jardim Emaús",
            "190 - Zona Sul / UFJF / CAS",

            //Linhas 200
            "201 - Progresso",
            "202 - Progresso",
            "203 - Marumbi",
            "204 - Santa Paula",
            "205 - Santa Paula",
            "206 - Borborema",
            "207 - Marumbi",
            "208 - Progresso",
            "209 - Marumbi",
            "210 - Santa Paula",
            "211 - Rio Branco",
            "213 - Bairu / Cruzeiro do Sul",
            "214 - Bairu / Cruzeiro do Sul",
            "215 - Bairu / Cruzeiro do Sul",
            "216 - Bairu / Boa Vista",
            "218 - Graminha",
            "220 - Bom Pastor",
            "221 - Bom Pastor / Santa Catarina",
            "222 - Bom Pastor / Santa Catarina",
            "226 - Guaruá / Bairu",
            "230 - Santa Paula",
            "231 - Progresso",
            "232 - Bonfim / Via Av. Brasil",
            "233 - Marumbi",
            "239 - Progresso / Av. Rio Branco",
            "249 - Marumbi / Av. Rio Branco",
            "259 - Santa Paula / Av. Rio Branco",
            "299 - Alto Bairu / Cruzeiro do Sul",

            //Linhas 300
            "301 - Usina Quatro",
            "302 - Floresta",
            "303 - Jardim Esperança",
            "304 - Caeté",
            "305 - Jardim Esperança",
            "306 - Retiro",
            "307 - Retiro / Floresta",
            "308 - Retiro",
            "309 - Terras Altas",
            "311 - Santa Tereza",
            "313 - Sarandira",
            "315 - Jardim Esperança",
            "321 - Vila Ozanan",
            "322 - Furtado de Menezes",
            "325 - Solidariedade",
            "326 - Solidariedade / Via Vila Ideal",
            "331 - Vila Ideal",
            "332 - Vila Ideal",
            "333 - Granjas Primavera",
            "335 - Granjas Bethel",
            "399 - Olavo Costa",

            //Linhas 400
            "400 - Alto Grajaú",
            "402 - Alto Grajaú",
            "403 - Nossa Senhora Aparecida",
            "404 - Santa Rita",
            "405 - Santa Rita / Alto Três Moinhos",
            "406 - Santa Rita / Sr Nenêm",
            "407 - Nossa Senhora Aparecida",
            "411 - Vitorino Braga",
            "412 - Parque Burnier",
            "413 - Bom Jardim",
            "415 - Linhares / Via Vale dos Peões",
            "416 - Linhares",
            "420 - Linhares",
            "422 - Santa Cândida",
            "423 - Bom Jardim",
            "424 - Aracy",
            "425 - Linhares",
            "426 - Grajaú",
            "427 - Linhares",
            "428 - Linhares / Fazenda Yung",
            "429 - Linhares",
            "430 - São Sebastião / Via Bonsucesso",
            "431 - São Benedito",
            "432 - São Benedito",
            "433 - Vila Alpina",
            "434 - Vila Alpina",
            "435 - Linhares",
            "436 - Linhares",
            "437 - São Bernardo",
            "438 - Parque Serra Verde",
            "439 - Santo Antônio",
            "440 - Santo Antônio",
            "441 - Santo Antônio",
            "443 - Nossa Senhora de Lourdes",
            "444 - Nossa Senhora de Lourdes",
            "445 - Nossa Senhora de Lourdes",
            "447 - Nossa Senhora de Lourdes",
            "499 - Linhares / Santo Antônio",

            //Linhas 500
            "501 - Padre Café / Jardim Glória",
            "503 - Paineras",
            "505 - Morro do Cristo",
            "508 - Avenida Deusdedith Salgado",
            "509 - Alto Dom Bosco / Laranjeiras",
            "510 - Dom Bosco",
            "511 - Dom Bosco / Borboleta",
            "512 - Dom Bosco / Borboleta",
            "514 - Cascatinha / Borboleta",
            "515 - Dom Orione",
            "516 - São Pedro / Via Borboleta",
            "518 - Salvaterra",
            "519 - Torreões",
            "520 - Aeroporto",
            "521 - Teixeiras",
            "522 - Teixeiras",
            "523 - Monte Verde",
            "524 - São Mateus",
            "525 - Universidade (UFJF)",
            "526 - Cascatinha / Vale do Ipê",
            "527 - Santa Cecília",
            "528 - Cascatinha / Borboleta",
            "529 - Torreões / Via Mascate / Monte Verde",
            "530 - São Pedro",
            "531 - Nova Califórnia",
            "532 - São Pedro / Via Caiçaras",
            "533 - São Pedro",
            "534 - Santos Dumont",
            "535 - Universidade (UFJF)",
            "537 - Jardim da Serra",
            "538 - Morada do Serro / Via Jardim Casa Blanca / Adolpho Vireque",
            "539 - Santos Dumont",
            "540 - São Pedro",
            "541 - São Pedro",
            "542 - Lagoa",
            "543 - Santa Córdula",
            "544 - Recanto dos Brugger",
            "545 - Universidade (UFJF)",
            "546 - Mirante",
            "547 - Nossa Senhora de Fátima",
            "548 - Adolpho Vireque / Via Jardim Casa Blanca / Morada do Serro",
            "549 - Nova Germânia",
            "555 - Universidade (UFJF)",
            "560 - Av. Presidente Itamar Franco / Via Shopping",
            "590 - Zona Sul / UFJF / CAS",
            "599 - São Pedro",

            //Linhas 600
            "600 - Monte Castelo / Via Jardim São João",
            "601 - Jardim Cachoeira / Via Monte Castelo",
            "602 - Monte Castelo",
            "603 - Carlos Chagas",
            "604 - Bairro Industrial",
            "605 - Milho Branco",
            "606 - Jardim Natal",
            "607 - Jardim Natal",
            "608 - Milho Branco",
            "609 - Milho Branco",
            "610 - Amazônia",
            "611 - Esplanada / Granbery",
            "612 - Esplanada / Granbery",
            "613 - Francisco Bernardino",
            "614 - Jardim Natal",
            "615 - Encosta do Sol",
            "616 - Jardim Natal",
            "620 - Francisco Bernardino / Via Fontes Ville",
            "621 - Democrata",
            "626 - Jardim Natal / Av. Rio Branco",
            "630 - Rodoviária",
            "636 - Jardim Cachoeira / Av. Rio Branco",
            "640 - Rodoviária",
            "646 - Milho Branco / Av. Rio Branco",

            //Linhas 700
            "700 - Barbosa Lage",
            "701 - Jóquei Clube",
            "702 - Jóquei Clube",
            "703 - Barbosa Lage / Santa Amélia",
            "704 - Jóquei Clube III",
            "705 - Jóquei Clube III",
            "706 - Cidade do Sol",
            "707 - Cidade do Sol",
            "708 - Vale dos Lírios",
            "709 - Jóquei Clube",
            "710 - Novo Triunfo",
            "711 - Barreira",
            "712 - Dias Tavares",
            "713 - Náutico",
            "714 - Chapéu D Uvas",
            "715 - Vila Esperança I",
            "716 - Distrito Industrial",
            "717 - Benfica",
            "718 - Ponte Preta",
            "719 - Nova Era / Centro",
            "720 - Santa Lúcia",
            "721 - Nova Era",
            "722 - Santa Cruz",
            "723 - São Judas Tadeu",
            "724 - Santa Lúcia / Distrito Industrial (Circular)",
            "725 - São Judas Tadeu",
            "726 - Igrejinha",
            "727 - Bairro Araújo",
            "728 - Nova Benfica",
            "729 - Paula Lima",
            "730 - Santa Lúcia",
            "731 - BR 040",
            "732 - Santa Cruz",
            "733 - Santa Cruz / Via São Damião",
            "734 - Cidade do Sol / Santa Maria",
            "735 - Vila Esperança II",
            "736 - Nova Benfica",
            "737 - Jóquei Clube",
            "738 - Pedra Branca",
            "739 - Nova Era",
            "740 - Humaitá",
            "741 - Valadares",
            "742 - Rosário de Minas",
            "743 - Toledos",
            "744 - Palmital",
            "745 - Rosário de Minas",
            "746 - Circular BR 267",
            "747 - São Judas Tadeu",
            "748 - Alto Santa Cruz",
            "749 - São Judas Tadeu",
            "751 - Santa Cruz / Via Jardim Alfineiros / Santa Clara",
            "752 - Penido",
            "753 - Miguel Marinho",
            "754 - CIRCULAR BENFICA / SENAI-VIA BR 040",
            "755 - Zona Norte / UFJF",
            "757 - Benfica / Jardim Bom Jesus",
            "758 - Novo Triunfo II",
            "760 - Humaitá",
            "766 - Zona Norte / Av. Rio Branco",
            "767 - Benfica / Bela Vista",
            "775 - Santa Cruz / Via Av. Brasil",
            "785 - Benfica / Via Av. Brasil"
        )

        icon = intArrayOf(R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,R.mipmap.busazul,
                          R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,R.mipmap.busvermelho,
                          R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano,R.mipmap.busciano
        )


        lv= findViewById(R.id.listview)
        for (i in linhas.indices) {
            val model = Model(linhas[i], icon[i])
            arrayList.add(model)
        }

        adapter = ListViewAdapter(this, arrayList)

        lv.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.searchView)
        val fav = menu.findItem(R.id.fav)
        val theme = menu.findItem(R.id.theme)
        val searchView = searchItem.actionView as SearchView

        fav.setOnMenuItemClickListener {
            val intent = Intent(this, Teste::class.java)
            startActivity(intent)
            true
        }

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            theme.setIcon(R.drawable.ic_baseline_nights_stay_24_black)
            theme.setOnMenuItemClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
        }else {
            theme.setOnMenuItemClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
        }

        searchView.queryHint = "Pesquise sua linha"
        searchView.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter(newText)
                return false
            }


            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        }

        )


        return true
    }


}


