package ir.didehvar.basalam.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.didehvar.basalam.databinding.ActivityMainBinding
import ir.didehvar.basalam.util.Resource
import ir.didehvar.basalam.util.hide
import ir.didehvar.basalam.util.show

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configRecyclerView()

        viewModel.productsLiveData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.loading.hide()
                    it.data?.let { newsResponse ->
                        if (productAdapter.differ.currentList.isEmpty()
                                .and(newsResponse.products.isEmpty())
                        )
                            Snackbar.make(
                                binding.root,
                                "هیچ داده ای یافت نشد.",
                                Snackbar.LENGTH_LONG
                            ).show()
                        else
                            productAdapter.differ.submitList(newsResponse.products)
                    }
                }

                is Resource.Error -> {
                    binding.loading.hide()
                    it.message?.let { message ->
                        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE).show()
                    }
                }

                is Resource.Loading -> {
                    binding.loading.show()
                }
            }
        }
    }

    private fun configRecyclerView() {
        productAdapter = ProductAdapter()
        binding.rvProducts.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        productAdapter.setOnItemClickListener {
            Toast.makeText(
                this,
                "اگر این برنامه کامل بود شما به صفحه محصول ${it.name} منتقل می‌شدید",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}