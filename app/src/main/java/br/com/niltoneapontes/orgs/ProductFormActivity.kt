package br.com.niltoneapontes.orgs

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.niltoneapontes.orgs.database.dao.AppDatabase
import br.com.niltoneapontes.orgs.databinding.ActivityProductFormBinding
import br.com.niltoneapontes.orgs.databinding.FormImageBinding
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import coil.load
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {
    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var productId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Adicionar Produto"
        configureButtonAction()
        setContentView(binding.root)

        val bindingImageForm = FormImageBinding.inflate(layoutInflater)

        bindingImageForm.reloadButton.setOnClickListener {
            url = bindingImageForm.urlImageInput.text.toString()
            bindingImageForm.imageFormImageView.load(url) {
                fallback(R.drawable.baseline_error_outline_24)
                error(R.drawable.baseline_error_outline_24)
            }
        }

        binding.formImageView.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(bindingImageForm.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bindingImageForm.urlImageInput.text.toString()
                    binding.formImageView.load(url) {
                        fallback(R.drawable.baseline_error_outline_24)
                        error(R.drawable.baseline_error_outline_24)
                    }
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()

        }

        intent.getParcelableExtra<Product>("selected_product")?.let {
            title = "Editar produto"
            productId = it.id
            url = it.image
            binding.formImageView.load(it.image)
            binding.nameInput.setText(it.name)
            binding.descriptionInput.setText(it.description)
            binding.valueInput.setText(it.value.toPlainString())

        }
    }

    private fun configureButtonAction() {
        val buttonInput = binding.buttonSave
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()

        buttonInput.setOnClickListener {
            val product = createProduct()

            if (productId > 0) {
                productDao.update(product)
                Toast.makeText(this, "Produto editado", Toast.LENGTH_LONG).show()
            } else {
                productDao.add(product)
                Toast.makeText(this, "Produto adicionado", Toast.LENGTH_LONG).show()
            }
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
            id = productId,
            name = name,
            description = description,
            value = value,
            image = url
        )

        Log.i("Product: ", product.toString())

        return product
    }
}