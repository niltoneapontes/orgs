package br.com.niltoneapontes.orgs

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.niltoneapontes.orgs.dao.ProductsDao
import br.com.niltoneapontes.orgs.databinding.ActivityProductFormBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this as AppCompatActivity).supportActionBar!!.title = "Novo Produto"
        configureButtonAction()
        setContentView(binding.root)
    }

    private fun configureButtonAction() {
        val buttonInput = binding.buttonSave
        val productsDao = ProductsDao()

        buttonInput.setOnClickListener {
            val product = createProduct()
            productsDao.add(product)
            Log.i("Products DAO: ", productsDao.getAll().toString())
            Toast.makeText(this, "Produto adicionado", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun createProduct(): Product {
        val nameInput = binding.nameInput
        val name = nameInput.text.toString()

        val descriptionInput =
            binding.descriptionInput
        val description = descriptionInput.text.toString()

        val valueInput = binding.valueInput
        val valueString = valueInput.text.toString()

        val value = if (valueString.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueString)
        }

        val product = Product(
            name = name,
            description = description,
            value = value
        )

        Log.i("Product: ", product.toString())

        return product
    }
}