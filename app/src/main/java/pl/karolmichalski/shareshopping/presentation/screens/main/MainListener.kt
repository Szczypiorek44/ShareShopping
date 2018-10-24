package pl.karolmichalski.shareshopping.presentation.screens.main

import pl.karolmichalski.shareshopping.data.models.Product


interface MainListener {
	fun onAddBtnClick()

	fun onProductClick(): (Product) -> Unit
}