package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.shareshopping.data.models.Product
import pl.karolmichalski.shareshopping.databinding.ItemProductBinding

class ProductViewHolder(private val binding: ItemProductBinding,
						private val onProductClick: (Product) -> Unit)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener {

	init {
		binding.listener = this
	}

	override fun onClick(v: View?) {
		binding.product?.apply {
			onProductClick(this)
		}
		binding.invalidateAll()
	}

	fun bind(product: Product) {
		binding.product = product
	}
}