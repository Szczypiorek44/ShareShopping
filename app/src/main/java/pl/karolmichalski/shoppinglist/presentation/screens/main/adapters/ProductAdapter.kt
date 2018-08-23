package pl.karolmichalski.shoppinglist.presentation.screens.main.adapters

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.data.models.Product
import pl.karolmichalski.shoppinglist.presentation.screens.main.viewholders.ProductViewHolder

@BindingAdapter("productList", "onItemClick")
fun RecyclerView.setProducts(productList: List<Product>, onProductClick: (Product) -> Unit) {
	if (adapter == null)
		adapter = ProductAdapter(onProductClick)
	(adapter as ProductAdapter).submitList(productList)
}

class ProductAdapter(private val onProductClick: (Product) -> Unit)
	: ListAdapter<Product, ProductViewHolder>(ProductDiff()) {

	class ProductDiff : DiffUtil.ItemCallback<Product>() {
		override fun areItemsTheSame(oldItem: Product?, newItem: Product?): Boolean {
			return oldItem?.key == newItem?.key
		}

		override fun areContentsTheSame(oldItem: Product?, newItem: Product?): Boolean {
			return oldItem?.name == newItem?.name
		}
	}

	private var recyclerView: RecyclerView? = null

	init {
		setHasStableIds(true)
	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		this.recyclerView = recyclerView
	}

	override fun submitList(list: List<Product>) {
		super.submitList(list)
		if (list.size > itemCount)
			recyclerView?.smoothScrollToPosition(list.size)
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder {
		return ProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_product, viewGroup, false), onProductClick)
	}

	override fun onBindViewHolder(viewHolder: ProductViewHolder, i: Int) {
		viewHolder.bind(getItem(i))
	}

	override fun getItemId(position: Int): Long {
		return getItem(position).id.toLong()
	}

}