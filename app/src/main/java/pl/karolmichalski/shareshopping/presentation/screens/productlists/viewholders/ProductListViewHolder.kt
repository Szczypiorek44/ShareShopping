package pl.karolmichalski.shareshopping.presentation.screens.productlists.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.databinding.ItemProductlistBinding

class ProductListViewHolder(private val binding: ItemProductlistBinding,
							private val onItemClick: (ProductList) -> Unit,
							private val onItemLongClick: (ProductList) -> Unit)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

	init {
		binding.listener = this
		binding.longClickListener = this
	}

	override fun onClick(v: View?) {
		binding.productList?.apply {
			onItemClick(this)
		}
	}

	override fun onLongClick(p0: View?): Boolean {
		binding.productList?.apply {
			onItemLongClick(this)
		}
		return true
	}

	fun bind(productList: ProductList) {
		binding.productList = productList
	}
}