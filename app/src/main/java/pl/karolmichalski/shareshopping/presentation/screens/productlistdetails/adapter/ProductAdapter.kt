package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.models.Product
import pl.karolmichalski.shareshopping.presentation.screens.productlistdetails.viewholders.ProductViewHolder

@BindingAdapter("productList", "onProductClick")
fun RecyclerView.setProductList(productList: List<Product>, onProductClick: (Product) -> Unit) {
	if (adapter == null)
		adapter = ProductAdapter(onProductClick)
	(adapter as ProductAdapter).submitList(productList)
}

class ProductAdapter(private val onProductClick: (Product) -> Unit) : ListAdapter<Product, ProductViewHolder>(DIFF_CALLBACK) {

	private companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
			override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
				return oldItem.name == newItem.name
			}
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder {
		return ProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_product, viewGroup, false), onProductClick)
	}

	override fun onBindViewHolder(viewHolder: ProductViewHolder, i: Int) {
		viewHolder.bind(getItem(i))
	}
}