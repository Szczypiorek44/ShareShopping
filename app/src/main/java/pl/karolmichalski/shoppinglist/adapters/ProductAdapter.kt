package pl.karolmichalski.shoppinglist.adapters

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.models.Product
import pl.karolmichalski.shoppinglist.viewholders.ProductViewHolder

@BindingAdapter("productList", "onItemClick")
fun RecyclerView.setProducts(productList: List<Product>, productClickCallback: ProductAdapter.ProductClickCallback) {
	if (adapter == null)
		adapter = ProductAdapter(context, productClickCallback)
	(adapter as ProductAdapter).submitList(productList)
}

class ProductAdapter(context: Context,
					 private val productClickCallback: ProductClickCallback)
	: ListAdapter<Product, ProductViewHolder>(ProductDiff()) {

	private val layoutInflater = LayoutInflater.from(context)

	class ProductDiff : DiffUtil.ItemCallback<Product>() {
		override fun areItemsTheSame(oldItem: Product?, newItem: Product?): Boolean {
			return oldItem?.name == newItem?.name
		}

		override fun areContentsTheSame(oldItem: Product?, newItem: Product?): Boolean {
			return oldItem?.name == newItem?.name
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder {
		return ProductViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_product, viewGroup, false), productClickCallback)
	}

	override fun onBindViewHolder(viewHolder: ProductViewHolder, i: Int) {
		viewHolder.bind(getItem(i))
	}

	interface ProductClickCallback {
		fun onProductClick(product: Product)
	}

}