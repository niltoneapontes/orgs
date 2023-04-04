package br.com.niltoneapontes.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converter {
    @TypeConverter
    fun fromDouble(value: Double?): BigDecimal {
        return value?.let {
            BigDecimal(value.toString())
        } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): Double? {
        return value?.let {
            value.toDouble()
        }
    }
}