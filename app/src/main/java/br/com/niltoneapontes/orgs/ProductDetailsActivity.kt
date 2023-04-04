package br.com.niltoneapontes.orgs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.niltoneapontes.orgs.database.dao.AppDatabase
import br.com.niltoneapontes.orgs.databinding.ActivityProductDetailsBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ProductDetailsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private var id: Long = 0L
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes do produto"

        val intent: Intent = intent
        id = intent.getLongExtra("id", 0L)

        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()

        product = productDao.getById(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()

        when(item.itemId) {
            R.id.edit_product -> {
                Intent(this, ProductFormActivity::class.java).apply {
                    putExtra("selected_product", product)
                    startActivity(this)
                }
            }
            R.id.delete_product -> {
                product?.let {
                    productDao.remove(it)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        val intent: Intent = intent
        id = intent.getLongExtra("id", 0L)

        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()

        product = productDao.getById(id)

        val productNameDetail = binding.productNameDetail
        val productDescriptionDetail = binding.productDescriptionDetail
        val productValueDetail = binding.productValueDetail
        val productImageViewDetail = binding.productImageViewDetail

        val locale = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        product?.let {
            productNameDetail.text = it.name
            productDescriptionDetail.text = it.description
            productValueDetail.text = locale.format(it.value)
            productImageViewDetail.load(it.image)
        }
    }
}