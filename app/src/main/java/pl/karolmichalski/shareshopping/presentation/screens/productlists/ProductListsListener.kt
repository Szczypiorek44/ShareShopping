package pl.karolmichalski.shareshopping.presentation.screens.productlists

import pl.karolmichalski.shareshopping.data.models.ProductList

interface ProductListsListener {
	fun onItemClick(): (ProductList) -> Unit
}