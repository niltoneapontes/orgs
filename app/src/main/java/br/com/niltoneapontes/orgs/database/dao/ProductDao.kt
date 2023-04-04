package br.com.niltoneapontes.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM Product")
    abstract fun getAll(): List<Product>

    @Insert
    abstract fun add(product: Product)
}