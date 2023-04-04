package br.com.niltoneapontes.orgs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.niltoneapontes.orgs.database.dao.AppDatabase
import br.com.niltoneapontes.orgs.databinding.ActivityMainBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.ListProductsAdapter

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
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        recyclerView.adapter = ListProductsAdapter(
            context = this, products = productDao.getAll()
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}