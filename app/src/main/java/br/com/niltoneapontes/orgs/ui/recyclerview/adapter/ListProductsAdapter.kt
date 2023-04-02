package br.com.niltoneapontes.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.R
import br.com.niltoneapontes.orgs.databinding.ProductBinding
import java.text.NumberFormat
import java.util.*

class ListProductsAdapter(
    private val context: Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.title)
            val description = itemView.findViewById<TextView>(R.id.description)
            val value = itemView.findViewById<TextView>(R.id.value)

            val currencyInstanceFormatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            val valueString = currencyInstanceFormatter.format(product.value)

            name.text = product.name
            description.text = product.description
            value.text = valueString
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(context),
                parent,
                false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

}
