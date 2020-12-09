package ir.didehvar.basalam.data.remote

import com.google.gson.JsonObject

class Request {

    companion object {

        val REQUEST_PRODUCTS = JsonObject().let {
            it.addProperty("query", "{productSearch(size: 20) {products {id name photo(size: LARGE) { url } vendor { name } weight price rating { rating count: signals } } } }")
        } as JsonObject

    }

}