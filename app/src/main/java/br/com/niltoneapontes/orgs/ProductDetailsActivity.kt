package br.com.niltoneapontes.orgs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewbinding.ViewBinding
import br.com.niltoneapontes.orgs.databinding.ActivityMainBinding
import br.com.niltoneapontes.orgs.databinding.ActivityProductDetailsBinding
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ProductDetailsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var image: String
    private lateinit var value: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes do produto"

        val intent: Intent = intent
        name = intent.getStringExtra("name").toString()
        description = intent.getStringExtra("description").toString()
        image = intent.getStringExtra("image").toString()
        value = intent.getStringExtra("value").toString()
    }

    override fun onResume() {
        super.onResume()

        Log.d("VALUES", name + description + value)

        val productNameDetail = binding.productNameDetail
        val productDescriptionDetail = binding.productDescriptionDetail
        val productValueDetail = binding.productValueDetail
        val productImageViewDetail = binding.productImageViewDetail

        val locale = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        productNameDetail.text = name
        productDescriptionDetail.text = description
        productValueDetail.text = locale.format(BigDecimal(value))
        productImageViewDetail.load(image)
    }
}