package pl.karolmichalski.shareshopping.presentation.screens.productlists

import android.view.View
import pl.karolmichalski.shareshopping.data.models.ProductList

interface ProductListsListener {
	fun onItemClick(): (ProductList) -> Unit
	fun onAddClick(view: View)
}