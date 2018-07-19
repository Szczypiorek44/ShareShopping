package pl.karolmichalski.shoppinglist.viewlisteners

import pl.karolmichalski.shoppinglist.adapters.ProductAdapter

interface MainListener: ProductAdapter.ProductClickCallback {
	fun onAddBtnClick()
}