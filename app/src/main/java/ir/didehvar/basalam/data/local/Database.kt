package ir.didehvar.basalam.data.local

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverters
import ir.didehvar.basalam.domain.model.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}