package ir.didehvar.basalam.data.local

import androidx.room.TypeConverter
import ir.didehvar.basalam.domain.model.Product

class Converters {

    @TypeConverter
    fun fromPhoto(photo: Product.Photo): String {
        return photo.url
    }

    @TypeConverter
    fun toPhoto(url: String): Product.Photo {
        return Product.Photo(url)
    }

    @TypeConverter
    fun fromVendor(vendor: Product.Vendor): String {
        return vendor.name
    }

    @TypeConverter
    fun toVendor(vendor: String): Product.Vendor {
        return Product.Vendor(vendor)
    }

    @TypeConverter
    fun fromRating(rating: Product.Rating): String {
        return "${rating.rating},${rating.count}"
    }

    @TypeConverter
    fun toRating(rating: String): Product.Rating {
        var rating = rating.split(",")
        return Product.Rating(rating[0].toFloat(), rating[1].toInt())
    }
}