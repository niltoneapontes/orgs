package br.com.niltoneapontes.orgs

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.ListProductsAdapter

class MainActivity: Activity() {
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

        recyclerView.adapter = ListProductsAdapter()
    }
}