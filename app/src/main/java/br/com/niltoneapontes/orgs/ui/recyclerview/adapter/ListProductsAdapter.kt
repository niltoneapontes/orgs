package br.com.niltoneapontes.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.orgs.R

class ListProductsAdapter(
    private val context: Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ListProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.title)
            val description = itemView.findViewById<TextView>(R.id.description)
            val value = itemView.findViewById<TextView>(R.id.value)

            name.text = product.name
            description.text = product.description
            value.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.product, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

}
