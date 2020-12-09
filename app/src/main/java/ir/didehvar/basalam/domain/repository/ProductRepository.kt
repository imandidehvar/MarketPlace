package ir.didehvar.basalam.domain.repository

import android.util.Log
import com.google.gson.JsonObject
import ir.didehvar.basalam.data.local.Database
import ir.didehvar.basalam.data.remote.API
import ir.didehvar.basalam.domain.model.ProductSearch
import ir.didehvar.basalam.util.Resource
import ir.didehvar.basalam.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import ir.didehvar.basalam.domain.model.Response as BasalamResponse
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class ProductRepository @Inject constructor(
    private val database: Database,
    private val api: API
) {
    suspend fun getProducts(): Resource<ProductSearch> {
        try {
            val remoteData = getProductsRemote()
            if (remoteData.isSuccessful) {
                saveCache(remoteData.body()!!.data.productSearch)
            } else
                return Resource.Error(remoteData.message())
        } catch (e: UnknownHostException){
            return Resource.Success(ProductSearch(database.getProductDao().getProducts()))
        }

        return Resource.Success(ProductSearch(database.getProductDao().getProducts()))
    }

    private suspend fun getProductsRemote(): Response<BasalamResponse> {
        val a = JsonObject()
        a.addProperty(
            "query",
            "{productSearch(size: 20) {products {id name photo(size: LARGE) { url } vendor { name } weight price rating { rating count: signals } } } }"
        )
        return api.getProducts(a)
    }

    private suspend fun saveCache(productSearch: ProductSearch) {
        productSearch.products.forEach {
            database.getProductDao().upset(it)
        }
    }
}