package pl.karolmichalski.shoppinglist.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import pl.karolmichalski.shoppinglist.adapters.ProductAdapter
import pl.karolmichalski.shoppinglist.databinding.ItemProductBinding
import pl.karolmichalski.shoppinglist.models.Product

class ProductViewHolder(private val binding: ItemProductBinding,
						private val productClickCallback: ProductAdapter.ProductClickCallback)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener {


	init {
		binding.listener = this
	}

	override fun onClick(v: View?) {
		binding.product?.apply {
			checked = !checked
			productClickCallback.onProductClick(this)
		}
		binding.invalidateAll()
	}

	fun bind(product: Product) {
		binding.product = product
	}

}