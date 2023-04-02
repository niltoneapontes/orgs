package br.com.niltoneapontes.orgs.dao

import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product

class ProductsDao {

    fun add(product: Product) {
        products.add(product)
    }

    fun getAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}