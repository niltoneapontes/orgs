package br.com.niltoneapontes.orgs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.dao.ProductsDao
import br.com.niltoneapontes.orgs.databinding.ActivityMainBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.ListProductsAdapter
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        configureListProductsActivity()
        configureFloatActionButton()
    }

    private fun configureFloatActionButton() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureListProductsActivity() {
        val recyclerView = binding.recyclerView
        val productsDao = ProductsDao()
        recyclerView.adapter = ListProductsAdapter(
            context = this, products = productsDao.getAll()
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}