package ir.didehvar.basalam.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Int,
    val name: String,
    val photo: Photo, // Should Have TypeConverter
    val vendor: Vendor, // Should Have TypeConverter
    val weight: Int,
    val price: Int,
    val rating: Rating // Should Have TypeConverter
){
    data class Photo(
        val url: String
    )
    data class Vendor(
        val name: String
    )
    data class Rating(
        val rating: Float,
        val count: Int
    )
}
