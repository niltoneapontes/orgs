package br.com.niltoneapontes.orgs

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.niltoneapontes.orgs.database.dao.AppDatabase
import br.com.niltoneapontes.orgs.databinding.ActivityMainBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.ListProductsAdapter
import kotlinx.coroutines.*

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

        val mainScope = MainScope()
        mainScope.launch {
            withContext(Dispatchers.IO){
                configureListProductsActivity()
            }

        }
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
        val mainScope = MainScope()
        mainScope.launch {
            try {
                throw Exception("Erro ao carregar produtos") // Testando catch
                val products = withContext(Dispatchers.IO) {
                    productDao.getAll()
                }

                withContext(Dispatchers.Main) {
                    recyclerView.adapter = ListProductsAdapter(
                        context = this@MainActivity, products = products
                    )

                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}