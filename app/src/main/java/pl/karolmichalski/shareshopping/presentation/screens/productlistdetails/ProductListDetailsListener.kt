package pl.karolmichalski.shareshopping.presentation.screens.productlistdetails

import pl.karolmichalski.shareshopping.data.models.Product

interface ProductListDetailsListener {
	fun onProductClick(): (Product) -> Unit
	fun onAddBtnClick()
}