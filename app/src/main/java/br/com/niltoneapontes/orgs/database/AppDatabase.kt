package br.com.niltoneapontes.orgs.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.niltoneapontes.orgs.database.converter.Converter
import br.com.niltoneapontes.orgs.ui.recyclerview.adapter.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).allowMainThreadQueries().build()
        }
    }
}