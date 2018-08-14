package pl.karolmichalski.shoppinglist.viewlisteners

import pl.karolmichalski.shoppinglist.models.Product


interface MainListener {
	fun onAddBtnClick()

	fun onProductClick(): (Product) -> Unit
}