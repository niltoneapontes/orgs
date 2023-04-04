package br.com.niltoneapontes.orgs.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.ProductDetailsActivity
import br.com.niltoneapontes.orgs.R
import br.com.niltoneapontes.orgs.databinding.ProductBinding
import coil.load
import java.text.NumberFormat
import java.util.*

class ListProductsAdapter(
    private val context: Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    class ViewHolder(private val context: Context, private val binding: ProductBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            val name = binding.title
            val description = binding.description
            val value = binding.value

            if (product.image == null) {
                binding.imageView.visibility = View.GONE
            }

            binding.imageView.load(product.image) {
                fallback(R.drawable.baseline_error_outline_24)
                error(R.drawable.baseline_error_outline_24)
            }

            val currencyInstanceFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            val valueString = currencyInstanceFormatter.format(product.value)

            name.text = product.name
            description.text = product.description
            value.text = valueString

            binding.productCard.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra("id", product.id)
                intent.putExtra("name", product.name)
                intent.putExtra("image", product.image.toString())
                intent.putExtra("description", product.description)
                intent.putExtra("value", product.value.toString())

                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(context),
                parent,
                false)

        return ViewHolder(parent.context, binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

}
