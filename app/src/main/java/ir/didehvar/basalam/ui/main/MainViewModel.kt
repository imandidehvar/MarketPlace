package ir.didehvar.basalam.ui.main

import android.app.Application
import android.content.Context
import android.hardware.camera2.params.Capability
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.didehvar.basalam.App
import ir.didehvar.basalam.domain.model.ProductSearch
import ir.didehvar.basalam.domain.repository.ProductRepository
import ir.didehvar.basalam.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import ir.didehvar.basalam.domain.model.Response as BasalamResponse

class MainViewModel @ViewModelInject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    var productsLiveData: MutableLiveData<Resource<ProductSearch>> = MutableLiveData()
        private set

    init {
        getProducts()
    }

    private fun getProducts() = viewModelScope.launch {
        productsLiveData.postValue(Resource.Loading())
        val response = repository.getProducts()
        productsLiveData.postValue(response)
    }

//    private fun checkInternetConnection(): Boolean {
//        val connectivityManager = getApplication<App>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val activeNetwork = connectivityManager.activeNetwork ?: return false
//            val capabilities =  connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
//            return when {
//                capabilities.hasTransport(TRANSPORT_WIFI) -> true
//                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
//                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
//                else -> false
//            }
//        } else {
//            connectivityManager.activeNetworkInfo?.run {
//                return when(type) {
//                    TYPE_WIFI -> true
//                    TYPE_MOBILE -> true
//                    TYPE_ETHERNET -> true
//                    else -> false
//                }
//            }
//        }
//        return false
//    }
}