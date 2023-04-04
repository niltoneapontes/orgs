package br.com.niltoneapontes.orgs.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product
import java.math.BigDecimal

class ProductsDao {

    fun add(product: Product) {
        products.add(product)
    }

    fun getAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(
                name="Cesta de frutas",
                description = "Maçã, pera e uvas",
                value = BigDecimal("19.99")
            )
        )
    }
}