package ir.didehvar.basalam.data.remote

import com.google.gson.JsonObject
import ir.didehvar.basalam.domain.model.Response as ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {

    @POST("api/user")
    suspend fun getProducts(@Body request: JsonObject = Request.REQUEST_PRODUCTS): Response<ResponseData>
}