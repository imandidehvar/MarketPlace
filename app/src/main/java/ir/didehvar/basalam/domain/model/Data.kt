package ir.didehvar.basalam.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("productSearch")
    @Expose
    val productSearch: ProductSearch
)