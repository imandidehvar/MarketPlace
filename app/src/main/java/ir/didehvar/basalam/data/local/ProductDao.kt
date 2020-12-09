package ir.didehvar.basalam.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.didehvar.basalam.domain.model.Product
import ir.didehvar.basalam.domain.model.ProductSearch

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upset(product: Product)
}