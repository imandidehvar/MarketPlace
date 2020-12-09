package ir.didehvar.basalam.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data")
    @Expose
    val data: Data
)