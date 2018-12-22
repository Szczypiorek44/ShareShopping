package pl.karolmichalski.shareshopping.presentation.screens.productlists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.shareshopping.R
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.presentation.screens.productlists.viewholders.ProductListViewHolder

@BindingAdapter("productLists", "onItemClick", "onItemLongClick")
fun RecyclerView.setProductLists(productLists: List<ProductList>, onItemClick: (ProductList) -> Unit, onItemLongClick: (ProductList) -> Unit) {
	if (adapter == null)
		adapter = ProductListsAdapter(onItemClick, onItemLongClick)
	(adapter as ProductListsAdapter).submitList(productLists)
}

class ProductListsAdapter(private val onItemClick: (ProductList) -> Unit,
						  private val onItemLongClick: (ProductList) -> Unit)
	: ListAdapter<ProductList, ProductListViewHolder>(DIFF_CALLBACK) {

	private companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductList>() {
			override fun areItemsTheSame(oldItem: ProductList, newItem: ProductList): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: ProductList, newItem: ProductList): Boolean {
				return oldItem.name == newItem.name
			}
		}
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductListViewHolder {
		return ProductListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_productlist, viewGroup, false), onItemClick, onItemLongClick)
	}

	override fun onBindViewHolder(viewHolder: ProductListViewHolder, i: Int) {
		viewHolder.bind(getItem(i))
	}
}