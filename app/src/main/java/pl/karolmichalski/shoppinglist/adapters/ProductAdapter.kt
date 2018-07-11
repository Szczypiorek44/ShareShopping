package pl.karolmichalski.shoppinglist.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.karolmichalski.shoppinglist.R
import pl.karolmichalski.shoppinglist.models.Product
import pl.karolmichalski.shoppinglist.viewholders.ProductViewHolder

class ProductAdapter(context: Context) : RecyclerView.Adapter<ProductViewHolder>() {

	private val productList: ArrayList<Product> = ArrayList()
	private val layoutInflater = LayoutInflater.from(context)

	fun setProductList(productList: List<Product>) {
		this.productList.clear()
		this.productList.addAll(productList)
		notifyDataSetChanged()
	}

	fun addProduct(product: Product) {
		this.productList.add(product)
		notifyItemInserted(this.productList.size)
	}

	override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder {
		return ProductViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_product, viewGroup, false))
	}

	override fun onBindViewHolder(viewHolder: ProductViewHolder, i: Int) {
		viewHolder.bind(productList[i])
	}

	override fun getItemCount(): Int {
		return productList.size
	}


}