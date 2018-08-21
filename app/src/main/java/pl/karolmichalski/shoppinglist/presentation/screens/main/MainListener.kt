package pl.karolmichalski.shoppinglist.presentation.screens.main

import pl.karolmichalski.shoppinglist.data.models.Product


interface MainListener {
	fun onAddBtnClick()

	fun onProductClick(): (Product) -> Unit
}