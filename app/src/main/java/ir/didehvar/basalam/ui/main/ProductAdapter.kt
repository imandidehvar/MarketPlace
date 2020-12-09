package ir.didehvar.basalam.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ir.didehvar.basalam.R
import ir.didehvar.basalam.databinding.LayoutProductCardBinding
import ir.didehvar.basalam.domain.model.Product
import java.text.DecimalFormat
import java.text.NumberFormat


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: LayoutProductCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            LayoutProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.binding.apply {
            productTitle.text = product.name
            vendor.text = "غرفه: ${product.vendor.name}"
            rate.text = product.rating.rating.toString()
            ratingCount.text = "(${product.rating.count})"
            weight.text = "${product.weight} گرم"

            val formatter: NumberFormat = DecimalFormat("#,###")
            price.text = "${formatter.format(product.price)} تومان"
            Glide.with(holder.binding.root).load(product.photo.url)
                .placeholder(R.mipmap.image_placeholder).diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.image_notfound).into(photo)
            root.setOnClickListener {
                onItemClickListener?.let { it(product) }
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }

}