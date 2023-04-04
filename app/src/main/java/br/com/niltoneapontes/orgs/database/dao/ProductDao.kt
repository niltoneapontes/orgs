package br.com.niltoneapontes.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM Product")
    abstract fun getAll(): List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    abstract fun getById(id: Long): Product?

    @Insert
    abstract fun add(product: Product)

    @Delete
    abstract fun remove(product: Product)

    @Update
    abstract fun update(product: Product)
}