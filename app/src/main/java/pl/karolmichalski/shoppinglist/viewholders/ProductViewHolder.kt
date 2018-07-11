package pl.karolmichalski.shoppinglist.viewholders

import android.support.v7.widget.RecyclerView
import pl.karolmichalski.shoppinglist.databinding.ItemProductBinding
import pl.karolmichalski.shoppinglist.models.Product
import pl.karolmichalski.shoppinglist.viewlisteners.ProductListener

class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root), ProductListener {

	init {
		binding.listener = this
	}

	override fun onProductClick() {
		binding.product?.apply { checked = !checked }
		binding.invalidateAll()
	}

	fun bind(product: Product) {
		binding.product = product
	}

}