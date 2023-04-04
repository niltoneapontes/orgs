package br.com.niltoneapontes.orgs.ui.recyclerview.adapter

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val description: String,
    val value: BigDecimal,
    val image: String? = null,
)
