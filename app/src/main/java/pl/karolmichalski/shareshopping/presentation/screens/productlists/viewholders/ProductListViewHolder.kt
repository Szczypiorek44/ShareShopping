package pl.karolmichalski.shareshopping.presentation.screens.productlists.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.shareshopping.data.models.ProductList
import pl.karolmichalski.shareshopping.databinding.ItemProductlistBinding

class ProductListViewHolder(private val binding: ItemProductlistBinding,
							private val onItemClick: (ProductList) -> Unit)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener {

	init {
		binding.listener = this
	}

	override fun onClick(v: View?) {
		binding.productList?.apply {
			onItemClick(this)
		}
	}

	fun bind(productList: ProductList) {
		binding.productList = productList
	}
}