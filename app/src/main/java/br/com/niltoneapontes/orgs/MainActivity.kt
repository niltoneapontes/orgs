package br.com.niltoneapontes.orgs

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.ListProductsAdapter
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import java.math.BigDecimal

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val name: TextView = findViewById<TextView>(R.id.title)
//        val description: TextView = findViewById<TextView>(R.id.description)
//        val value: TextView = findViewById<TextView>(R.id.value)
//
//        name.text = "Cesta de frutas"
//        description.text = "Laranja, manga e pera"
//        value.text = "R$ 30,00"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = ListProductsAdapter(
            context = this, products = listOf(
                Product(
                    name = "Cesta de frutas",
                    description = "Laranja, manga e uva",
                    value = BigDecimal("19.90")
                ),
                Product(
                    name = "Cesta de frutas 2",
                    description = "Laranja, maçã e uva",
                    value = BigDecimal("19.90")
                ),
                Product(
                    name = "Cesta de frutas 3",
                    description = "Laranja, pera e uva",
                    value = BigDecimal("19.90")
                )
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}